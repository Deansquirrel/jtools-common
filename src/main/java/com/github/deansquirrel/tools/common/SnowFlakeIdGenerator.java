package com.github.deansquirrel.tools.common;

/**
 * Twitter's Concurrent Id Generator -- SnowFlake
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0;
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的
 * 41位的时间截，可以使用69年，即 {@code T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69}
 * 10位的数据机器位，可以部署在1024个节点，包括：
 *     5位datacenter Id：数据中心机器号
 *     5位worker Id：工作机器号，预分配不重复的ID
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，
 */
public class SnowFlakeIdGenerator {
    //开始时间戳：2020-01-01
    private final long twitEpoch = 1577808000000L;

    //机器ID的位数：5
    private final long workerIdBits = 5L;
    
    //数据中心ID的位数：5
    private final long datacenterIdBits = 5L;
    
    //机器ID的最大值：31
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    
    //数据中心ID的最大值：31
    private final long maxDatacenterId = -1L ^ ( - 1L << datacenterIdBits);
    
    //序列所占的位数：12
    private final long sequenceBits = 12L;
    
    //机器ID的偏移位数：12
    private final long workerIdShift = sequenceBits;
    
    //数据中心的偏移位数：12 + 5 = 17
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    
    //时间戳的偏移位数：12 + 5 + 5 = 22
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    
    //生成序列的掩码，这里为：0b111111111111=0xfff=4095
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    
    //工作机器的ID
    private long workerId;
    
    //数据中心的ID
    private long datacenterId;
    
    //毫秒内的序列值
    private long sequence = 0L;
    
    //上一次生成序列的时间戳
    private long lastTimestamp = -1L;
    
    /***
     * 构造器
     * @param workerId 工作机器的ID
     * @param datacenterId 数据中心的ID
     */
    public SnowFlakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id cannot be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id cannot be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    
    /***
     * 获取下一个ID（线程安全的）
     * @return SnowFlakeID
     */
    public synchronized long getNextId() {
        //获取时间戳
        long timestamp = getTimeStamp();
        
        //当前时间戳小于上一次分配时的时间戳，证明系统时钟经过了回退，此时直接抛出异常即可
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("clock moved backwards. Refused to generate id for %d milliseconds", lastTimestamp));
        }
        
        //如果是同一个时间戳内，则进行毫秒内的序列生成
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //当前毫秒内的序列号用完了，等待至下一秒再分配
                timestamp = waitForNextMillis(lastTimestamp);
            }
        // 时间戳改变，重置毫秒内的序列为0
        } else {
            sequence  = 0L;
        }
        
        lastTimestamp = timestamp;
        
        //拼接ID
        return ((timestamp - twitEpoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }
    
    /***
     * 用循环进行当前毫秒的阻塞，直至新的时间戳
     * @param lastTimestamp 上一次生成ID的时间戳
     * @return 新的时间戳
     */
    protected long waitForNextMillis(long lastTimestamp) {
        long timestamp = getTimeStamp();
        while(timestamp <= lastTimestamp) {
            timestamp = getTimeStamp();
        }
        return timestamp;
    }
    
    /***
     * 当前时间
     * @return 以毫秒为单位的当前时间
     */
    protected long getTimeStamp() {
        return System.currentTimeMillis();
    }
}

package com.github.deansquirrel.tools.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileTool {

    private static final Logger logger = LoggerFactory.getLogger(FileTool.class);

    /**
     * 获取当前路径
     * @return 当前路径
     */
    public static String getCurrPath() {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(path.exists()) {
                return path.getAbsolutePath();
            }
        } catch (FileNotFoundException e) {
            logger.warn(ExceptionTool.getStackTrace(e));
        }
        return (new File("")).getAbsolutePath();
    }

    /**
     * 创建新文件或文件路径
     * @param path 路径
     */
    public static boolean createNewFile(String path) {
        File file = new File(path);
        File fileParent = file.getParentFile();
        if(fileParent.exists()) {
            return true;
        }
        try {
            return fileParent.mkdirs();
        } catch (SecurityException e) {
            logger.warn(ExceptionTool.getStackTrace(e));
            return false;
        }
    }

    /**
     * 文件格式转换
     * @param mf MultipartFile格式文件爱你对象
     * @return File文件对象
     * @throws IOException IO异常
     */
    public static File transFile(MultipartFile mf) throws IOException {
        String fileName = mf.getOriginalFilename();
        if(fileName == null || fileName.isEmpty()) {
            fileName = "f" + System.currentTimeMillis();
        }
        File f = new File(fileName);
        OutputStream out = null;
        try {
            out = Files.newOutputStream(f.toPath());
            byte[] ss = mf.getBytes();
            for (byte s : ss) {
                out.write(s);
            }
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.warn(ExceptionTool.getStackTrace(e));
                }
            }
        }
        return f;
    }


}

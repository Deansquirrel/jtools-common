package com.github.deansquirrel.tools.common;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 对象管理器
 * 
 * @author yuansong6@163.com
 *
 * @param <T>
 */

public class ObjectManager<T> {

	private Map<String, T> _list;

	public ObjectManager() {
		_list = new ConcurrentHashMap<>();
	}

	/***
	 * 注册
	 * 
	 * @param key
	 * @param v
	 */
	public void register(String key, T v) {
		synchronized (_list) {
			if (!_list.containsKey(key)) {
				_list.put(key, v);
			} else {
				unregister(key);
				register(key, v);
			}
		}
	}

	/***
	 * 反注册
	 * 
	 * @param key
	 */
	public void unregister(String key) {
		synchronized (_list) {
			if (_list.containsKey(key)) {
				_list.remove(key);
			}
		}
	}

	/***
	 * 获取已注册对象长度
	 * 
	 * @return
	 */
	public Integer length() {
		return _list.size();
	}

	/***
	 * 对象管理器是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return length() == 0;
	}

	/***
	 * 根据key获取已注册对象
	 * 
	 * @param key
	 * @return
	 */
	public T getObject(String key) {
		return _list.get(key);
	}

	/***
	 * 获取所有已注册的Key值
	 * 
	 * @return
	 */
	public Set<String> keyList() {
		return _list.keySet();
	}

	/***
	 * 判断指定Key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return _list.containsKey(key);
	}

	/***
	 * 反注册当前所有key
	 */
	public void clear() {
		synchronized (_list) {
			for (String key : keyList()) {
				unregister(key);
			}
		}
	}
}

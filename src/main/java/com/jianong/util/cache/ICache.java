package com.jianong.util.cache;

import java.io.Serializable;
import java.util.Map;

/**
 * 缓存顶级接口
 * 
 * @ClassName: ICache
 * @Description:
 * @author: wxd
 * @date: 2017年3月21日 下午3:31:33
 */
public interface ICache {

	/**
	 * 根据key获取值
	 * 
	 * @Title: get
	 * @Description:
	 * @param key
	 * @return
	 * @throws CacheException
	 * @return: T
	 */
	<T> T get(String key) throws CacheException;

	/**
	 * 设置键值和有限期
	 * 
	 * @Title: set
	 * @Description:
	 * @param key
	 * @param expiry
	 *            --有限期（秒）
	 * @param value
	 * @throws CacheException
	 * @return: void
	 */
	void set(String key, int expiry, String value) throws CacheException;

	void set(String key, String value) throws CacheException;

	/**
	 * hset
	 * 
	 * @Title: hset
	 * @Description:
	 * @param key
	 * @param dataMap
	 *            数据
	 * @throws CacheException
	 * @return: void
	 */
	void hset(String key, Map<String, ? extends Serializable> dataMap) throws CacheException;
	
	

}

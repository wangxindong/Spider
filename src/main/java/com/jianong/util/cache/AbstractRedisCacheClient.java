package com.jianong.util.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractRedisCacheClient {

	/**
	 * 根据Key获取String类型数据
	 * 
	 * @param key
	 *            键值
	 * @return String
	 * @throws CacheException
	 */
	public String get(String key) throws CacheException {
		return null;
	}

	/**
	 * 根据Key获取String类型数据
	 * 
	 * @param key
	 *            键值
	 * @return byte[]
	 * @throws CacheException
	 */
	public byte[] get(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 设置String类型的数据
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 * @throws CacheException
	 */
	public void set(String key, String value) throws CacheException {

	}

	/**
	 * 设置String类型的数据
	 * 
	 * @param key
	 *            键值字节数组
	 * @param value
	 *            数据字节数组
	 * @throws CacheException
	 */
	public void set(byte[] key, byte[] value) throws CacheException {
	}

	/**
	 * 删除键值为key的数据
	 * 
	 * @param key
	 *            键值
	 * @throws CacheException
	 */
	public void del(String key) throws CacheException {
	}

	/**
	 * 删除指定的key
	 * 
	 * @param key
	 *            键值字节数组
	 * @throws CacheException
	 */
	public void del(byte[] key) throws CacheException {
	}

	/**
	 * 从头部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 * @throws CacheException
	 */
	public void lpush(String key, String value) throws CacheException {
	}

	/**
	 * 从头部添加元素
	 * 
	 * @param key
	 *            键值字节数组
	 * @param value
	 *            数据字节数组
	 * @throws CacheException
	 */
	public void lpush(byte[] key, byte[] value) throws CacheException {
	}

	/**
	 * 从尾部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 * @throws CacheException
	 */
	public void rpush(String key, String value) throws CacheException {
	}

	/**
	 * 从尾部添加元素
	 * 
	 * @param key
	 *            键值字节数组
	 * @param value
	 *            数据字节数组
	 * @throws CacheException
	 */

	/**
	 * 从list 的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return String
	 * @throws CacheException
	 */
	public String lpop(String key) throws CacheException {
		return null;
	}

	/**
	 * 从list 的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值字节数组
	 * @return String
	 * @throws CacheException
	 */
	public byte[] lpop(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 从list 的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return String
	 * @throws CacheException
	 */
	public String rpop(String key) throws CacheException {
		return null;
	}

	/**
	 * 从list 的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值字节数组
	 * @return byte[]
	 * @throws CacheException
	 */
	public byte[] rpop(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 返回key 对应list 的长度
	 * 
	 * @param key
	 *            键值
	 * @return Long
	 * @throws CacheException
	 */
	public Long llen(String key) throws CacheException {
		return null;
	}

	/**
	 * 返回key 对应list 的长度
	 * 
	 * @param key
	 *            键值字节数组
	 * @return Long
	 * @throws CacheException
	 */
	public Long llen(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 返回指定范围的数据
	 * 
	 * @param key
	 *            键值
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return List<String>
	 * @throws CacheException
	 */
	public List<String> lrange(String key, long start, long end) throws CacheException {
		return null;
	}

	/**
	 * 返回指定范围的数据
	 * 
	 * @param key
	 *            键值字节数组
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return List<byte[]>
	 * @throws CacheException
	 */
	public List<byte[]> lrange(byte[] key, long start, long end) throws CacheException {
		return null;
	}

	/**
	 * 向名称为key 的set 中添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 * @throws CacheException
	 */
	public void sadd(String key, String... value) throws CacheException {
	}

	/**
	 * 向名称为key 的set 中添加元素
	 * 
	 * @param key
	 *            键值字节数组
	 * @param value
	 *            数据字节数组
	 * @throws CacheException
	 */
	public void sadd(byte[] key, byte[]... value) throws CacheException {
	}

	/**
	 * 删除名称为key 的set 中的元素member
	 * 
	 * @param key
	 *            键值
	 * @throws CacheException
	 */
	public void srem(String key) throws CacheException {
	}

	/**
	 * 删除名称为key 的set 中的元素member
	 * 
	 * @param key
	 *            键值字节数组
	 * @throws CacheException
	 */
	public void srem(byte[] key) throws CacheException {
	}

	/**
	 * 返回名称为key 的set 的元素个数
	 * 
	 * @param key
	 *            键值
	 * @return long
	 * @throws CacheException
	 */
	public long scard(String key) throws CacheException {
		return 0;
	}

	/**
	 * 返回名称为key 的set 的元素个数
	 * 
	 * @param key
	 *            键值
	 * @return long
	 * @throws CacheException
	 */
	public long scard(byte[] key) throws CacheException {
		return 0;
	}

	/**
	 * 返回所有成员
	 * 
	 * @param key
	 *            键值
	 * @return Set<String>
	 * @throws CacheException
	 */
	public Set<String> smembers(String key) throws CacheException {
		return null;
	}

	/**
	 * 返回所有成员
	 * 
	 * @param key
	 *            键值字节数组
	 * @return Set<byte[]>
	 * @throws CacheException
	 */
	public Set<byte[]> smembers(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 设置hash field 为指定值，如果key 不存在，则先创建
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 * @throws CacheException
	 */
	public void hset(String key, String field, String value) throws CacheException {
	}

	/**
	 * 设置hash field 为指定值，如果key 不存在，则先创建
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @param value
	 *            值
	 * @throws CacheException
	 */
	public void hset(byte[] key, byte[] field, byte[] value) throws CacheException {
	}

	/**
	 * 获取指定的hash field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return String
	 * @throws CacheException
	 */
	public String hget(String key, String field) throws CacheException {
		return null;
	}

	/**
	 * 获取指定的hash field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return String
	 * @throws CacheException
	 */
	public byte[] hget(byte[] key, byte[] field) throws CacheException {
		return null;
	}

	/**
	 * 返回hash 的所有value
	 * 
	 * @param key
	 *            键值
	 * @return List<String>
	 * @throws CacheException
	 */
	public List<String> hvals(String key) throws CacheException {
		return null;
	}

	/**
	 * 返回hash 的所有value
	 * 
	 * @param key
	 *            键值
	 * @return Collection<byte[]>
	 * @throws CacheException
	 */
	public Collection<byte[]> hvals(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 返回hash 的所有field
	 * 
	 * @param key
	 *            键值
	 * @return Set<String>
	 * @throws CacheException
	 */
	public Set<String> hkeys(String key) throws CacheException {
		return null;
	}

	/**
	 * 返回hash 的所有field
	 * 
	 * @param key
	 *            键值
	 * @return Set<String>
	 * @throws CacheException
	 */
	public Set<byte[]> hkeys(byte[] key) throws CacheException {
		return null;
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值
	 * @return long
	 * @throws CacheException
	 */
	public long hlen(String key) throws CacheException {
		return 0;
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值
	 * @return long
	 * @throws CacheException
	 */
	public long hlen(byte[] key) throws CacheException {
		return 0;
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @throws CacheException
	 */
	public long hdel(String key, String field) throws CacheException {
		return 0;
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值字节数组
	 * @return field 字段
	 * @throws CacheException
	 */
	public long hdel(byte[] key, byte[] field) throws CacheException {
		return 0;
	}

	/**
	 * 生成递增的整数值
	 * 
	 * @param key
	 *            价值
	 * @return long
	 * @throws CacheException
	 */
	public long incr(String key) throws CacheException {
		return -1;
	};

	/**
	 * 生成递增的整数值
	 * 
	 * @param key
	 *            价值
	 * @return long
	 * @throws CacheException
	 */
	public long incr(byte[] key) throws CacheException {
		return -1;
	}

}

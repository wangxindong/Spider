package com.jianong.util.cache;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工具包装类
 * 
 * @ClassName: RedisClientUtil
 * @Description:
 * @author: wxd
 * @date: 2017年3月21日 下午4:58:44
 */
public class RedisClientUtil {

	private static volatile RedisClientUtil redisClientUtil;
	private static JedisPool pool;

	static {
		getConfig();
		init();
	}

	private RedisClientUtil() {
	}

	/**
	 * 外部获取对象
	 * 
	 * @Title: getInstance
	 * @Description:
	 * @return
	 * @return: RedisClientUtil
	 */
	public static RedisClientUtil getInstance() {
		if (null == redisClientUtil) {
			synchronized (RedisClientUtil.class) {
				if (null == redisClientUtil) {
					redisClientUtil = new RedisClientUtil();
				}
			}
		}
		return redisClientUtil;
	}

	private static void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(20);
		config.setMaxIdle(10);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		pool = new JedisPool(config, "192.168.0.252", 7000, 100, "jianongyqjk802#Date20160606");
	}

	private static void getConfig() {
	}

	/**
	 * 获取key 对应的string 值,如果key 不存在返回nil
	 * 
	 * @param key
	 *            键值
	 * @return byte[]
	 */
	public byte[] get(byte[] key) {
		byte[] value = null;
		try (Jedis jedis = pool.getResource()) {
			value = jedis.get(key);
		}
		return value;
	}

	/**
	 * 获取key 对应的string 值,如果key 不存在返回nil
	 * 
	 * @param key
	 *            键值
	 * @return String
	 */
	public String get(String key) {
		String value = null;
		try (Jedis jedis = pool.getResource()) {
			value = jedis.get(key);
		}
		return value;

	}

	/**
	 * 设置key 对应的值为string 类型的value
	 * 
	 * @param key
	 * @param value
	 * @return byte[]
	 */
	public byte[] set(byte[] key, byte[] value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.set(key, value).getBytes();
		}
	}

	/**
	 * 设置key 对应的值为string 类型的value
	 * 
	 * @param key
	 * @param value
	 * @return String
	 */
	public String set(String key, String value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.set(key, value);
		}

	}

	/**
	 * 设置key 对应的值为string 类型的value， 并设置超时时长
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 * @param expire
	 *            超时时长
	 * @return byte[]
	 */
	public byte[] set(byte[] key, byte[] value, int expire) {
		String returnValue = null;
		try (Jedis jedis = pool.getResource()) {
			returnValue = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		}
		return returnValue.getBytes();
	}

	/**
	 * 设置key 对应的值为string 类型的value， 并设置超时时长
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return String
	 */
	public String set(String key, String value, int expire) {
		String returnValue = null;
		try (Jedis jedis = pool.getResource()) {
			returnValue = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		}
		return returnValue;
	}

	/**
	 * 删除指定key的数据
	 * 
	 * @param key
	 *            键值
	 */
	public void del(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(key);
		}
	}

	/**
	 * 删除指定key的数据
	 * 
	 * @param key
	 *            键值
	 */
	public void del(String key) {

		try (Jedis jedis = pool.getResource()) {
			jedis.del(key);
		}

	}

	/**
	 * 删除当前选择数据库中的所有key
	 */
	public void flushDB() {
		try (Jedis jedis = pool.getResource()) {
			jedis.flushDB();
		}
	}

	/**
	 * 返回当前数据库中key 的数目
	 * 
	 * @return Long
	 */
	public Long dbSize() {
		Long dbSize = 0L;
		try (Jedis jedis = pool.getResource()) {
			dbSize = jedis.dbSize();
		}
		return dbSize;
	}

	/**
	 * 返回当前数据库中key 的数目
	 * 
	 * @param pattern
	 *            过滤规则
	 * @return Set<byte[]>
	 */
	public Set<byte[]> keys(String pattern) {
		Set<byte[]> keys = null;
		try (Jedis jedis = pool.getResource()) {
			keys = jedis.keys(pattern.getBytes());
		}
		return keys;
	}

	/**
	 * 关闭
	 * 
	 * @param jedis
	 */
	public void close(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
	}

	/**
	 * 从头部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 */
	public void lpush(byte[] key, byte[] value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.lpush(key, value);
		}
	}

	/**
	 * 从头部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 */
	public void lpush(String key, String value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.lpush(key, value);
		}
	}

	/**
	 * 从尾部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 */
	public void rpush(byte[] key, byte[] value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.rpush(key, value);
		}
	}

	/**
	 * 从尾部添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据
	 */
	public void rpush(String key, String value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.rpush(key, value);
		}
	}

	/**
	 * 从list 的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return byte[]
	 */
	public byte[] lpop(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.lpop(key);
		}
	}

	/**
	 * 从list 的头部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return String
	 */
	public String lpop(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.lpop(key);
		}
	}

	/**
	 * 从list 的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return byte[]
	 */
	public byte[] rpop(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.rpop(key);
		}
	}

	/**
	 * 从list 的尾部删除元素，并返回删除元素
	 * 
	 * @param key
	 *            键值
	 * @return String
	 */
	public String rpop(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.rpop(key);
		}
	}

	/**
	 * 返回key 对应list 的长度
	 * 
	 * @param key
	 *            键值
	 * @return Long
	 */
	public Long llen(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.llen(key);
		}
	}

	/**
	 * 返回存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 *            键值
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引
	 * @return List<byte[]>
	 */
	public List<byte[]> lrange(byte[] key, long start, long end) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.lrange(key, start, end);
		}
	}

	/**
	 * 返回存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 *            键值
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引
	 * @return List<byte[]>
	 */
	public List<String> lrange(String key, long start, long end) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.lrange(key, start, end);
		}
	}

	/**
	 * 向名称为key 的set 中添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据集
	 */
	public void sadd(byte[] key, byte[]... value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.sadd(key, value);
		}
	}

	/**
	 * 向名称为key 的set 中添加元素
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            数据集
	 */
	public void sadd(String key, String... value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.sadd(key, value);
		}
	}

	/**
	 * 删除名称为key 的set 中的元素member
	 * 
	 * @param key
	 *            键值
	 */
	public void srem(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.srem(key);
		}
	}

	/**
	 * 返回名称为key 的set 的元素个数
	 * 
	 * @param key
	 *            键值
	 * @return long
	 */
	public long scard(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.scard(key);
		}
	}

	/**
	 * 返回所有成员
	 * 
	 * @param key
	 *            键值
	 * @return Set<byte[]>
	 */
	public Set<byte[]> smembers(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.smembers(key);
		}
	}

	/**
	 * 返回所有成员
	 * 
	 * @param key
	 *            键值
	 * @return Set<byte[]>
	 */
	public Set<String> smembers(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.smembers(key);
		}
	}

	/**
	 * 设置hash field 为指定值，如果key 不存在，则先创建
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @param value
	 *            数据
	 */
	public void hset(byte[] key, byte[] field, byte[] value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.hset(key, field, value);
		}
	}

	/**
	 * 设置hash field 为指定值，如果key 不存在，则先创建
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @param value
	 *            数据
	 */
	public void hset(String key, String field, String value) {
		try (Jedis jedis = pool.getResource()) {
			jedis.hset(key, field, value);
		}
	}

	/**
	 * 获取指定的hash field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return byte[]
	 */
	public byte[] hget(byte[] key, byte[] field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hget(key, field);
		}
	}

	/**
	 * 获取指定的hash field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return byte[]
	 */
	public String hget(String key, String field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hget(key, field);
		}
	}

	/**
	 * 返回hash 的所有value
	 * 
	 * @param key
	 *            键值
	 * @return List<String>
	 */
	public List<String> hvals(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hvals(key);
		}
	}

	/**
	 * 返回hash 的所有value
	 * 
	 * @param key
	 *            键值
	 * @return List<String>
	 */
	public List<byte[]> hvals(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hvals(key);
		}
	}

	/**
	 * 返回hash 的所有field
	 * 
	 * @param key
	 *            键值
	 * @return Set<byte[]>
	 */
	public Set<byte[]> hkeys(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hkeys(key);
		}
	}

	/**
	 * 返回hash 的所有field
	 * 
	 * @param key
	 *            键值
	 * @return Set<String>
	 */
	public Set<String> hkeys(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hkeys(key);
		}
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值
	 * @return long
	 */
	public long hlen(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hlen(key);
		}
	}

	/**
	 * 返回指定hash 的field 数量
	 * 
	 * @param key
	 *            键值
	 * @return long
	 */
	public long hlen(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hlen(key);
		}
	}

	/**
	 * 删除指定hash 的field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return long
	 */
	public long hdel(byte[] key, byte[] field) {

		try (Jedis jedis = pool.getResource()) {
			return jedis.hdel(key, field);
		}

	}

	/**
	 * 删除指定hash 的field
	 * 
	 * @param key
	 *            键值
	 * @param field
	 *            字段
	 * @return long
	 */
	public long hdel(String key, String field) {

		try (Jedis jedis = pool.getResource()) {
			return jedis.hdel(key, field);
		}

	}

	/**
	 * 发布
	 * 
	 * @param channel
	 *            通道
	 * @param message
	 *            消息
	 */
	public void publish(byte[] channel, byte[] message) {

		try (Jedis jedis = pool.getResource()) {
			jedis.publish(channel, message);
		}

	}

	/**
	 * 发布
	 * 
	 * @param channel
	 *            通道
	 * @param message
	 *            消息
	 */
	public void publish(String channel, String message) {

		try (Jedis jedis = pool.getResource()) {
			jedis.publish(channel, message);
		}

	}

	/**
	 * 生成递增的整数值
	 * 
	 * @param key
	 *            键值
	 * @return long
	 */
	public long incr(String key) {

		try (Jedis jedis = pool.getResource()) {
			return jedis.incr(key);
		}

	}

	/**
	 * 生成递增的整数值
	 * 
	 * @param key
	 *            键值
	 * @return long
	 */
	public long incr(byte[] key) {

		try (Jedis jedis = pool.getResource()) {
			return jedis.incr(key);
		}
	}

}

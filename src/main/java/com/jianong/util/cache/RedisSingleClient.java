package com.jianong.util.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 单节点redis使用
 * 
 * @ClassName: RedisSingleClient
 * @Description:
 * @author: wxd
 * @date: 2017年3月21日 下午5:07:56
 */
public class RedisSingleClient extends AbstractRedisCacheClient {

	private RedisClientUtil jedisClient = RedisClientUtil.getInstance();

	@Override
	public String get(String key) throws CacheException {
		try {
			return jedisClient.get(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public byte[] get(byte[] key) throws CacheException {
		try {
			return jedisClient.get(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void set(String key, String value) throws CacheException {
		try {
			jedisClient.set(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void set(byte[] key, byte[] value) throws CacheException {
		try {
			jedisClient.set(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void del(String key) throws CacheException {
		try {
			jedisClient.del(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void del(byte[] key) throws CacheException {
		try {
			jedisClient.del(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void lpush(String key, String value) throws CacheException {
		try {
			jedisClient.lpush(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void lpush(byte[] key, byte[] value) throws CacheException {
		try {
			jedisClient.lpush(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void rpush(String key, String value) throws CacheException {
		try {
			jedisClient.rpush(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public String lpop(String key) throws CacheException {
		try {
			return jedisClient.lpop(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public byte[] lpop(byte[] key) throws CacheException {
		try {
			return jedisClient.lpop(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public String rpop(String key) throws CacheException {
		try {
			return jedisClient.rpop(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public byte[] rpop(byte[] key) throws CacheException {
		try {
			return jedisClient.rpop(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Long llen(String key) throws CacheException {
		try {
			return jedisClient.llen(key.getBytes());
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Long llen(byte[] key) throws CacheException {
		try {
			return jedisClient.llen(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public List<String> lrange(String key, long start, long end) throws CacheException {
		try {
			return jedisClient.lrange(key, start, end);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public List<byte[]> lrange(byte[] key, long start, long end) throws CacheException {
		try {
			return jedisClient.lrange(key, start, end);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void sadd(String key, String... value) throws CacheException {
		try {
			jedisClient.sadd(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void sadd(byte[] key, byte[]... value) throws CacheException {
		try {
			jedisClient.sadd(key, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void srem(String key) throws CacheException {
		try {
			jedisClient.srem(key.getBytes());
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void srem(byte[] key) throws CacheException {
		try {
			jedisClient.srem(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long scard(String key) throws CacheException {
		try {
			return jedisClient.scard(key.getBytes());
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long scard(byte[] key) throws CacheException {
		try {
			return jedisClient.scard(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Set<String> smembers(String key) throws CacheException {
		try {
			return jedisClient.smembers(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Set<byte[]> smembers(byte[] key) throws CacheException {
		try {
			return jedisClient.smembers(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void hset(String key, String field, String value) throws CacheException {
		try {
			jedisClient.hset(key, field, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void hset(byte[] key, byte[] field, byte[] value) throws CacheException {
		try {
			jedisClient.hset(key, field, value);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public String hget(String key, String field) throws CacheException {
		try {
			return jedisClient.hget(key, field);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) throws CacheException {
		try {
			return jedisClient.hget(key, field);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public List<String> hvals(String key) throws CacheException {
		try {
			return jedisClient.hvals(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Collection<byte[]> hvals(byte[] key) throws CacheException {
		try {
			return jedisClient.hvals(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Set<String> hkeys(String key) throws CacheException {
		try {
			return jedisClient.hkeys(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Set<byte[]> hkeys(byte[] key) throws CacheException {
		try {
			return jedisClient.hkeys(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long hlen(String key) throws CacheException {
		try {
			return jedisClient.hlen(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long hlen(byte[] key) throws CacheException {
		try {
			return jedisClient.llen(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long hdel(String key, String field) throws CacheException {
		try {
			return jedisClient.hdel(key, field);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	public long hdel(byte[] key, byte[] field) throws CacheException {
		try {
			return jedisClient.hdel(key, field);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long incr(String key) throws CacheException {
		try {
			return jedisClient.incr(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public long incr(byte[] key) throws CacheException {
		try {
			return jedisClient.incr(key);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

}

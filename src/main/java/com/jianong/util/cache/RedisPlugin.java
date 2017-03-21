package com.jianong.util.cache;

public class RedisPlugin {

	public AbstractRedisCacheClient getRedisClient() {
		AbstractRedisCacheClient client = null;
		client = new RedisSingleClient();
		return client;
	}
}

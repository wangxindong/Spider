package com.jianong.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.FactoryBean;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.jianong.util.Logger;

/**
 * 缓存工厂方法，继承自spring的FactoryBean
 * 生成的实例是一个guava包提供的缓存对象
 * @author dell
 *
 */
public class CacheFactory implements FactoryBean<Cache<String, Object>> {
	Logger logger = Logger.getLogger(Cache.class);
	@Override
	public Cache<String,Object> getObject() throws Exception {
		Cache<String, Object> cache= CacheBuilder
				.newBuilder()
				.maximumSize(1000)
				.expireAfterAccess(30, TimeUnit.MINUTES).removalListener(new RemovalListener<String, Object>() {
					@Override
					public void onRemoval(RemovalNotification<String, Object> notification) {
						String tips = String.format("key=%s,value=%s,reason=%s", 
								notification.getKey(), 
								notification.getValue(), 
								notification.getCause());
						logger.debug(tips);
			            System.out.println(tips);
					}
				}).build();
		return cache;
	}

	@Override
	public Class<?> getObjectType() {
		return Cache.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

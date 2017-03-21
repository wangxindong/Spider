package com.jianong.util.cache.config;

import java.util.Set;

public class RedisConfig {
	/**
	 * 默认超时时长
	 */
	private static final Integer DEFAULT_TIMEOUT = 300000;

	/**
	 * 最大分发数
	 */
	private static final Integer DEFAULT_MAX_REDIRECTIONS = 6;

	/**
	 * 是否集群
	 */
	private boolean cluster = true;

	/**
	 * 默认数据库
	 */
	private Integer dataBase = 0;

	/**
	 * 非集群环境的服务器地址
	 */
	private String host;

	/**
	 * 非集群环境下的服务器端口
	 */
	private Integer port;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 最大等待时长
	 */
	private Integer maxWaitMillis = -1;

	/**
	 * 最大总数
	 */
	private Integer maxTotal = 1000;

	/**
	 * 最小空闲数
	 */
	private Integer minIdle = 8;

	/**
	 * 最大空闲数
	 */
	private Integer maxIdle = 100;

	/**
	 * 超时时长
	 */
	private Integer timeout = DEFAULT_TIMEOUT;

	/**
	 * 最大分发数
	 */
	private Integer maxRedirections = DEFAULT_MAX_REDIRECTIONS;

	/**
	 * 服务器地址
	 */
	private Set<String> clusterAddress;

	/**
	 * 系统代码
	 */
	private String systemCode = "DEFAULT";

	/**
	 * 默认的动态库文件名称
	 */
	private static final String DEFAULT_DLL_NAME = "HQCluster";

	/**
	 * 动态库文件名
	 */
	private String dllName = DEFAULT_DLL_NAME;

	public boolean isCluster() {
		return cluster;
	}

	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}

	public Integer getDataBase() {
		return dataBase;
	}

	public void setDataBase(Integer dataBase) {
		this.dataBase = dataBase;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Integer maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(Integer maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public Set<String> getClusterAddress() {
		return clusterAddress;
	}

	public void setClusterAddress(Set<String> clusterAddress) {
		this.clusterAddress = clusterAddress;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getDllName() {
		return dllName;
	}

	public void setDllName(String dllName) {
		this.dllName = dllName;
	}

}

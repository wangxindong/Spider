package com.jianong.util.cache;

/**
 * 自定义cache异常类
 * 
 * @ClassName: CacheException
 * @Description:
 * @author: wxd
 * @date: 2017年3月21日 下午3:34:32
 */
public class CacheException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	public CacheException() {
		super();
	}

	public CacheException(String message, Throwable t) {
		super(message, t);
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable t) {
		super(t);
	}

}

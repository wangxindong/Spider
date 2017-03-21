package com.government.spider.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 转码管理器
 * 
 * @author zel
 * 
 */
public class EncodingUtil {
	public static String encode(String keyword) {
		try {
			return URLEncoder.encode(keyword, StaticValue.default_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decode(String keyword,String charset) {
		try {
			return URLDecoder.decode(keyword, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String keyword="361%C2%B0";
		System.out.println(decode(keyword,"utf-8"));
	}
}

package com.government.spider.url;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jianong.util.PageData;
/**
 * 中国猪业网、中国禽业网其中rule_fine表中crawltype = 1
 */

public class ZhuJi {
	private final static String hrefRule = "JAVASCRIPT:[A-Za-z]*(.*)";
	private final static String oriUrl = "http://www.caaa.cn/show/newsarticle.php?ID=";
	
	public static String getUnVisitedHrefPd(PageData pd,String href){
		String crawlUrl = "";
		//获得其精准URL规则
		String urlRule = pd.getString("weburl");
		String module = pd.getString("module");
		//JAVASCRIPT:[A-Za-z]*(.*)判断是否是目标href
		if(isUrl(href, hrefRule)){
			crawlUrl = oriUrl + getNum(href);
		}else if(isUrl(href, urlRule)){
			crawlUrl = href;
		}else if(isUrl(module+href, urlRule)){
			crawlUrl = module+href;
		}
		return crawlUrl;
	}
	
	/**
	 * 判断URL是否是目标精准url
	 * @param args
	 */
	public static boolean isUrl(String url,String urlRule){
		Pattern pattern = Pattern.compile(urlRule);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}
	
	/**
	 * 提取数字的方法
	 * @param args
	 */
	public static String getNum(String href){
		String hrefId = "";
		String regEx="[^0-9]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(href);
		hrefId = matcher.replaceAll("").trim();
		return hrefId;
	}
	
	public static void main(String[] args){
		System.out.println(isUrl("JAVASCRIPT:CAAA(380932)", "JAVASCRIPT:[A-Za-z]*(.*)"));
		
		String a="JAVASCRIPT:CAAA(380932)";
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(a);   
		System.out.println( m.replaceAll("").trim());
	}
}

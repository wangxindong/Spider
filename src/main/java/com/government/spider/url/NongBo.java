package com.government.spider.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jianong.util.PageData;

public class NongBo {

	
	public static String getUnVisitedHrefPd(PageData pd,String href){
		String crawlUrl = "";
		//获得其精准URL规则
		String urlRule = pd.getString("weburl");
		String oriUrl = pd.getString("oriurl");
		if(isUrl(href, urlRule)){
			crawlUrl = href;
		}else{
			crawlUrl = oriUrl + href;
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
}

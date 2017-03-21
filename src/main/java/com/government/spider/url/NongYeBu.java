package com.government.spider.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jianong.util.PageData;
/**
 * 农业部相关网站，rule_fine表中crawltype=0
 */
public class NongYeBu {

	public static String getUnVisitedHrefPd(PageData pd,String href){
		//获得其精准URL规则
		String urlRule = pd.getString("weburl");
		String url = pd.get("module")+href;
		//"http://www.moa.gov.cn/zwllm/[A-Za-z]+/[A-Za-z]+/./[A-Za-z]+/\\d*/\\w+.(htm)|(html)$"
		boolean isUrl = isUrl(String.valueOf(url),String.valueOf(urlRule));
		System.out.println("抓取到的URL:"+url+",规则："+urlRule+"，是否符合精准URL:"+isUrl);
		if(isUrl){
			return url;
		}
		return null;
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

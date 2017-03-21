package com.government.spider.url;

import com.jianong.util.PageData;
/**
 * 鸡病专业网
 * @author dell
 *
 */
public class Jibing {

	public static String getUnVisitedHrefPd(PageData pd,String href){
		String crawlUrl = "";
		//获得其精准URL规则
		String urlRule = pd.getString("oriurl");
		crawlUrl = urlRule+href;
		return crawlUrl;
	}
}

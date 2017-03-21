package com.government.spider.url;

import com.jianong.util.PageData;
/**
 * 获取URL
 * @author dell
 *
 */
public class GetUrl {
	/**
	 * 养鸡网获取精准URL方法
	 * @param pd
	 * @param href
	 * @return
	 */
	public static String getUnVisitedHrefPd(PageData pd,String href){
		String crawlUrl = "";
		//获得其精准URL规则
		String urlRule = pd.getString("oriurl");
		crawlUrl = urlRule+href;
		return crawlUrl;
	}
}

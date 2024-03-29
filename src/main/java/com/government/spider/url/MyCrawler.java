package com.government.spider.url;

import java.util.Set;

public class MyCrawler {
	
	
	/**
	 * 使用种子初始化URL队列
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	
	/**
	 * 抓取过程
	 */
	public void crawling(String[] seeds){
		//定义过滤器，提取以http://www.lietu.com开头的链接
//		LinkFilter filter = new LinkFilter() {
//			http://www.lietu.com
//			@Override
//			public boolean accept(String url) {
//				if(url.startsWith("http://www.moa.gov.cn/zwllm/zcfg/"))
//					return true;
//				else
//					return false;
//			}
//		};
		
		//初始化URL队列
		initCrawlerWithSeeds(seeds);
		
		//循环条件：待抓取的链接不空且抓取的页面不多余1000个
		while(!LinkQueue.unVisitedUrlsEmpty() && LinkQueue.getVisitedUrlNum() <= 1000){
			//对头URL出队列
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			if(visitUrl == null)
				continue;
			DownLoadFile downLoader = new DownLoadFile();
			//下载网页中精准URL
			//downLoader.downUrlByOriUrl(visitUrl);
			//该URL放入已访问的URL中
			LinkQueue.addUnvisitedUrl(visitUrl);
			//提取出下载网页的URL
//			Set<String> links = HtmlParseTool.extracLinks(visitUrl, filter);
			//新的未访问的URL入队
//			for(String link : links){
//				LinkQueue.addUnvisitedUrl(link);
//			}
//			System.out.println(links.size());
		}
	}
	
	//main入口测试
	public static void main(String[] args){
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[]{"www.moa.gov.cn"});
	}
}

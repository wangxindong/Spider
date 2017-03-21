package com.government.spider.url;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.government.spider.crawler.httpclient.Crawl4HttpClient;
import com.government.spider.pojos.HttpRequestPojo;
import com.government.spider.pojos.enumeration.CharsetEnum;
import com.government.spider.pojos.enumeration.HttpRequestMethod;
import com.jianong.util.PageData;


public class DownLoadFile {
	
	/**
	 * 根据URL和网页类型生成需要保持的网页的文件名，去除URL中的非文件名称符
	 */
	public String getFileNameByUrl(String url,String contentType){
		//移除http
		url=url.substring(7);
		//text/html类型
		if(contentType.indexOf("html") != -1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
			return url;
		}
		//如application/pdf类型
		else{
			return url.replaceAll("[\\?/:*|<>\"]", "_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	/**
	 * 保存网页字节数组到本地文件，filePath为要保存的文件的相对地址
	 */
	private void saveToLocal(byte[] data ,String filePath){
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for (int i = 0; i < data.length; i++) {
				out.write(data[i]);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//下载URL指向的网页
	public String downloadFile(String url){
		String filePath = null;
		//1.生成HttpClinet对象并设置参数
		HttpClient httpClient = new HttpClient();
		//设置HTTP链接超时5s
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.生成GetMethod对象并设置参数
		GetMethod getMethod = new GetMethod(url);
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		//3.执行HTTP GET请求
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			//判断语句访问的状态码
			if(statusCode != HttpStatus.SC_OK){
				System.out.println("Method failed:"+getMethod.getStatusLine());
				filePath = null;
			} 
			//处理http相应内容
			byte[] responseBoby = getMethod.getResponseBody();//读取为字节数组
			//根据网页URL生成保存时的文件名
			filePath = "E:\\"+getFileNameByUrl(url, getMethod.getRequestHeader("Host").getValue());
//			saveToLocal(responseBoby, filePath);
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		}catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		}finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return filePath;
	}
	
	
	/**
	 * 下载指定页面下的所有精准页面地址
	 * @param oriurl
	 * @return
	 * @throws Exception 
	 */
	public void downUrlByOriUrl(PageData pd){
		try {
			//提取待抓取的精确页面
			List<String> visitedUrl = getUrlList(pd);
			//存入抓取页面
			for (int i = 0; i < visitedUrl.size(); i++) {
				System.out.println("开始写入！"+","+pd.get("oriurl")+","+visitedUrl.get(i));
				//crawlUrlService.insertUrl((String) pd.get("oriurl"),visitedUrl.get(i),(String)pd.get("modulename"));
				System.out.println("抓取完成！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 提取待抓取的精确页面
	 */
	public  List<String> getUrlList(PageData pd) throws Exception{
		List<String> resultList = new ArrayList<String>();
		//
		resultList = getUrlListByNongyeBu(pd);
		return resultList;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public List<String> getUrlListByNongyeBu(PageData pd) throws Exception{
		//存储未爬取的url
				List<String> visitedUrl = new ArrayList<String>();
				//获得其精准URL规则
				String urlRule = pd.getString("weburl");
				//获取到已抓取的过的URL地址，放入缓存中
				//List<String> visitedUrlList = crawlUrlService.selectUrl((String) pd.get("oriurl"));
				//获取页面中的所有匹配规则的url地址
				HttpRequestPojo requestPojo = new HttpRequestPojo();
				requestPojo.setRequestMethod(HttpRequestMethod.GET);
				
				Map<String, String> formNameValueMap = new HashMap<String, String>();

				//unCrawlUrl临时保存翻页的URL
				requestPojo.setUrl((String) pd.get("unCrawlUrl"));
//				requestPojo.setUrl("http://www.moa.gov.cn/zwllm/tzgg/");
				requestPojo.setFormNameValePairMap(formNameValueMap, CharsetEnum.UTF8);

				String source = Crawl4HttpClient.crawlWebPage(requestPojo);
				 
				Document doc = Jsoup.parse(source);
				//.rlr a
				Elements urlLinks = doc.select((String) pd.get("rule"));
				System.out.println("开始抓取！"+pd.getString("unCrawlUrl")+","+pd.get("rule"));
				List<String> crawlUrlList = new ArrayList<String>();
				for (int i = 0; i < urlLinks.size(); i++) {
					String url = "";
					if((int) pd.get("crawltype") == 0){
						url = NongYeBu.getUnVisitedHrefPd(pd, urlLinks.get(i).attr("href"));
					}else if((int) pd.get("crawltype") == 1){
						url = ZhuJi.getUnVisitedHrefPd(pd, urlLinks.get(i).attr("href"));
					}else if((int) pd.get("crawltype") == 2||(int) pd.get("crawltype") == 4){
						url = NongBo.getUnVisitedHrefPd(pd, urlLinks.get(i).attr("href"));
					}else if((int) pd.get("crawltype") == 3||(int) pd.get("crawltype") == 5){
						url = Jibing.getUnVisitedHrefPd(pd, urlLinks.get(i).attr("href"));
					}else if ((int) pd.get("crawltype") == 6){
						url = urlLinks.get(i).attr("href");
					}
					if(url != ""){
//						"http://www.moa.gov.cn/zwllm/[A-Za-z]+/[A-Za-z]+/./[A-Za-z]+/\\d*/\\w+.(htm)|(ht ml)$"
						boolean isUrl = isUrl(String.valueOf(url),String.valueOf(urlRule));
						System.out.println("抓取到的URL:"+url+",规则："+urlRule+"，是否符合精准URL:"+isUrl);
						if(isUrl){
							System.out.println(i+":"+url);
							crawlUrlList.add(url);
						}
					}
				}
				//抓取的url与已抓取的url进行匹配，获得没有抓取过的URL列表
				//如果为空证明发布新的文章
				/*if(crawlUrlList.size() != 0 && visitedUrlList.size() != 0){
					visitedUrl = getUnvisitedUrl(crawlUrlList,visitedUrlList);
					System.out.println("抓取个数："+visitedUrl.size());
					return visitedUrl;
				}else{
					//返回未抓取过的数据列表
					return crawlUrlList;
				}*/
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
	
	/**
	 * 获取为爬取部分的URL
	 */
	public static List<String> getUnvisitedUrl(List<String> crawlUrlList ,List<String> visitedUrl){
		List<String> unVisitedUrl = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>(crawlUrlList.size(),visitedUrl.size());
		for(String string : crawlUrlList){
			map.put(string, 1);
		}
		
		for(String string : visitedUrl){
			Integer cc = map.get(string);
			if(cc != null){
				map.put(string, ++cc);
				continue;
			}
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			if(entry.getValue() == 1){
				unVisitedUrl.add(entry.getKey());
			}
		}
		
		return unVisitedUrl;
	}
	
	public static void main(String[] args){
//		http://www.moa.gov.cn/zwllm/tzgg/gb/./nybgg/201603/t20160331_5078216.htm,规则：http://www.moa.gov.cn/zwllm/[A-Za-z]+/[A-Za-z]+/./\d*/\w+.(htm)|(html)$
//		http://www.moa.gov.cn/zwllm/tzgg/gg/./201611/t20161103_5348351.htm,规则：http://www.moa.gov.cn/zwllm/[A-Za-z]+/[A-Za-z]+/./\\d*/\\w+.(htm)|(html)$
//		http://shuju.aweb.com.cn/technology/20150109/261260.html,规则：http://[A-Za-z]+.aweb.com.cn/\d*/\d*.html
		http://www.pig66.com/([0-9]+|[A-Za-z]+)/([0-9]+|[A-Za-z]+)(_[0-9]+)?(/[0-9]+/[0-9]+)?/[0-9]+.(html|htm|shtml)？
		System.out.println(isUrl("http://www.1350135.com/html/zhonghe/news/2016/122644237.html",
								 "http://www.1350135.com/[a-zA-Z]+/[a-zA-Z]+/[a-zA-Z]+(/[0-9]+)?/[0-9]+.(html|htm)"));
		
	 	System.out.println((int)(Math.random()*10));
//		PageData pd = new PageData();
//		DownLoadFile dlf = new DownLoadFile();
//		pd.put("module", "http://www.moa.gov.cn/zwllm/tzgg/gg/");
//		pd.put("weburl", "http://www.moa.gov.cn/zwllm/[A-Za-z]+/[A-Za-z]+/./\\d*/\\w+.(htm)|(html)$");
//		pd.put("oriurl", "www.moa.gov.cn");
//		pd.put("href", ".rlr a");
//		try {
//			dlf.getUrlList(pd);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		downUrlByOriUrl("www.moa.gov.cn");
	}
	
}
 
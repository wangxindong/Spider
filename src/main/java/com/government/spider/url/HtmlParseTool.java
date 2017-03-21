package com.government.spider.url;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class HtmlParseTool {
	//获取一个网站上的链接，filter用来过来链接
	public static Set<String> extracLinks(String url ,LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			//过滤<frame>标签的filter,用来提取frame标签中的src属性
			NodeFilter frameFilter = new NodeFilter(){
				public boolean accept(Node node){
					if(node.getText().startsWith("frame src=")){
						return true;
					}else{
						return false;
					}
				}
			};
			//orFilter来设置过滤<a>标签和<frame>标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
			//得到所有经过过滤的标签
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if(tag instanceof LinkTag){//<a>标签
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();//url
					if(filter.accept(linkUrl)){
						links.add(linkUrl);
					}
				}else{//<frame>标签
					//提取frame里面src属性的链接，如<fram src="test.html"/>
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if(end == -1){
						end = frame.indexOf(">");
					}
					String frameUrl = frame.substring(5, end - 1);
					if(filter.accept(frameUrl)){
						links.add(frameUrl);
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
}

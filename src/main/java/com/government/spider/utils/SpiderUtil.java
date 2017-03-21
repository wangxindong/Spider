package com.government.spider.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.reflection.SystemMetaObject;

public class SpiderUtil {
	/**
	 * 字符串中提取xxxx-xx-xx xx:xx:xx 	xxxx/xx/xx xx:xx:xx 格式的时间
	 * 并将部分和此格式的时间形式规范
	 * @param str
	 * @return
	 */
	public static String getDate(String str){
		if(str == null || str == ""){
			return "";
		}
		str = str.replaceAll("/", "-");
		String reg = "\\d{4}\\-\\d{1,2}\\-\\d*(\\s*\\d{1,2}\\:\\d{1,2}(\\:\\d{1,2})?)?";
		Pattern pattern = Pattern.compile (reg);
		Matcher matcher = pattern.matcher(str);
		StringBuffer date = null;
		while(matcher.find()){
			date = new StringBuffer(matcher.group());
		}
		if(date == null)
			return null;
		String[] splits = date.toString().split("\\s")[0].split("-");
		if(splits[1].length()==1){
			date.insert(date.indexOf("-")+1, 0);
		}
		if(splits[2].length()==1){
			date.insert(date.lastIndexOf("-")+1, 0);
		}
		if(date.toString().matches("\\d{4}\\-?\\d*\\-?\\d*")){
			date.append(" 00:00:00");
		}else if(date.toString().matches("\\d{4}\\-?\\d*\\-?\\d*\\s*\\d{2}\\:\\d{2}")){
			date.append(":00");
		}
		return date.toString();
	}
	/**
	 * 提取地址，地址为来源：开头的地址
	 * @param str
	 * @return
	 */
	public static String getAddress(String str){
		if(str == null || str == ""){
			return null;
		}
		String reg = "来源(：|:) ?\\S*";
		Pattern pattern = Pattern.compile (reg);
		Matcher matcher = pattern.matcher(str);
		String address = null;
		while(matcher.find()){
			address = matcher.group();
		}
		if(address!=null)
			address = address.substring(3).trim();
		return address;
	}
	/**
	 * 转换时间：将几秒前、几分钱、几天前转为时间
	 * @param str
	 * @return
	 */
	public static String getDateBefore(String str){
		String reg = "\\d+((秒前)|(分钟前)|(小时前)|(天前))";
		Pattern pattern = Pattern.compile (reg);
		Matcher matcher = pattern.matcher(str);
		String releasedate = null;
		while(matcher.find()){
			releasedate = matcher.group();
		}
		if(releasedate!=null){
			int num = Integer.valueOf(releasedate.replaceAll("(秒前)|(分钟前)|(小时前)|(天前)", ""));
			Calendar cal = Calendar.getInstance();
			if(releasedate.contains("秒前")){
				cal.add(Calendar.SECOND, -num);
			}else if(releasedate.contains("分钟前")){
				cal.add(Calendar.MINUTE, -num);
			}else if(releasedate.contains("小时前")){
				cal.add(Calendar.HOUR_OF_DAY, -num);
			}else if(releasedate.contains("天前")){
				cal.add(Calendar.DAY_OF_MONTH, -num);
			}
			
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		}
		return "";
	}
	
	/**
	 * 匹配url是否符合我们爬取时候的地址
	 * @param url
	 * @return
	 */
	public static boolean MatchUrl(String url){
		String pattern = "http://.*";
		return url.matches(pattern);
	}
	public static void main(String[] args) {
		System.out.println("http://www.371zy.com/zfxm/yzxm/7558.html".matches("http://www.371zy.com/.*"));
	}
	
}

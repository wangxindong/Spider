package com.government.spider.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 对数据单位价格以及单位进行统一
 * @author dell
 *
 */
public class PatternUtil {

	/**
	 * 提取数字
	 */
	public static int getNum(String str){
		int num = 0;
		int number = 0;
		List<String> numList = new ArrayList<String>();
		String reg = "\\d*";
		int i = 0;
		if(str == null || str == ""){//没有出入参数
			return num;
		}else if(str.indexOf("-") != -1 && str.indexOf("/") != -1){//例如:98-99元/30【跌1】
			return num;
		}else if(str.indexOf("/") != -1 && str.indexOf("-") == -1 && str.indexOf("公斤") == -1){//例如:148元/45【跌5】
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(str);
			System.out.println(str+","+matcher.groupCount());
			while(matcher.find()){
				numList.set(i, matcher.group());
			}
			if(numList.size() != 0 && Integer.valueOf(numList.get(1)) != 0){
				num = Integer.valueOf(numList.get(0)) / Integer.valueOf(numList.get(1));
			}
		}else if(str.indexOf("公斤") != -1){//例如:7.00元/公斤
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				if(!matcher.group().equals("")){
					number = Integer.valueOf(matcher.group()) ;
					System.out.println(matcher.group());
				}
//				numList.set(i++,matcher.group()); 
			}
				num =Integer.valueOf(number) / 2;
		}
		return num;
	}
	
	public static void main(String[] args){
		int str = getNum("山东平原鸡蛋价格：150元/公斤");
		String a = "山东平原鸡蛋价格：150元/公斤";
		System.out.println(a.replaceAll("150", String.valueOf(str)));
	}
}

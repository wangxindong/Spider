package com.zel.vaolan.manager.extractor;
 
import java.util.List;

import com.zel.vaolan.impl.KeywordExtractorImpl;
import com.zel.vaolan.manager.analyzer.SkyLightAnalyzerManager;
import com.zel.vaolan.utils.IOUtil;
import com.zel.vaolan.utils.StaticValue4Keyword;

/** 
 * 关键词抽取管理器
 * 
 * @author zel
 */
public class KeywordExtManager {
	private SkyLightAnalyzerManager skyLightAnalyzerManager;
	private KeywordExtractorImpl keywordExtractorImpl;

	public KeywordExtManager() {
		skyLightAnalyzerManager = new SkyLightAnalyzerManager();
		keywordExtractorImpl = new KeywordExtractorImpl(skyLightAnalyzerManager);
	}

	// 得到一般性的关键词
	public List<String> getNormalKeyword(String source) {
		return keywordExtractorImpl.getNormalKeyword(source);
	}

	// 得到主题词
	public List<String> getThemeKeyword(String source) {
		return keywordExtractorImpl.getThemeKeyword(source);
	}
  
	public static void main(String[] args) {
		KeywordExtManager keywordExtManager = new KeywordExtManager();
		String source = null;

//		 source = "爸爸去哪儿这个节目不错!";
//		 source = "马英九这人挺不错的";
		 source = "今日全国生猪均价下跌地区较多，上涨地区较少。幅度在-0.40至0.45元/公斤之间。"
		 		+ "今日全国生猪平均价格为17.50元/公斤，较昨日猪价下跌0.15元/公斤，较上周猪价下跌0.73元/公斤，"
		 		+ "陕西省、湖北省、北京市、海南省、吉林省和贵州省上涨，其余各省市均出现一定幅度的下跌或持平;涨幅维持在0.02-0.45元/公斤，"
		 		+ "跌幅维持在0.03-0.40元/公斤。";
//		 source = "结婚的和尚和尚未结婚的和尚!";
		String filePath = "d:/Noname5.txt";

		source = IOUtil
				.readFile(filePath, StaticValue4Keyword.default_encoding);

		// List<String> keywordList = keywordExtManager.getThemeKeyword(source);
		List<String> keywordList = keywordExtManager.getNormalKeyword(source);

		System.out.println(keywordList);
	}
}

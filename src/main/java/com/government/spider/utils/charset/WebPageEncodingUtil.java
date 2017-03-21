package com.government.spider.utils.charset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.government.spider.utils.StaticValue;
import com.government.spider.utils.StringOperatorUtil;
import com.vaolan.extkey.utils.RegexParserUtil;
import com.vaolan.parser.JsoupHtmlParser;
import com.vaolan.status.DataFormatStatus;


/**
 * 网页编页工具类
 * 
 * @author zel
 * 
 */
public class WebPageEncodingUtil {
	public static String content_type_charset_begin = "charset=";
	public static String content_type_charset_end = "[\\s]*";
	public static RegexParserUtil regexParserUtil = new RegexParserUtil(
			content_type_charset_begin, content_type_charset_end,
			RegexParserUtil.zel_all_chars);

	public static String getContentTypeCharset(String cotent_type_header) {
		if (StringOperatorUtil.isBlank(cotent_type_header)) {
			return null;
		}
		regexParserUtil.reset(cotent_type_header);
		return regexParserUtil.getText();
	}

	public static String getPageSourceCharset(String pageSource) {
		if (pageSource == null || pageSource.isEmpty()) {
			return null;
		}
		String meta_string=JsoupHtmlParser.getTagContent(pageSource, "meta",
				DataFormatStatus.TagAllContent);
		return getCharset(meta_string);
	}

	private static String getCharset(String metaString) {
		if (StringOperatorUtil.isBlank(metaString)) {
			return null;
		}
		StringReader sr = new StringReader(metaString);
		BufferedReader br = new BufferedReader(sr);
		String temp_line = null;
		try {
			while ((temp_line = br.readLine()) != null) {
				temp_line = temp_line.toLowerCase();
				if (temp_line.contains("<meta")
						&& temp_line.contains("charset")) {
					if (temp_line.contains(StaticValue.default_encoding)) {
						return StaticValue.default_encoding;
					} else if (temp_line.contains(StaticValue.gb2312_encoding)) {
						return StaticValue.gb2312_encoding;
					} else if (temp_line.contains(StaticValue.gbk_encoding)) {
						return StaticValue.gbk_encoding;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// String cotent_type_header = "text/html;charset=";
		// System.out.println(WebPageEncodingUtil
		// .getContentTypeCharset(cotent_type_header));

		String meta = "<html><meta charset='utf-8'></html>";
		WebPageEncodingUtil.getPageSourceCharset(meta);

	}
}

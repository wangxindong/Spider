package com.jianong.base;

public interface GovernmentConst {

	enum GovernmentConstEnum implements GovernmentConst {
		DEFAULT_SOLR_FIELD
	}

	/**
	 * crawl_data数据表
	 */
	public final String CRAWL_DATA_TABLE = "-1";

	/**
	 * 农业部
	 */
	public final String NONGYEBU = "0";

	/**
	 * 农业科普
	 */
	public final String NONGYE_INFO = "2";

	/**
	 * 农业新闻
	 */
	public final String NONGYE_NEWS = "3";

	/**
	 * 猪业
	 */
	public final String SUBJECT_PIG = "4";

	/**
	 * 禽业
	 */
	public final String SUBJECT_POULTRY = "5";

	/**
	 * 畜牧
	 */
	public final String SUBJECT_FARMING = "6";

	/**
	 * 增加两个表选择参数
	 */
	public final String TABLE_GOVERNMENT = "7";

	public final String TABLE_CRAWL_DATA = "8";

	/**
	 * 默认排序字段
	 */
	public final String DEFAULT_SOLR_FIELD = "releasedate";

	public final int BEGIN = 1;

	public final int END = 2;
	
	public final String TABLE_GOVERNMENT_DATA_KEY = "government_data";
	
	public final String TABLE_CRAWL_DATA_KEY = "crawl_data";

}

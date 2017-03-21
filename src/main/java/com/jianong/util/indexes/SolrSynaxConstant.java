package com.jianong.util.indexes;

/**
 * solr常用表达式
 *
 * Created with IntelliJ IDEA. User: wangxindong Date: 2017/1/22 Time: 22:01
 */
public interface SolrSynaxConstant {
	
	String SPACE = " ";

	/**
	 * 组查询进一步限制
	 */
	String GROUP_QUERY_PARAM = "group.query";
	/**
	 * 组查询定义排序条件
	 */
	String GROUP_SORT_PARAM = "group.sort";
	/**
	 * 组查询定义相应的数目
	 */
	String GROUP_LIMIT_PARAM = "group.limit";
	/**
	 * 组查询定义分组列
	 */
	String GROUP_FIELD_PARAM = "group.field";
	/**
	 * 组查询参数
	 */
	String GROUP_PARAM = "group";
	/**
	 * 所要统计的列
	 */
	String STATS_FIELD_PARAM = "stats.field";
	/**
	 * 统计参数
	 */
	String STATS_PARAM = "stats";
	/**
	 * SOLR打分字段
	 */
	String SCORE_FIELD = " score ";
	/**
	 * 匹配任意字符
	 */
	String ANY = "*";
	/**
	 * 或的表达式
	 */
	String OR = " || ";
	/**
	 * 且表达式
	 */
	String AND = " && ";
	/**
	 * 不等表达式
	 */
	String NOT = " NOT ";
	/**
	 * 匹配表达式
	 */
	String MATCH = ":";
	/**
	 * 区间中的TO
	 */
	String TO = " TO ";
	/**
	 * 中括号左边
	 */
	String BRACKETS_START = " [ ";
	/**
	 * 中括号右边
	 */
	String BRACKETS_END = " ] ";
	/**
	 * 真
	 */
	String TRUE = "true";

	/**
	 * 字段值为空
	 */
	String FILED_VALUE_NULL = "-";

	/**
	 * 过滤查询
	 */
	String FILTER_QUERY = "fq";

	/**
	 * 指定返回字段
	 */
	String FIELD_LIST = "fl";

	/**
	 * 返回格式
	 */
	String WRITER_TYPE = "wq";

	/**
	 * 查询
	 */
	String QUERY = "q";

	/**
	 * 关系
	 */
	String Q_OP = "q.op";

	String AND_OP = "AND";

	String OR_OP = "OR";

	String NOT_OP = "NOT";
	
	String TERMFREQ = "termfreq";
	
	String SORT = "sort";

}

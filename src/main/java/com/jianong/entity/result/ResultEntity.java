/**  
 * @Title: ResultEntity.java
 * @Prject: JIANONG
 * @Package: com.jianong.entity.result
 * @Description: 
 * @author: CHIENJUN
 * @date: 2016年12月6日 下午4:50:08
 * @version: V1.0  
 */
package com.jianong.entity.result;

/**
 * @ClassName: ResultEntity
 * @Description: 返回结果集
 * @author: CHIENJUN
 * @date: 2016年12月6日 下午4:50:08
 */
public class ResultEntity {
	// 提示语
	private String result_msg;
	// 细表返回结果
	private Object dataSet_fine;
	// 返回状态码
	private String result_code;
	// 返回结果
	private Object dataSet;
	// 账号id
	private String accountId;
	// 账号名
	private String accountname;
	// 总页数
	private String pageCount;
	// 当前页码
	private String page;
	// 查询关键词
	private String query;

	/**
	 * @return 获得 query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            设置 query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return 获得 提示语
	 */
	public String getResult_msg() {
		return result_msg;
	}

	/**
	 * @param result_msg
	 *            设置 result_msg to 提示语
	 */
	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	/**
	 * @return 获得 细表返回结果
	 */
	public Object getDataSet_fine() {
		return dataSet_fine;
	}

	/**
	 * @param dataSet_fine
	 *            设置 dataSet_fine to 细表返回结果
	 */
	public void setDataSet_fine(Object dataSet_fine) {
		this.dataSet_fine = dataSet_fine;
	}

	/**
	 * @return 获得 返回状态码
	 */
	public String getResult_code() {
		return result_code;
	}

	/**
	 * @param result_code
	 *            设置 result_code to 返回状态码
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	/**
	 * @return 获得 返回结果
	 */
	public Object getDataSet() {
		return dataSet;
	}

	/**
	 * @param dataSet
	 *            设置 dataSet to 返回结果
	 */
	public void setDataSet(Object dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * @return 获得 账号id
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            设置 accountId to 账号id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return 获得 账号名
	 */
	public String getAccountname() {
		return accountname;
	}

	/**
	 * @param accountname
	 *            设置 accountname to 账号名
	 */
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	/**
	 * @return 获得 总页数
	 */
	public String getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            设置 pageCount to 总页数
	 */
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return 获得 当前页码
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            设置 page to 当前页码
	 */
	public void setPage(String page) {
		this.page = page;
	}

}

package com.government.spider.strategy;

import java.util.Date;

public class NewsContent {
	/**
	 * id
	 */
	private String id;
	/**
	 * 页面地址
	 */
	private String oriurl;
	/**
	 * 文章地址
	 */
	private String urladdress;
	/**
	 * 页面标题
	 */
	private String title;
	/**
	 * 发布时间
	 */
	private String releasedate;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 主题词
	 */
	private String subjectword;
	/**
	 * 权重
	 */
	private String weight;
	/**
	 * 正文
	 */
	private String text;
	/**
	 * 带页面标签的正文
	 */
	private String labeltext;
	/**
	 * 附件名称
	 */
	private String sourcename;
	/**
	 * 附件地址
	 */
	private String sourceurl;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 地区
	 */
	private String place;
	/**
	 * 备注
	 */
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriurl() {
		return oriurl;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSubjectword() {
		return subjectword;
	}

	public void setSubjectword(String subjectword) {
		this.subjectword = subjectword;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLabeltext() {
		return labeltext;
	}

	public void setLabeltext(String labeltext) {
		this.labeltext = labeltext;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUrladdress() {
		return urladdress;
	}

	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}

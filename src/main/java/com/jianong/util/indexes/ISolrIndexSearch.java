package com.jianong.util.indexes;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import com.jianong.entity.Page;
import com.jianong.util.PageData;

/**
 * solr查询接口
 * 
 * @ClassName: ISolrIndexSearch
 * @Description:
 * @author: wxd
 * @date: 2017年1月22日 下午3:39:36
 */
public interface ISolrIndexSearch extends IndexSearch {

	public SolrQuery getQuery() throws Exception;

	public Map<String, Object> search(Page page, SolrQuery query, boolean isHight) throws Exception;
	
	public List<PageData> search(PageData pd, SolrQuery query) throws Exception;
	
	/**
	 * 统计
	 * @Title: statisticData
	 * @Description: 
	 * @return
	 * @throws Exception
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> statisticData(SolrQuery query) throws Exception;
	
	public List<PageData> statisticData(PageData pd, SolrQuery query) throws Exception;
	
	

}

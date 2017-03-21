package com.jianong.util.indexes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jianong.entity.Page;
import com.jianong.util.Const;
import com.jianong.util.DateUtil;
import com.jianong.util.Logger;
import com.jianong.util.PageData;

@SuppressWarnings("all")
public class SolrIndexSearch implements ISolrIndexSearch {
	private static final Logger LOGGER = Logger.getLogger(SolrIndexSearch.class);

	private SolrQuery query;
	private HttpSolrClient solr;

	public SolrIndexSearch() {
		init();
	}

	private void init() {
		solr = SolrIndexFactory.getInstance().connect();
	}

	private SolrQuery setHighlightHandle(SolrQuery query) {
		query.setHighlight(true);
		query.addHighlightField("title");
		query.addHighlightField("text");
		query.setHighlightSimplePre("<span class=\"" + Const.HIGHLIGHT_CLASS + "\">");
		query.setHighlightSimplePost("</span>");
		return query;
	}

	private List<PageData> gethighlighDataList(QueryResponse response) {
		List<PageData> dataList = Lists.newArrayList();
		// 获取高亮字段
		Map<String, Map<String, List<String>>> hightMap = response.getHighlighting();
		SolrDocumentList results = response.getResults();
		Iterator<SolrDocument> it = results.iterator();
		while (it.hasNext()) {
			SolrDocument document = it.next();
			String id = document.getFieldValue("id").toString();
			String title = document.getFieldValue("title").toString();
			List<String> titleHight = hightMap.get(id).get("title");
			List<String> textHight = hightMap.get(id).get("text");
			if (titleHight != null && titleHight.size() != 0) {
				document.setField("title", titleHight.get(0));
			}
			if (textHight != null && textHight.size() != 0) {
				document.setField("text", textHight.get(0));
			}

			PageData pds = solrDocumentToPageData(document);
			dataList.add(pds);
		}
		return dataList;
	}
	
	private SolrQuery setSortQuery(SolrQuery query) {
		query.set(SolrSynaxConstant.SORT, "score desc ,releasedate desc");
		return query;
	}

	/**
	 * 逻辑层传入查询条件
	 * 
	 * @Title: search
	 * @Description:
	 * @param search
	 * @param query
	 * @param isHight
	 * @return
	 * @throws Exception
	 * @see com.jianong.util.indexes.ISolrIndexSearch#search(com.jianong.entity.SolrDocumentParameter,
	 *      org.apache.solr.client.solrj.SolrQuery, boolean)
	 */
	@Override
	public Map<String, Object> search(Page page, SolrQuery query, boolean isHight) throws Exception {
		Map<String, Object> dataMap = new HashMap<>();
		PageData pd = page.getPd();
		query = setSortQuery(query);
		int showCount = 0;
		int currentPage = 0;
		int totalResult = 0;

		if (null != pd.getString("currentPage")) {
			currentPage = Integer.parseInt(pd.getString("currentPage"));
		}
		if (currentPage == 0) {
			currentPage = 1;
		}
		if (null != pd.getString("showCount")) {
			showCount = Integer.parseInt(pd.getString("showCount"));
		} else {
			showCount = Const.SHOW_COUNT;
		}
		query.setStart(showCount * (currentPage - 1)).setRows(showCount);
		List<PageData> dataList = Lists.newArrayList();

		if (isHight) {
			query = setHighlightHandle(query);
			QueryResponse response = solr.query(query);
			dataList = gethighlighDataList(response);
			totalResult = (int) response.getResults().getNumFound();
			LOGGER.info("查询参数：" + query + "共查询到文档：" + totalResult + "个" + "用时：" + response.getQTime());
		} else {
			QueryResponse response = solr.query(query);
			SolrDocumentList results = response.getResults();
			dataList = solrDocumentToList(results);
			totalResult = (int) results.getNumFound();
			LOGGER.info("查询参数：" + query + "共查询到文档：" + totalResult + "个" + "用时：" + response.getQTime());
		}

		dataMap.put("dataList", dataList);
		// 构造page
		page.setCurrentPage(currentPage);
		page.setShowCount(showCount);
		page.setTotalResult(totalResult);
		dataMap.put("page", makePage(page));
		return dataMap;
	}

	private Page makePage(Page page) {
		page.getTotalPage();
		page.setEntityOrField(true);
		page.getPageStr();
		return page;
	}

	private PageData solrDocumentToPageData(SolrDocument document) {
		PageData pd = new PageData();
		Iterator<Entry<String, Object>> it = document.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			// 时间
			if (entry.getKey().equals("releasedate")) {
				pd.put(entry.getKey(), DateUtil.getDateTimeFromTimeStrap(Long.parseLong(entry.getValue().toString())));
			} else {
				pd.put(entry.getKey(), entry.getValue());
			}
		}
		return pd;
	}

	/**
	 * 根据id查询数据
	 * 
	 * @Title: search
	 * @Description:
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.jianong.util.indexes.ISolrIndexSearch#search(java.lang.String)
	 */
	@Override
	public PageData search(String id) throws Exception {
		PageData pd = new PageData();
		SolrQuery query = new SolrQuery();
		query.setQuery(SolrStatementUtils.generateBaseMatchStatement("id", id));
		query = setHighlightHandle(query);
		QueryResponse response = solr.query(query);
		List<PageData> datas = gethighlighDataList(response);
		LOGGER.info("查询参数：" + query + "用时：" + response.getQTime());
		return datas.get(0);
	}

	/**
	 * solrdocuemnt转list
	 * 
	 * @Title: solrDocumentToList
	 * @Description:
	 * @param result
	 * @return
	 * @return: List<PageData>
	 */
	private List<PageData> solrDocumentToList(SolrDocumentList result) {
		List<PageData> dataList = new ArrayList<>();
		Iterator<SolrDocument> it = result.iterator();
		while (it.hasNext()) {
			SolrDocument solrDocument = (SolrDocument) it.next();
			PageData pd = new PageData();
			for (String key : solrDocument.keySet()) {
				if (key.equals("releasedate")) {
					pd.put(key, DateUtil.getDateTimeFromTimeStrap(Long.parseLong(solrDocument.get(key).toString())));
				} else {
					pd.put(key, solrDocument.get(key));
				}
			}
			dataList.add(pd);
		}
		return dataList;
	}

	@Override
	public SolrQuery getQuery() throws Exception {
		return query;
	}

	/**
	 * 统计
	 * 
	 * @Title: statisticData
	 * @Description:
	 * @param query
	 * @return
	 * @throws Exception
	 * @see com.jianong.util.indexes.ISolrIndexSearch#statisticData(org.apache.solr.client.solrj.SolrQuery)
	 */
	@Override
	public Map<String, Object> statisticData(SolrQuery query) throws Exception {
		Map<String, Object> dataMap = Maps.newHashMap();
		QueryResponse response = solr.query(query);
		List<FacetField> facetFields = response.getFacetFields();
		for (FacetField face : facetFields) {
			List<Count> counts = face.getValues();
			for (Count count : counts) {
				dataMap.put(count.getName(), count.getCount());
			}
		}
		return dataMap;
	}

	@Override
	public List<PageData> statisticData(PageData pd, SolrQuery query) throws Exception {
		List<PageData> dataList = Lists.newArrayList();
		QueryResponse response = solr.query(query);
		List<FacetField> facetFields = response.getFacetFields();
		for (FacetField face : facetFields) {
			List<Count> counts = face.getValues();
			for (Count count : counts) {
				PageData pds = new PageData();
				pds.put("name", count.getName());
				pds.put("value", count.getCount());
				dataList.add(pds);
			}
		}
		return dataList;
	}

	/**
	 * 不需要返回page的查询
	 * 
	 * @Title: search
	 * @Description:
	 * @param pd
	 * @param query
	 * @return
	 * @throws Exception
	 * @see com.jianong.util.indexes.ISolrIndexSearch#search(com.jianong.util.PageData,
	 *      org.apache.solr.client.solrj.SolrQuery)
	 */
	@Override
	public List<PageData> search(PageData pd, SolrQuery query) throws Exception {
		List<PageData> dataList = Lists.newArrayList();
		Map<String, Object> dataMap = new HashMap<>();
		QueryResponse response = solr.query(query);
		SolrDocumentList results = response.getResults();
		dataList = solrDocumentToList(results);
		return dataList;
	}

}

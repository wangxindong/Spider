package com.jianong.util.indexes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.jianong.util.Logger;
import com.jianong.util.PageData;

@SuppressWarnings("all")
public class SolrIndexWriter implements IndexeWriter {
	private static final Logger LOGGER = Logger.getLogger(SolrIndexWriter.class);

	// private static HttpSolrClient solr;
	private static ConcurrentUpdateSolrClient solr;

	@Override
	public void open() {
		solr = SolrIndexFactory.getInstance().getConcurrentUpdateSolrClient();
	}

	@Override
	public void delete(List<String> ids) throws Exception {
		solr.deleteById(ids);
		solr.commit();
	}

	@Override
	public void delete(String id) throws Exception {
		UpdateResponse response = solr.deleteById(id);
		solr.commit();
		LOGGER.info("删除文档id:" + id + "用时：" + response.getQTime() + "状态：" + response.getStatus());
	}

	public void write(List<PageData> document) throws Exception {
		UpdateResponse response = null;
		for (PageData pd : document) {
			SolrInputDocument d = new SolrInputDocument();
			Iterator it = pd.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (!Objects.equals(key, "_version_")) {
					d.setField(key, pd.get(key));
				}
			}
			UpdateRequest request = new UpdateRequest();
			request.setAction(ACTION.COMMIT, false, false);
			request.add(d);
			response = request.process(solr);
		}
		LOGGER.info("写入文档个数:" + document.size() + "用时：" + response.getQTime() + "状态：" + response.getStatus());
	}

	@Override
	public void write(PageData pd) throws Exception {
		SolrInputDocument d = new SolrInputDocument();
		Iterator it = pd.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (!Objects.equals(key, "_version_")) {
				d.setField(key, pd.get(key));
			}
		}
		UpdateRequest request = new UpdateRequest();
		request.setAction(ACTION.COMMIT, false, false);
		request.add(d);
		UpdateResponse response = request.process(solr);
	}

	@Override
	public void commit() {
		try {
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			solr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PageData document) throws Exception {
		List<PageData> pds = new ArrayList<>(1);
		pds.add(document);
		write(pds);
	}

	@Override
	public void deleteAll() throws Exception {
		UpdateResponse response = solr.deleteByQuery("*:*");
		solr.commit();
		LOGGER.info("删除全部文档" + response.getStatus() + "用时：" + response.getQTime());
	}

	/**
	 * 传入开始时间创建索引
	 * 
	 * @Title: createIndex
	 * @Description:
	 * @param beginTime
	 * @throws Exception
	 * @see com.jianong.util.indexes.IndexeWriter#createIndex(java.lang.String)
	 */
	@Override
	public void createIndex(String beginTime) throws Exception {

	}

	public static void main(String[] args) throws Exception {
		SolrIndexWriter solr = new SolrIndexWriter();
		solr.open();
		PageData pd = new PageData();
		pd.put("id", "4444444444444444444444");
		solr.write(pd);
	}

}

package com.jianong.util.indexes;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public class SolrIndexFactory {
	private static String url;
	private static String timeout;
	private static String maxConnection;
	private static String queueSize;
	private static IndexeWriter indexeWriter;
	private static ISolrIndexSearch indexSearch;
	private ConcurrentUpdateSolrClient concurrentUpdateSolrClient;

	private static SolrIndexFactory factory;

	static {
		ResourceBundle resource = ResourceBundle.getBundle("solrindex");
		url = resource.getString("solr.url");
		timeout = resource.getString("solr.timeout");
		maxConnection = resource.getString("solr.maxconnection");
		queueSize = resource.getString("solr.queuesize");
	}

	private SolrIndexFactory() {

	}

	public static ISolrIndexSearch getIndexSearch() {
		if (null == indexSearch) {
			synchronized (SolrIndexFactory.class) {
				if(null == indexSearch){
					indexSearch = new SolrIndexSearch();
				}
			}
		}
		return indexSearch;
	}

	public static synchronized IndexeWriter getIndexWriter() {
		if (null == indexeWriter) {
			indexeWriter = new SolrIndexWriter();
		}
		return indexeWriter;
	}

	public static synchronized SolrIndexFactory getInstance() {
		if (null == factory) {
			factory = new SolrIndexFactory();
		}
		return factory;
	}

	/**
	 * 
	 * @Title: getConcurrentUpdateSolrClient
	 * @Description:
	 * @return
	 * @return: ConcurrentUpdateSolrClient
	 */
	public ConcurrentUpdateSolrClient getConcurrentUpdateSolrClient() {
		concurrentUpdateSolrClient = new ConcurrentUpdateSolrClient.Builder(url)
				.withQueueSize(Integer.parseInt(queueSize)).build();
		concurrentUpdateSolrClient.setParser(new XMLResponseParser());
		concurrentUpdateSolrClient.setConnectionTimeout(Integer.parseInt(timeout));
		concurrentUpdateSolrClient.setRequestWriter(new BinaryRequestWriter());
		return concurrentUpdateSolrClient;
	}

	public CloudSolrClient getCloudSolrClient() {
		CloudSolrClient client = new CloudSolrClient.Builder().withZkHost(url).build();
		client.setDefaultCollection("collection1");
		client.setParser(new XMLResponseParser());
		client.setRequestWriter(new BinaryRequestWriter());
		return (CloudSolrClient) client;
	}

	public HttpSolrClient connect() {
		HttpSolrClient solr = new HttpSolrClient.Builder(url).build();
		solr.setAllowCompression(false);
		solr.setParser(new XMLResponseParser());
		solr.setRequestWriter(new BinaryRequestWriter());
		solr.setConnectionTimeout(Integer.parseInt(timeout));
		solr.setSoTimeout(100000);
		solr.setDefaultMaxConnectionsPerHost(Integer.parseInt(maxConnection));
		return solr;
	}

	public static void close(SolrClient client) {
		try {
			if (null != client) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static SolrQuery getQuery() {
		SolrQuery query = new SolrQuery();
		return query;
	}
}

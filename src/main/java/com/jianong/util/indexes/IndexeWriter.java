package com.jianong.util.indexes;

import java.util.List;

import com.jianong.util.PageData;

/**
 * 索引操作接口
 * 
 * @ClassName: Indexes
 * @Description:
 * @author: wxd
 * @date: 2017年1月22日 下午1:18:47
 */
public interface IndexeWriter {

	void open();

	void delete(List<String> ids) throws Exception;

	void delete(String id) throws Exception;

	void deleteAll() throws Exception;

	void update(PageData document) throws Exception;

	void write(List<PageData> document) throws Exception;

	void write(PageData pd) throws Exception;

	/**
	 * 创建索引
	 * 
	 * @Title: createIndex
	 * @Description:
	 * @param pd
	 * @throws Exception
	 * @return: void
	 */
	void createIndex(String beginTime) throws Exception;

	void commit();

	void close();

}

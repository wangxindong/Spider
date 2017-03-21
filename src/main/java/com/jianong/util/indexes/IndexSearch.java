package com.jianong.util.indexes;

import com.jianong.util.PageData;

/**
 * 索引查询
 * 
 * @ClassName: IndexSearch
 * @Description:
 * @author: wxd
 * @date: 2017年1月22日 下午2:30:30
 */
public interface IndexSearch {
	
	/**
	 * 根据id查询内容
	 * 
	 * @Title: search
	 * @Description:
	 * @param id
	 * @return
	 * @throws Exception
	 * @return: PageData
	 */
	public PageData search(String id) throws Exception;

}

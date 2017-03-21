/**  
 * @Title: Result.java
 * @Prject: JIANONG
 * @Package: com.jianong.util
 * @Description: TODO
 * @author: CHIENJUN
 * @date: 2016年12月6日 上午11:26:23
 * @version: V1.0  
 */
package com.jianong.util;

import java.util.HashMap;
import java.util.Map;

import com.jianong.entity.result.ResultEntity;

/**
 * @ClassName: Result
 * @Description: 返回的结果集
 * @author: CHIENJUN
 * @date: 2016年12月6日 上午11:26:23
 */
public class Result {
	/**
	 * @Title: getResult
	 * @Description: 获得返回结果
	 * @param resultEntity
	 * @return
	 * @return: Map<String,Object>
	 */
	public static Map<String, Object> getResult(ResultEntity resultEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result_msg", resultEntity.getResult_msg());
		map.put("dataSet_fine", resultEntity.getDataSet_fine());
		map.put("result_code", resultEntity.getResult_code());
		map.put("dataSet", resultEntity.getDataSet());
		map.put("accountId", resultEntity.getAccountId());
		map.put("accountname", resultEntity.getAccountname());
		map.put("pageCount", resultEntity.getPageCount());
		map.put("page", resultEntity.getPage());
		map.put("query", resultEntity.getQuery());
		return map;
	}
}

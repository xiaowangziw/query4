package com.pppcar.service;

import java.util.HashMap;
import java.util.List;

import com.pppcar.pojo.Order;
import com.pppcar.pojo.OrderDetailSearchArgs;
import com.pppcar.pojo.Page;
import com.pppcar.pojo.Province;

public interface QueryService {

	/**
	 * 条件分页查询
	 * @param start
	 * @param pageSize
	 * @param province
	 * @param city
	 * @param salesman
	 * @param classification
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Page<Order> queryOrderDetails(Integer start,Integer pageSize,HashMap<String, Object> orderDetailSearchArgs);

	/**
	 * 获取订单表中所有销售员的信息
	 * @return
	 */
	List<Order> getAllSalesman();

	/**
	 * 获取订单表中所有品类信息
	 * @return
	 */
	List<Order> getAllClassifications();
	
	/**
	 * 下载数据
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Order> queryAndDownload(HashMap<String, Object> paramMap);

}

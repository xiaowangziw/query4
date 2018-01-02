package com.pppcar.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pppcar.pojo.Classification;
import com.pppcar.pojo.Order;
import com.pppcar.pojo.OrderDetailSearchArgs;
import com.pppcar.pojo.Page;

public interface QueryDao {

//	List<Order> queryOrderDetails(@Param("start")Integer start,@Param("pageSize")Integer pageSize, 
//			@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("salesmanId")String salesmanId,
//			@Param("classificationId")String classificationId,@Param("startTime")String startTime,@Param("endTime")String endTime);
	/**
	 * 根据条件查询数据
	 * @param orderDetailSearchArgs
	 * @return
	 */
List<Order> queryOrderDetails(HashMap<String, Object> orderDetailSearchArgs);

	/**
	 * 查询数据总条数
	 * @param orderDetailSearchArgs
	 * @return
	 */
	int queryCount(HashMap<String, Object> orderDetailSearchArgs);
	/**
	 * 获取订单表中所有销售员信息
	 * @return
	 */
	List<Order> getAllSalesman();

	/**
	 * 获取订单表中所有品类信息
	 * @return
	 */
	List<Order> getAllClassifications();

	/**
	 * 根据二级品类id获取一级品类信息
	 * @param classificationId2
	 * @return
	 */
	Classification getParentidByClassificationId(@Param("classificationId")Integer classificationId);

}

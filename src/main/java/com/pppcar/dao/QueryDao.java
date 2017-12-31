package com.pppcar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pppcar.pojo.Classification;
import com.pppcar.pojo.Order;
import com.pppcar.pojo.Page;

public interface QueryDao {

	List<Order> queryOrderDetails(@Param("start")Integer start,@Param("pageSize")Integer pageSize, 
			@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("salesmanId")String salesmanId,
			@Param("classificationId")String classificationId,@Param("startTime")String startTime,@Param("endTime")String endTime);
//	List<Order> queryOrderDetails(@Param("start")Integer start,@Param("pageSize")Integer pageSize, @Param("orderDetailSearchArgs")OrderDetailSearchArgs orderDetailSearchArgs);

	int queryCount(@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("salesmanId")String salesmanId,
			@Param("classificationId")String classificationId,@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 获取订单表中所有销售员信息
	 * @return
	 */
	List<Order> getAllSalesman();

	List<Order> getAllClassifications();

	/**
	 * 根据二级品类id获取一级品类信息
	 * @param classificationId2
	 * @return
	 */
	Classification getParentidByClassificationId(@Param("classificationId")Integer classificationId);

}

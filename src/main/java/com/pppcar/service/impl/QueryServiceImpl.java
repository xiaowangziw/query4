package com.pppcar.service.impl;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pppcar.dao.QueryDao;
import com.pppcar.pojo.Classification;
import com.pppcar.pojo.Order;
import com.pppcar.pojo.OrderDetailSearchArgs;
import com.pppcar.pojo.Page;
import com.pppcar.pojo.Province;
import com.pppcar.service.QueryService;

@Service("queryService")
public class QueryServiceImpl implements QueryService {
	
	@Autowired
	QueryDao queryDao;

	public Page<Order> queryOrderDetails(Integer start,Integer pageSize, HashMap<String, Object> orderDetailSearchArgs) {
//		public Page<Order> queryOrderDetails(Integer start,Integer pageSize, String provinceId,String cityId,String salesmanId,String classificationId,String startTime,String endTime) {
		
		if(start == null ){
    		start = 0;
    		
    	}
    	if(pageSize == null){
    		pageSize = 20;
    	}
    	
    	int orderCount = queryDao.queryCount(orderDetailSearchArgs);
    	
    	orderDetailSearchArgs.put("start", start);
    	orderDetailSearchArgs.put("pageSize", pageSize);
          List<Order> orderList = queryDao.queryOrderDetails(orderDetailSearchArgs);
//          int orderCount = queryDao.queryCount(provinceId, cityId, salesmanId, classificationId, startTime, endTime);
//          List<Order> orderList = queryDao.queryOrderDetails(start,pageSize,provinceId, cityId, salesmanId, classificationId,startTime,endTime);
          //根据查出来的二级品类id classificationId 查处对应的一级品类id和名称
          for (Order order : orderList) {
        	 Integer classificationId2 = order.getClassificationId();
        	 
			System.out.println(classificationId2);
			Classification cl =  queryDao.getParentidByClassificationId(classificationId2);
			if(cl != null){
				order.setClassificationId(cl.getClassificationId());
				order.setClassification(cl.getClassName());
			}
		}
          Page<Order> page = new Page<Order>(orderCount, orderCount/pageSize+1, start, pageSize, orderList);
          
          return page;
	}

	/**
	 * 获取订单表中所有销售员的信息
	 * @return
	 */
	public List<Order> getAllSalesman() {
		
		return queryDao.getAllSalesman();
	}
	
	/**
	 * 获取订单表中所有品类的信息
	 * @return
	 */
	public List<Order> getAllClassifications() {
		
		return queryDao.getAllClassifications();
	}
	
	
	
	
	/**
	 * 下载查询出来的全部数据
	 */
	public List<Order> queryAndDownload(HashMap<String, Object> paramMap) {

		int orderCount = queryDao.queryCount(paramMap);

		paramMap.put("start", 0);
		paramMap.put("pageSize", orderCount);
		List<Order> orderList = queryDao.queryOrderDetails(paramMap);
		// 根据查出来的二级品类id classificationId 查处对应的一级品类id和名称
		for (Order order : orderList) {
			Integer classificationId2 = order.getClassificationId();

			System.out.println(classificationId2);
			Classification cl = queryDao.getParentidByClassificationId(classificationId2);
			if (cl != null) {
				order.setClassificationId(cl.getClassificationId());
				order.setClassification(cl.getClassName());
			}
		}
		return orderList;
	}

}

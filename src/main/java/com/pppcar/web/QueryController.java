package com.pppcar.web;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pppcar.pojo.Order;
import com.pppcar.pojo.OrderDetailSearchArgs;
import com.pppcar.pojo.Page;
import com.pppcar.pojo.PageBean;
import com.pppcar.pojo.Province;
import com.pppcar.service.QueryService;

/**
 * 销售数据统计
 * 
 * @author shining
 *
 */
@Controller
public class QueryController {

	@Autowired
	QueryService queryService;
	
	@RequestMapping(value = "/")
	public String toList() {
		return "query";
	}

	@RequestMapping(value = "/toQuery")
	@ResponseBody
	public PageBean<Order> list(@RequestParam(value = "start", defaultValue = "1")Integer start,@RequestParam(value = "length", defaultValue = "20")Integer pageSize,
			String provinceId,String cityId,String salesmanId,String classificationId,String startTime,String endTime){
		
		Page<Order> orderList = queryService.queryOrderDetails(start,pageSize, provinceId,cityId, salesmanId,classificationId,startTime,endTime);
		return new PageBean<Order>(orderList);
	}
	
	
//	@RequestMapping(value = "/toQuery2")
//	@ResponseBody
//	public String toQuery(String param) {
//		if (param != null) {
//		JSONObject jsonObject = JSONObject.fromObject(param);
//		String province = jsonObject.getString("province");
//		String city = jsonObject.getString("city");
//		String salesman = jsonObject.getString("salesman");
//		String classification = jsonObject.getString("classification");
//		String startTime = jsonObject.getString("startTime");
//		String endTime = jsonObject.getString("endTime");
//		System.out.println(province);
//		System.out.println(city);
//		System.out.println(salesman);
//		System.out.println(classification);
//		System.out.println(startTime);
//		System.out.println(endTime);
//		}
//		return "query";
//	}
	
	
//	@RequestMapping(value = "/toQuery")
//	@ResponseBody
//	public PageBean<Order> list(@RequestParam(value = "start", defaultValue = "1")Integer start,@RequestParam(value = "length", defaultValue = "20")Integer pageSize,
//			OrderDetailSearchArgs orderDetailSearchArgs) {
//		
//		Page<Order> orderList = queryService.queryOrderDetails(start,pageSize, orderDetailSearchArgs);
//		return new PageBean<Order>(orderList);
//	}
	
	@RequestMapping(value = "/getSalesmans")
	@ResponseBody
	public List<Order> getSalesman() {
		List<Order> salesmans = queryService.getAllSalesman();
		return salesmans;
	}

}

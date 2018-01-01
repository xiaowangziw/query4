package com.pppcar.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pppcar.pojo.Order;
import com.pppcar.pojo.OrderDetailSearchArgs;
import com.pppcar.pojo.Page;
import com.pppcar.pojo.PageBean;
import com.pppcar.service.QueryService;
import com.pppcar.utils.FileDownload;

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

//	@RequestMapping(value = "/toQuery")
//	@ResponseBody
//	public PageBean<Order> list(@RequestParam(value = "start", defaultValue = "1")Integer start,@RequestParam(value = "length", defaultValue = "20")Integer pageSize,
//			String provinceId,String cityId,String salesmanId,String classificationId,String startTime,String endTime){
//		
//		Page<Order> orderList = queryService.queryOrderDetails(start,pageSize, provinceId,cityId, salesmanId,classificationId,startTime,endTime);
//		return new PageBean<Order>(orderList);
//	}
	
	
//	@RequestMapping(value = "/toQuery2")
//	@ResponseBody
//	public String toQuery(String param) {
//		if (param != null) {
//			OrderDetailSearchArgs orderDetails = JSON.parseObject(param, OrderDetailSearchArgs.class);
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
	
	
	@RequestMapping(value = "/toQuery")
	@ResponseBody
	public PageBean<Order> list(@RequestParam(value = "start", defaultValue = "1")Integer start,@RequestParam(value = "length", defaultValue = "20")Integer pageSize,
			String param ) {
		// 参数
		HashMap<String, Object> paramMap = parseJsonToParam(param);
//		OrderDetailSearchArgs orderDetailSearchArgs = JSON.parseObject(param, OrderDetailSearchArgs.class);
		Page<Order> orderList = queryService.queryOrderDetails(start,pageSize, paramMap);
		return new PageBean<Order>(orderList);
	}
	
	private HashMap<String, Object> parseJsonToParam(String data) {
		
	JSONObject object = JSON.parseObject(data);
	HashMap<String, Object> hashMap = new HashMap<String, Object>();

		//如果第一次加载直接返回
		if (object == null) {
//			默认查询三个月的数据
			 LocalDate now = LocalDate.now();
			 hashMap.put("endTime",Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			 LocalDate minusMonths = now.minusMonths(3);
			 hashMap.put("startTime", Date.from(minusMonths.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			return hashMap;
		}
		hashMap.put("provinceId", object.getString("provinceId"));
		hashMap.put("cityId", object.getString("cityId"));
		hashMap.put("salesmanId", object.getString("salesmanId"));
		hashMap.put("classificationId", object.getString("classificationId"));
		hashMap.put("endTime", object.getString("endTime"));
		
		if(object.getString("startTime") == null || object.getString("startTime") ==""){
			 LocalDate now = LocalDate.now();
			 LocalDate minusMonths = now.minusMonths(3);
			 hashMap.put("startTime", Date.from(minusMonths.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		if(object.getString("endTime") == null || object.getString("endTime") ==""){
			 LocalDate now = LocalDate.now();
			 hashMap.put("endTime", Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		return hashMap;
	}
	
	
	@RequestMapping(value = "/getSalesmans")
	@ResponseBody
	public List<Order> getSalesman() {
		List<Order> salesmans = queryService.getAllSalesman();
		return salesmans;
	}
	
	/**
	 * 导出列表功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/memberInfoDownload")
	public void memberInfoDownload(Order memberInfoBean,HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
			 List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>(); //= queryService.queryAllMemberAndDownload(memberInfoBean);					 
			//编辑工作簿对象
			HSSFWorkbook book = new HSSFWorkbook();
			//创建工作表
			HSSFSheet sheet = book.createSheet("销售数据");
			//excel表格的第一行标题
			HSSFRow first = sheet.createRow(0);
			first.createCell(0).setCellValue("省");
			first.createCell(1).setCellValue("市");
			first.createCell(2).setCellValue("销售员");
			first.createCell(3).setCellValue("品类");
			first.createCell(4).setCellValue("数量");
			first.createCell(5).setCellValue("金额");
			first.createCell(6).setCellValue("开始时间");
			first.createCell(7).setCellValue("截至时间");
			if(queryList.size() > 0){			
				for (Map<String, Object> map : queryList) {
					//循环一次,创建一行
					//首先获取excel表格的最后一行的行号
					int lastRowNum = sheet.getLastRowNum();
					//新创建的一行的行号就是最后一行的基础上加1
					HSSFRow row = sheet.createRow(lastRowNum+1);
					row.createCell(0).setCellValue((String)map.get("province"));
					row.createCell(1).setCellValue((String)map.get("city"));
					row.createCell(2).setCellValue((String)map.get("salesman"));
					row.createCell(3).setCellValue((String)map.get("classification"));
					row.createCell(4).setCellValue((String)map.get("number"));
					row.createCell(5).setCellValue((String)map.get("price"));
					row.createCell(6).setCellValue((String)map.get("startTime"));
					row.createCell(7).setCellValue((String)map.get("endTime"));
					
				}
			}		
			//下载
			FileDownload.DownloadFile("销售数据",request, response, book);
		
	}
	
  

}

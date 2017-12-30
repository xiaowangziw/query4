package com.pppcar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 销售数据统计
 * @author shining
 *
 */
@Controller
public class QueryController {
	
	@RequestMapping(value="/")
	public String toList(){
		
		return "query";
	}

}

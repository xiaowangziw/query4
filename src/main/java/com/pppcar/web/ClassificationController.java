package com.pppcar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pppcar.pojo.City;
import com.pppcar.pojo.Classification;
import com.pppcar.pojo.Province;
import com.pppcar.service.ClassificationService;
import com.pppcar.service.PCDService;

/**
 * 加载省市数据
 * 
 * @author wfx
 *
 */
@Controller
public class ClassificationController {
	@Autowired
	ClassificationService classificationService;

	/**
	 * 加载品类信息
	 * @return
	 */
	@RequestMapping(value = "/getClassifications")
	@ResponseBody
	public List<Classification> getClassification() {
		List<Classification> classifications = classificationService.getAllClassification();
		return classifications;
	}

	

}

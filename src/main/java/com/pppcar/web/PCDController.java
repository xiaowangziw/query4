package com.pppcar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pppcar.pojo.City;
import com.pppcar.pojo.Province;
import com.pppcar.service.PCDService;

/**
 * 加载省市数据
 * 
 * @author wfx
 *
 */
@Controller
public class PCDController {
	@Autowired
	PCDService pcdService;

	@RequestMapping(value = "/getProvinces")
	@ResponseBody
	public List<Province> getProvinces() {
		List<Province> provinces = pcdService.getAllProvince();
		System.out.println(provinces);
		return provinces;
	}

	@RequestMapping(value = "/getCities")
	@ResponseBody
	public List<City> getCities(Integer proid) {
		List<City> cities = pcdService.getAllCityByProvinceId(proid);
		System.out.println(cities);
		return cities;
	}

}

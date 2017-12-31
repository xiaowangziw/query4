package com.pppcar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pppcar.dao.PCDDao;
import com.pppcar.pojo.City;
import com.pppcar.pojo.Province;
import com.pppcar.service.PCDService;

@Service("PCDService")
public class PCDServiceImpl implements PCDService {

	@Autowired
	PCDDao pcdDao;

	public List<Province> getAllProvince() {
		List<Province> allProvince = pcdDao.getAllProvince();
		return allProvince;
	}

	public List<City> getAllCityByProvinceId(Integer provinceId) {

		return pcdDao.getALLByProvinceId(provinceId);
	}

}

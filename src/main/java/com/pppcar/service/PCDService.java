package com.pppcar.service;

import java.util.List;

import com.pppcar.pojo.City;
import com.pppcar.pojo.Province;

public interface PCDService {

	public List<Province> getAllProvince();

	public List<City> getAllCityByProvinceId(Integer provinceId);

}

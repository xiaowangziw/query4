package com.pppcar.service;

import java.util.List;

import com.pppcar.pojo.City;
import com.pppcar.pojo.Province;

public interface PCDService {

	List<Province> getAllProvince();

	List<City> getAllCityByProvinceId(Integer provinceId);

}

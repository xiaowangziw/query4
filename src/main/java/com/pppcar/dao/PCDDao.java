package com.pppcar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pppcar.pojo.City;
import com.pppcar.pojo.Province;

public interface PCDDao {

	List<Province> getAllProvince();

	List<City> getALLByProvinceId(@Param("provinceId") Integer provinceId);

}

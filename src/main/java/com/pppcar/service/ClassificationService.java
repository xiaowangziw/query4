package com.pppcar.service;

import java.util.List;

import com.pppcar.pojo.Classification;

public interface ClassificationService {

	/**
	 *	获取品类
	 * @return
	 */
	List<Classification> getAllClassification();

}

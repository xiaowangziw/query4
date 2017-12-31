package com.pppcar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pppcar.dao.ClassificationDao;
import com.pppcar.pojo.Classification;
import com.pppcar.service.ClassificationService;

@Service("classificationService")
public class ClassificationServiceImpl implements ClassificationService{

	@Autowired
	ClassificationDao classificationDao;
	public List<Classification> getAllClassification() {
		
		return classificationDao.getAllClassification();
	}

}

package com.pppcar.pojo;

public class Classification {
	private Integer classificationId;//品类id
	private Integer parentId;//品类父id
	private String className;//品类名称
	
	public Integer getClassificationId() {
		return classificationId;
	}
	public void setId(Integer classificationId) {
		this.classificationId = classificationId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}

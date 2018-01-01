package com.pppcar.pojo;

/***
 * 订单详情查询参数实体类
 * 
 * @author 
 * @data
 */
public class OrderDetailSearchArgs {

    private String province;
	private String city;
	private String salesman;
	private String classification;
	private Integer provinceId;
	private Integer cityId;
	private Integer salesmanId;
	private Integer classificationId;
    private String startTime;
    private String endTime;
    
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}
	public Integer getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    

  
}

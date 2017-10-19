package edu.nju.model;

import java.util.Date;

public class OrderVO {
	private String id;
	private Date startDate;
	private Date endDate;
	private String deviceNumber;//甲醛仪设备编号
	private String name;
	private String phone;
	private String address;
	public OrderVO(String id, Date startDate, Date endDate, String deviceNumber, String name, String phone,
			String address) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deviceNumber = deviceNumber;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
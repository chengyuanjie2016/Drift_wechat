package edu.nju.model;

import java.util.Date;
import java.util.List;

import edu.nju.entities.CheckResult;

public class OrderVO {
	private String id;
	private Date startDate;
	private Date endDate;
	private String deviceNumber;//甲醛仪设备编号
	private String name;
	private String phone;
	private String address;
	private String state;
	private List<CheckResult> jqNum;//室内甲醛含量
	
	public OrderVO(String id, Date startDate, Date endDate, String deviceNumber, String name, String phone,
			String address,String state,List<CheckResult> jqNum) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deviceNumber = deviceNumber;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.state = state;
		this.jqNum = jqNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public List<CheckResult> getJqNum() {
		return jqNum;
	}
	public void setJqNum(List<CheckResult> jqNum) {
		this.jqNum = jqNum;
	}
	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", deviceNumber="
				+ deviceNumber + ", name=" + name + ", phone=" + phone + ", address=" + address + ", state=" + state
				+ "]";
	}
	
}

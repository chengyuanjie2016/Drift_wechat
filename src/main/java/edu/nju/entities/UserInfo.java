package edu.nju.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_info")
public class UserInfo {
	private String id;
	private String name;
	private String address;
	private String phone;
	private String openid;
	private String nickName;
	private String zmxyid; //芝麻信用id
	private String transactionid;//芝麻信用的业务流水号id
	private int score;//芝麻信用分
	private int state;//1-5代表用户当前进行到哪一步了
	private String headimgUrl;
//	private double longtitute;
//	private double latitute;
	
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getZmxyid() {
		return zmxyid;
	}
	public void setZmxyid(String zmxyid) {
		this.zmxyid = zmxyid;
	}
	public String getHeadimgUrl() {
		return headimgUrl;
	}
	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", openid="
				+ openid + ", nickName=" + nickName + ", zmxyid=" + zmxyid + ", transactionid=" + transactionid
				+ ", score=" + score + "]";
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}

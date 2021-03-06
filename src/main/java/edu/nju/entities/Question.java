package edu.nju.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author lsy
 * 问题表
 */
@Entity
@Table(name = "question")
public class Question {
	private String id;
	private String openid;//提问者id
	private String title;
	private String content;
	private Date createTime;
	private String picSig;//某一张图片
	private int qstate;//标志位，0代表正常帖子，1代表推荐的帖子，2代表置顶的帖子
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="LONGTEXT",nullable=true)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="LONGTEXT",nullable=true)
	public String getPicSig() {
		return picSig;
	}
	public void setPicSig(String picSig) {
		this.picSig = picSig;
	}
	public int getQstate() {
		return qstate;
	}
	public void setQstate(int qstate) {
		this.qstate = qstate;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", openid=" + openid + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + ", picSig=" + picSig + "]";
	}
	
}

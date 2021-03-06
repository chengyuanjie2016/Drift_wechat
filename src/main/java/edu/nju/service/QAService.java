package edu.nju.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.dao.QADao;
import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;
import edu.nju.utils.HandleFile;

@Transactional
@Service
public class QAService {
	@Autowired
	QADao dao;
	
	public boolean makeFolder(String path) {
		return HandleFile.makeFolder(path);
	}
	/**
	 * 发布问题
	 */
	public String publishQuestion(String openid,String title,String content,
			List<MultipartFile> fileList,String filepath) {
		Question q = new Question();
		Date date = new Date();
		q.setOpenid(openid);
		q.setTitle(title);
		String[] result = HandleFile.saveFile(content,fileList,filepath);
		String realContent = result[0];
		String picSig = result[1];
		q.setContent(realContent);
		q.setCreateTime(date);
		q.setPicSig(picSig);
		q.setQstate(0);
		return dao.publishQuestion(q);
//		return "";
	}
	
	/**
	 * 添加回答
	 */
	public String addAnswer(String openid,String qid,String content,List<MultipartFile> fileList,String filepath) {
		Answer a = new Answer();
		a.setCreateTime(new Date());
		a.setOpenid(openid);
		a.setQid(qid);
		String[] result = HandleFile.saveFile(content,fileList,filepath);
		String realContent = result[0];
		String picSig = result[1];
		a.setPicSig(picSig);
		a.setContent(realContent);
		return dao.addAnswer(a);
	}
	
	/**
	 * 根据时间倒叙排序
	 */
	public List<Question> getAllQuestion(int start,int num){
		List<Question> list = dao.getAllQuestion(start,num);
		return list;
	}
	
	/**
	 * 根据问题的回答数量排序
	 */
	public List<Question> getQuestionByNum(int start,int num){
		List<Question> list = dao.getQuestionByNum(start,num);
		return list;
	}
	
	public List<Answer> getAnswers(String qid,int start,int num){
		List<Answer> list = dao.getAnswers(qid,start,num);
		return list;
	}
	
	/**
	 * 获得某个问题的回答数量
	 */
	public Long getAnswerNum(String qid) {
		Long num = dao.getAnswerNum(qid);
		return num;
	}
	
	/**
	 * 获得所有问题的数量
	 */
	public Long getQuestionNum() {
		Long num = dao.getQuestionNum();
		return num;
	}
	
	/**
	 * 点赞
	 */
	public boolean addlike(String answerid,String authorid,String likeid,String qid) {
		LikeInfo like = new LikeInfo();
		like.setAnswerid(answerid);
		like.setAuthorid(authorid);
		like.setLikeid(likeid);
		like.setQid(qid);
		like.setCreateTime(new Date());
		return dao.addlike(like);
	}
	
	/**
	 * 取消赞
	 */
	public boolean revokeLike(String qid,String answerid, String authorid,String likeid) {
		return dao.revokeLike(qid,answerid,authorid,likeid);
	}
	
	/**
	 * 检查是否点赞
	 */
	public boolean checkIfLike(String qid,String answerid, String authorid,String likeid) {
		return dao.checkIfLike(qid,answerid,authorid,likeid);
	}
	
	/**
	 * 根据点赞数排序
	 */
	public List<Answer> sortByLikes(String qid){
		List<Answer> list = dao.sortByLikes(qid);
		return list;
	}
	
	/**
	 * 根据日期排序
	 */
	public List<Answer> sortByDate(String qid){
		return dao.sortByDate(qid);
	}
	
	/**
	 * 根据answerid获得answer
	 */
	public Answer getByAnswerId(String aid) {
		return dao.getByAnswerId(aid);
	}
	
	public Question getByQuestionId(String qid) {
		return dao.getByQuestionId(qid);
	}
	
	/**
	 * 获得某个答案的点赞数量
	 */
	public Long getLikeNum(String answerid) {
		return dao.getLikeNum(answerid);
	}
}

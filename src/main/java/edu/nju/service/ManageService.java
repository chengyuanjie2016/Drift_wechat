package edu.nju.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ManageDao;
import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;

@Transactional
@Service
public class ManageService {
	
	@Autowired
	ManageDao manageDao;
	
	//获得所有设备状态信息
	public List<Device> getDevices(){
		List<Device> dlist = manageDao.getDevices();
		return dlist;
	};

	//获得所有用户信息
	public List<UserInfo> getUsers(){
		List<UserInfo> ulist = manageDao.getUsers();
		return ulist;
	};

}
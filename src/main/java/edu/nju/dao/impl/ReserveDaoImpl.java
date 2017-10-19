package edu.nju.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ReserveDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;
import edu.nju.utils.Utility;

@Repository
public class ReserveDaoImpl implements ReserveDao{
	
	 @Autowired
	 private BaseDao baseDao;
	 @Autowired
	 private UserDao userDao;
	 
//	 private static final int DEVICE_NUM=50;

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> getOrder(String openId) {
		String hql = "from Order where openid =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		List<OrderVO> volist = new ArrayList<OrderVO>();
		for(int i=0;i<list.size();i++){
			Order o = list.get(i);
			String openid = o.getOpenId();
			UserInfo u = userDao.getUser(openid);
			if(u!=null){
				OrderVO vo = new OrderVO(o.getId(), o.getStartDate(),o.getEndDate(), o.getDeviceNumber(), u.getName(), u.getPhone(),
						u.getAddress());
				volist.add(vo);
			}
			
		}
		return volist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrderById(String openId){
		String hql = "from Order where openid =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getBefore(String openId) {
		List<Order> list = getOrderById(openId);
		if(list.size()>0){
			Order o = list.get(0);
			String deviceId = o.getDeviceId();
			Date startDate = o.getStartDate();
			Date beforeDate = Utility.getSpecifiedDayAfter(startDate,-1);
			String hql = "from Order where endDate = :beforeDate and deviceId = : deviceId";
			List<Order> orderList = baseDao.getNewSession().createQuery(hql).setParameter("beforeDate", beforeDate).
					setParameter("deviceId", deviceId).getResultList();
			if(orderList.size()>0){
				Order resultOrder = orderList.get(0);
				String userId = resultOrder.getOpenId();
				UserInfo u = userDao.getUser(userId);
				return u;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getAfter(String openId) {
		List<Order> list = getOrderById(openId);
		if(list.size()>0){
			Order o = list.get(0);
			String deviceId = o.getDeviceId();
			Date startDate = o.getEndDate();
			Date afterDate = Utility.getSpecifiedDayAfter(startDate,1);
			String hql = "from Order where startDate = :afterDate and deviceId + : deviceId";
			List<Order> orderList = baseDao.getNewSession().createQuery(hql).setParameter("afterDate", afterDate).
					setParameter("deviceId", deviceId).getResultList();
			if(orderList.size()>0){
				Order resultOrder = orderList.get(0);
				String userId = resultOrder.getOpenId();
				UserInfo u = userDao.getUser(userId);
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean saveDelInfo(String openId,String did) {
		Device d = getDeviceByOpenId(openId);
		List<Device> list =getDeviceById(d.getId());
		if(list.size()>0){
			Device u = list.get(0);
//			u.setDeliveryId(did);
			List<Order> orderList = getById(d.getId());
			if(orderList.size()>0){
				Order o = orderList.get(0);
				o.setState(2);
				o.setDeliveryId(did);
				baseDao.save(o);
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Device> getDeviceById(String deviceId){
		String hql = "from Device where id =:deviceId";
		List<Device> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		return list;
	}
//
//	@Override
//	public String getDelNum(String deviceId) {
//		List<Device> list =getDeviceById(deviceId);
//		if(list.size()>0){
//			return list.get(0).getDeliveryId();
//		}
//		return "";
//	}

	@SuppressWarnings("unchecked")
	@Override
	public Device reserve(String openId) {
		String hql = "from Device where type = 0";
		List<Device> list = baseDao.getNewSession().createQuery(hql).getResultList();
		Utility.sortInt(list);//按照排队人数对设备进行排序
		Device device = list.get(0);//排队人数最少的设备被预定
		device.setQueueNum(device.getQueueNum()+1);//排队人数+1
		return device;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Device priorReserve(String openId) {
		String hql = "from Device";
		List<Device> list = baseDao.getNewSession().createQuery(hql).getResultList();
		Utility.sortInt(list);//按照排队人数对设备进行排序
		Device device = list.get(0);//排队人数最少的设备被预定
		device.setQueueNum(device.getQueueNum()+1);//排队人数+1
		return device;
	}

	@Override
	public boolean makeOrder(String openid, int ifPay, int num) {
		Device device = new Device();
		if(ifPay==0){
			device = reserve(openid);
		}else{
			device = priorReserve(openid);
		}
		Date queueDate = device.getQueueDate();
		Date startDate = Utility.getSpecifiedDayAfter(queueDate,1);
		Date endDate = Utility.getSpecifiedDayAfter(startDate,1);
		Order o = new Order(openid,startDate,endDate,device.getId(),device.getNumber(),0,num,ifPay,"");
		baseDao.save(o);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getById(String deviceId){
		String hql = "from Order where deviceId =:deviceId";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Device getDeviceByOpenId(String openId) {
		String hql = "from Order where openId = :openId";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openId", openId).getResultList();
		if(list.size()>0){
			Order o = list.get(0);
			String deviceId = o.getDeviceId();
			List<Device> deviceList =getDeviceById(deviceId);
			if(deviceList.size()>0){
				return deviceList.get(0);
			}
		}
		return null;
	}
	
}
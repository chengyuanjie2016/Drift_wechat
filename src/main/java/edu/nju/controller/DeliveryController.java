package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Device;
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController {
	
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/get")
	public void getDelivery(HttpSession session, HttpServletResponse response){
		JSONObject result=new JSONObject();
		Device device = service.getDeviceByOpenId((String)session.getAttribute("openid"));
		result.put("before", service.getBefore((String)session.getAttribute("openid")).getName());
		result.put("after", service.getAfter((String)session.getAttribute("openid")).getName());
		result.put("receive", service.getRecDid((String)session.getAttribute("openid")));
		result.put("send", service.getSendDid((String)session.getAttribute("openid")));
		result.put("enable", service.getOrderState((String)session.getAttribute("openid")));
//		Device device = service.getDeviceByOpenId("hahaha");
//		result.put("before", service.getBefore("hahaha").getName());
//		result.put("after", service.getAfter("hahaha").getName());
//		result.put("receive", service.getRecDid("hahaha"));
//		result.put("send", service.getSendDid("hahaha"));
		result.put("deviceId", device.getId());
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public String setDelivery(String deliveryNum, HttpSession session, HttpServletResponse response) throws ParseException{
		service.saveDelInfo((String) session.getAttribute("openid"),deliveryNum);
//		service.saveDelInfo("hahaha",deliveryNum);
		return "jsp/Delivery";
	}
	
	@RequestMapping(value = "/confirm")
	public String deliveryConfirm(HttpSession session, HttpServletResponse response){
		service.confirm((String)session.getAttribute("openid"));
//		service.confirm("hahaha");
		return "jsp/Orders";
	}
}

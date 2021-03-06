package edu.nju.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Device;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.service.ManageService;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.utils.PageUtil;

@Controller
@RequestMapping(value="/manage")
public class ManageController {
	@Autowired
	ManageService manageService;
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	ReserveGetService reserveGetService;
	@RequestMapping(value = "/addDevice")
	public String toAddDevice(HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "jsp/Manage/AddDevice";
	}
	
	@RequestMapping(value = "/toChangeArea")
	public String toChangeArea(String did,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		model.addAttribute("device",reserveGetService.getDeviceById(did));
		return "jsp/Manage/ModifyDevice";
	}
	@RequestMapping(value = "/deviceList")
	public String getIndex(String page,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		PageUtil pageUtil = new PageUtil(Integer.valueOf(page),manageService.getDevicenum());
		List<DeviceVO> deviceList = manageService.getDevices(pageUtil.getStart(),pageUtil.getPageSize());
		model.addAttribute("deviceList", deviceList);
		model.addAttribute("page", pageUtil);
		return "jsp/Manage/DeviceList";
	}
	
	@RequestMapping(value = "/changeType")
	@ResponseBody
	public String changeType(String did, String type,HttpSession session,Model model) {
		if(manageService.changeDeviceType(did, Integer.valueOf(type))){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	@RequestMapping(value = "/deleteDevice")
	@ResponseBody
	public String deleteDevice(String did,HttpSession session,Model model) {
		if(manageService.deleteDevice(did)){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	@RequestMapping(value = "/orderList")
	public String getOrderList(String page,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		PageUtil pageUtil = new PageUtil(Integer.valueOf(page),manageService.getOrdernum());
		List<OrderVO> orderList = manageService.getOrders(pageUtil.getStart(),pageUtil.getPageSize());
		model.addAttribute("orderList", orderList);
		model.addAttribute("page", pageUtil);
		return "jsp/Manage/OrderList";
	}
	
	@RequestMapping(value = "/companySend")
	public String companySend(HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<OrderVO> orderList = reserveService.getCompanySend();
		model.addAttribute("orderList", orderList);
		return "jsp/Manage/CompanySend";
	}
	
	@RequestMapping(value = "/deliveryNum")
	public void writeDeliveryNum(String orderId, String deliveryNum, HttpSession session, HttpServletResponse response) {
		reserveService.companySend(orderId, deliveryNum);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/companyReceive")
	public String companyRevice(String orderId, HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<OrderVO> orderList = reserveService.getCompanyReceive();
		model.addAttribute("orderList", orderList);
		return "jsp/Manage/CompanyReceive";
	}
	
	@RequestMapping(value = "/receiveConfirm")
	public void reviceConfirm(String orderId, HttpSession session, HttpServletResponse response) {
		reserveService.companyReceive(orderId);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/modify")
	public void modifyOrder(String order, HttpServletResponse response) {
		List<JSONObject> data = new ArrayList<JSONObject>();
		//调用service
		for(Map.Entry<DeviceVO, Date> entry : manageService.getAvailableDevice(order).entrySet()){
			JSONObject result=new JSONObject();
			result.put("id", entry.getKey().getId());
			result.put("number", entry.getKey().getNumber());
			result.put("date", entry.getValue().toString());
			data.add(result);
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/confirm")
	public void confirm(String orderId, String deviceNumber, String deviceId, String date,HttpServletResponse response) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		Date startDate=sdf.parse(date);
		
		//调用service
		manageService.updateOrder(orderId, deviceNumber, deviceId, startDate);
		try {
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addDeviceAction")
	@ResponseBody
	public String addDevice(String number, @RequestParam("areas") String[] areas, String type,Model model) {
		
		List<String> areaList = new ArrayList<String>();		
		for(int i=0;i<areas.length;i++){
			areaList.add(areas[i]);
		}
		Device device =  new Device();
		device.setLoc("company");
		device.setNumber(number);
		device.setQueueNum(0);
		device.setType(Integer.parseInt(type));
		manageService.addDeviceList(device, areaList);
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "success";
	}
	
	@RequestMapping(value = "/modifyDeviceAction")
	@ResponseBody
	public String addDevice( @RequestParam("areas") String[] areas,String did,String type,Model model) {
		List<String> areaList = new ArrayList<String>();		
		for(int i=0;i<areas.length;i++){
			areaList.add(areas[i]);
		}
		Device device = reserveGetService.getDeviceById(did);
		manageService.addDeviceList(device, areaList);
		List<String> provinces = new ArrayList<String>();
		provinces = readFile();
		model.addAttribute("provinces", provinces);
		return "success";
	}
	
	@RequestMapping(value = "/login")
	public void Login(String username, String password, HttpServletResponse response, HttpSession session){
		boolean result = manageService.login(username, password);
		JSONObject status = new JSONObject();
		if(result){
			status.put("status", "200");
			session.setAttribute("manage", username);
		}else{
			status.put("status", "400");
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(status);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String checkStatus(HttpSession session){
		if(session.getAttribute("manage") == null){
			return "jsp/Manage/Login";
		}else{
			return "true";
		}
	}
	
	public static List<String> readFile()
	{   
		String filePath = ManageController.class.getClassLoader().getResource("../province.txt").getPath();
		List<String> provinces = new ArrayList<String>();   
	    try   
	    {       
	        File f = new File(filePath);      
	        if(f.isFile()&&f.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");       
	            BufferedReader reader=new BufferedReader(read);       
	            String line;       
	            while ((line = reader.readLine()) != null)   
	            {        
	            	provinces.add(line);
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }     
	    return provinces;   
	}   
}

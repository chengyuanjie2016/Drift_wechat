package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.CheckResult;
import edu.nju.entities.Order;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.utils.Constants;

@Controller
@RequestMapping(value="/FB")
public class FeedBackController {
	@Autowired
	ReserveService service;
	@Autowired
	ReserveGetService getService;
	@RequestMapping(value = "/save")
	@ResponseBody
	public String addFB(HttpSession session,@RequestBody List<CheckResult> list,Model model) {
		String openid = (String)session.getAttribute("openid");
		//String openid = "oRTgpwYGzwzbmz3DSAS-Z5WM37Yg";
		JSONObject resultObj=new JSONObject(getService.getOrder(openid));
		List<Object> list1 = new ArrayList<Object>();
		if(resultObj.get(Constants.RESPONSE_CODE_KEY).equals("0")){
			JSONArray userAll = (JSONArray) resultObj.get(Constants.RESPONSE_DATA_KEY);
			String orderid ="";
			if(userAll.length()>0){
				JSONObject job = userAll.getJSONObject(0);
				System.out.println(job);
				orderid = job.getString("id");
			}
			for(CheckResult CR:list){
				CR.setOrderid(orderid);
			}
			
			if(service.addCheckResult(list)){
				return "1"; 
			}
		}
		return "0"; 
	}
}

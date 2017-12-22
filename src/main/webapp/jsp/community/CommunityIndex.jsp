<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	.question{
		color:black;
		font-size: 18px;
		font-weight:bold;
	}
	
	hr.style-one {
		border: 0;	
		height: 1px;	
		background: #333;	
		background-image: linear-gradient(to right, #ccc, #333, #ccc);	
	}
	hr.style-four {
		height: 12px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
</style>  

<body>
	<!--导航栏  -->
	<div class="weui-tabbar">
	  <a href="/Drift_wechat/jsp/BaiduMap.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/index.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">首页</p>
	  </a>
	  <a href="/Drift_wechat/jsp/Orders.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/order.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">订单</p>
	  </a>
	  <a href="javascript:;" class="weui-tabbar__item weui-bar__item--on">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/community.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">社区</p>
	  </a>
	  <a href="/Drift_wechat/jsp/MyIndex.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/my.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">我的</p>
	  </a>
	</div>
	
	<!--问题列表-->
	<div class="weui-panel weui-panel_access">
		<div class="weui-cell" style="background:#F5F5F5;margin:10px " align="center" >
		    <div class="weui-cell__bd" style="color:black">
		      <h3>热门话题</h3>
		    </div>
		    <div class="weui-cell__ft"><a href="/Drift_wechat/jsp/community/Ask.jsp"><img alt="" style="" src="/Drift_wechat/images/community/ask.png">&nbsp;&nbsp;&nbsp;提问</a></div>
		</div>
	<hr class="style-four" />		  
	  <div class="weui-panel__bd">
	    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			<div class="weui-cell__hd" style="width:30px"><h3>01</h3></div>
		      <div class="weui-media-box__bd">
			      <p class="weui-media-box__title question">标题二由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
		        <p class="weui-media-box__desc">109回答</p>
		      </div>
		      <div class="weui-media-box__hd">
		        <img class="weui-media-box__thumb" src="/Drift_wechat/images/info.jpg">
		      </div>
	    </a>
	    
	   	<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
	      <div class="weui-cell__hd" style="width:30px"><h3>01</h3></div>
	      <div class="weui-media-box__bd">
		    <p class="weui-media-box__title question">标题二由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
	        <p class="weui-media-box__desc">109回答</p>
	      </div>
	    </a>
	    
	  </div>
	  <div class="weui-panel__ft">
	    <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
	      <div class="weui-cell__bd" align="center">查看更多</div>
	      <span class="weui-cell__ft"></span>
	    </a>    
	  </div>
	  
	</div>
	

	 
</body>
</html>
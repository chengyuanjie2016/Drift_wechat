<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
  <script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <script src="https://as.alipayobjects.com/g/component/fastclick/1.0.6/fastclick.js"></script>
  <script>
    if ('addEventListener' in document) {
      document.addEventListener('DOMContentLoaded', function() {
        FastClick.attach(document.body);
      }, false);
    }
  </script>
  <title>开始预约</title>
</head>
<body>
	<!--TOP -->
	<%@include file="TopBar.html"%>
  	<!--文案 -->
	<div style="height:100%;width:100%;margin-top:20%;">
		<img width=100% height=100% src="/Drift_wechat/images/index.png" title="甲醛漂流仪"/>
	</div>
	<!--按钮组 -->
	<div class="weui-flex" style="bottom:10%;position:absolute;width:100%;">
	  <div class="weui-flex__item placeholder" style="padding-left:20px;padding-right:10px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set'">我要预约</button>
	  </div>
	  <div class="weui-flex__item placeholder" style="padding-left:10px;padding-right:20px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:;">联系客服</button>
	  </div>
	</div>
	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script>set(1);</script>
</body>
</html>
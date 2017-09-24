<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/city-picker.min.js" charset="utf-8"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">
    <title>预定信息</title>
  </head>
  
  <body>
  <script type="text/javascript">
    weui.alert('芝麻信用绑定成功，开始填写预定信息吧！');
  </script>
  
 <div class="container" id="container">
	<img src="/Drift_wechat/images/icon.jpg"  width="80px" height="80px" align="right"/>
    <div class="page__hd">
        <h1 class="page__title" align="left">&nbsp;&nbsp;请输入预约信息</h1>
    </div>
    </br>
    <form method="get" id="detail" name="detail" action="/Drift_wechat/api/user/setAddress">
    <div class="page__bd">
        <div class="weui-cells__title">预约时间</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label for="" class="weui-label">起始日期</label></div>
                <div class="weui-cell__bd">
                    <input id="startDate" name="startDate" class="weui-input" type="date" value="" required="required">
                </div>
            </div>
        
            <div class="weui-cell">
                <div class="weui-cell__hd"><label for="" class="weui-label">结束日期</label></div>
                <div class="weui-cell__bd">
                    <input id="endDate" name="endDate" class="weui-input" type="date" value="" required="required">
                </div>
            </div> 
        </div>
		</br>
        <div class="weui-cells__title">地址选择</div>   
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input id='city-picker' name="address" class="weui-input" placeholder="选择省市区" required="required">
                    <script>
  						$("#city-picker").cityPicker({
    					title: "请选择收货地址"
  						});
					</script>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" name="address_detail" placeholder="请填写可以邮寄给您的详细地址哦" required="required">
                </div>
            </div>
        </div>
        
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">接受产品推荐</div>
                <div class="weui-cell__ft">
                    <input id="recommand" name="recommand" class="weui-switch" type="checkbox">
                </div>
            </div>
        </div>

        <div class="weui-btn-area">
            <button class="weui-btn weui-btn_primary" type="submit" id="submit">确定并提交</button>
        </div>
      
    </div>
    </form>
</div>

        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
  </body>
</html>

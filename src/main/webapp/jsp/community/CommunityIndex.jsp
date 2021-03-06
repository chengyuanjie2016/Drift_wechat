<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>果麦公益检测</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.css">
	<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
	<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
	<script src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script src="/Drift_wechat/js/fastclick.js"></script>
	<script src="/Drift_wechat/js/jquery-weui.js"></script>
	<script src="/Drift_wechat/js/bootstrap.min.js"></script>
	<style type="text/css">
		.question{
			color:black;
			font-size: 18px;
			font-weight:bold;
		}
		hr.style-four {
			height: 12px;
			border: 0;
			box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
		}
	</style> 
  </head>

  <body ontouchstart>
	<!--下拉刷新  -->
    <div class="weui-pull-to-refresh__layer">
      <div class='weui-pull-to-refresh__arrow'></div>
      <div class='weui-pull-to-refresh__preloader'></div>
      <div class="down">下拉刷新</div>
      <div class="up">释放刷新</div>
      <div class="refresh">正在刷新</div>
    </div>
    
	<!--导航栏  -->
	<div class="weui-tabbar weui-footer_fixed-bottom" style="bottom:0">
	  <a href="/Drift_wechat/api/wechat/center" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/index.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">首页</p>
	  </a>
	  <a href="/Drift_wechat/api/user/getState" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/order.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">订单</p>
	  </a>
	  <a href="/Drift_wechat/api/QA/Index" class="weui-tabbar__item weui-bar__item--on">
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
		      <div class="weui-media-box__bd" style="height:20px">
	      </div>
	<!--页面抬头-->
	<div class="weui-cell" align="center" style="background:#F5F5F5;margin:10px;">
  		<div class="weui-cell__ft">
			<div class="dropdown" style="margin:0px;padding:0px">
			    <button type="button" tyle="background:#F5F5F5" class="btn dropdown-toggle btn-sm" id="dropdownMenu1" data-toggle="dropdown">排序
			        <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
			        <li role="presentation">
			            <a role="menuitem" tabindex="-1" href="/Drift_wechat/api/QA/Index">按时间排序</a>
			        </li>
			        <li role="presentation">
			            <a role="menuitem" tabindex="-1" href="/Drift_wechat/api/QA/IndexSortByNum">按热度排序</a>
			        </li>
			    </ul>
			</div>
  		</div>
	    <div class="weui-cell__bd" style="font-weight:bold;">
	      <h4>热门话题</h4>
	    </div>
	    <div class="weui-cell__ft"><a href=><a href="/Drift_wechat/jsp/community/PublishQ.html"><img alt="" style="" src="/Drift_wechat/images/community/ask.png">&nbsp;&nbsp;&nbsp;提问</a></div>
	</div>
	
	<hr class="style-four" />	
	  
	  <!--问题列表  -->		  
	  <div class="weui-panel__bd">
 		<c:forEach items="${qList}" var="Q" varStatus="index">
			<a href="/Drift_wechat/api/QA/DateSort?qid=${Q.id}  " class="weui-media-box weui-media-box_appmsg">
				<div class="weui-cell__hd" style="width:30px"><h3>${index.count}</h3></div>
			      <div class="weui-media-box__bd">
				      <p class="weui-media-box__title question">${Q.title}</p>
			        <p class="weui-media-box__desc">${qnumList[index.count-1]}回答</p>
			      </div>
			      <c:if test="${not empty Q.picSig}">
					  <div class="weui-media-box__hd">
				          <img class="weui-media-box__thumb" src="${Q.picSig}">
				      </div>
				  </c:if>
		    </a>
		</c:forEach>
		<a href="" class="weui-media-box weui-media-box_appmsg">
	      <div class="weui-media-box__bd" style="height:20%">
		      <p class="weui-media-box__title question"></p>
	      </div>
		</a>	
	</div>
	
	<script>
	  $(function() {
	    FastClick.attach(document.body);
	  });
	  
      $(document.body).pullToRefresh(function() {
        setTimeout(function() {
          window.location.href="/Drift_wechat/api/QA/Index";
          $(document.body).pullToRefreshDone();
        }, 1000);
      });
    </script>
  </body>
</html>

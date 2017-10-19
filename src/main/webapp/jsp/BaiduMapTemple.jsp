<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2"></script>
    <title>百度地图示例</title>
</head>
<body>
    <div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

    var points = [new BMap.Point(116.3786889372559,39.90762965106183),
                  new BMap.Point(116.38632786853032,39.90795884517671)
                  //new BMap.Point(116.39534009082035,39.907432133833574),
                  //new BMap.Point(116.40624058825688,39.90789300648029),
                  //new BMap.Point(116.41413701159672,39.90795884517671)
    ];
    var myPoint = new BMap.Point(116.378688937,39.9076296510);
    var myPoint2 = new BMap.Point(116.41413701159672,39.90795884517671);
    
    //地图初始化
    var bm = new BMap.Map("allmap");
    bm.centerAndZoom(myPoint, 15);
    var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(300,157));
    var myIcon2 = new BMap.Icon("/Drift_wechat/images/icon.jpg", new BMap.Size(100,100));
    bm.addOverlay(new BMap.Marker(myPoint,{icon:myIcon}));
    bm.addOverlay(new BMap.Marker(myPoint2,{icon:myIcon2}));

    //坐标转换完之后的回调函数
    translateCallback = function (data){
      if(data.status === 0) {
        for (var i = 0; i < data.points.length; i++) {
            bm.addOverlay(new BMap.Marker(data.points[i]));
            bm.setCenter(data.points[i]);
        }
      }
    }
    setTimeout(function(){
        var convertor = new BMap.Convertor();
        convertor.translate(points, 1, 5, translateCallback)
    }, 1000);
</script>
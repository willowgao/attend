<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/echartsCommon.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
	</head>
	<body>
		<div id="bar"
			style="position: absolute; left: 15px; top: 10px; width: 95%; height: 50%; border: 1px solid #dddddd; margin: 10px auto;"></div>
		<script type="text/javascript">
		var jq = jQuery.noConflict();
          jq(function() {
			drawBar(); 
		});   
        
            function drawBar() {
               var myChart = echarts.init(document.getElementById('bar'));
               myChart.showLoading({
			    text: '正在努力的读取数据中...',    //loading话术
			})
               var option =  "";
               
			jq.ajaxSettings.async = false; 
            jq.getJSON(programName+'/query/queryAssess!queryOrgRanking.action', function(datas) {
			//取出json的值
				option =  datas;
			});
			 
                 if(option!=null&&option!=""){
             	  myChart.setOption(option);
               }
               myChart.hideLoading();
               
           }
             
           
    </script>
	</body>
</html>
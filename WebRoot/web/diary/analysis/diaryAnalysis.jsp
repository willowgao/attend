<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/echarts/build/source/echarts.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
	</head>
	<body>
		<div id="bar"
			style="position: absolute; left: 15px; top: 261px; width: 1300px; height: 300px; border: 1px solid #dddddd; margin: 10px auto;"></div>
		<script type="text/javascript">
		var jq = jQuery.noConflict();
        require.config({
            paths: {
                echarts:'../web/echarts/build/dist' //引用资源文件夹路径，注意路径
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar'  // 按需加载所需图表，用到什么类型就加载什么类型，这里不需要考虑路径     
            ],
            DrawCharts
          
        );     
        
          function DrawCharts(ec) {  
             drawBar(ec);         
           }
            function drawBar(ec) {
               var myChart = ec.init(document.getElementById('bar'));
               myChart.showLoading({
			    text: '正在努力的读取数据中...',    //loading话术
			})
               var ecConfig = require('echarts/config');
               var option =  "";
               
			jq.ajaxSettings.async = false; 
               jq.getJSON(programName+'/diary/diaryAnalysis!getQueryMaxDiss.action', function(datas) {
			//取出json的值
				option =  datas;
			});
			 
               myChart.setOption(option);
               myChart.hideLoading();
               
           }
             
           
    </script>
	</body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/common.jsp"%>
<%@ include file="../common/echartsCommon.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>  
  <div id="bar" style="position: absolute; left:15px; top: 261px;width:1300px;height:300px;border:1px solid #dddddd;margin:10px auto;"></div>
  <div id="pie" style="position: absolute; left:15px; top: 5px;width:648;height:250px;border:1px solid #dddddd;margin:10px auto;"></div>
  <div id="line" style="position: absolute; left:668px; top: 5px;width:648;height:250px;border:1px solid #dddddd;margin:10px auto;"></div>
  <script type="text/javascript">
		var jq = jQuery.noConflict();
        jq(function() {
			drawBar();
			drawLine();
			drawPie();
		});
	
        
             function drawBar() {
                var myChart = echarts.init(document.getElementById('bar'));
                myChart.showLoading({
				    text: '正在努力的读取数据中...',    //loading话术
				})
                var option =  "";
                
				jq.ajaxSettings.async = false; 
                jq.getJSON('<%=webapp%>/echarts/echartTest!getBar.action', function(datas) {
				//取出json的值
					option =  datas;
				});
				 
                myChart.setOption(option);
                myChart.hideLoading();
                
            }
            
             function drawLine() {
                var myChart = echarts.init(document.getElementById('line'));
                myChart.showLoading({
				    text: '正在努力的读取数据中...',    //loading话术
				})
                var option =  "";
                
				jq.ajaxSettings.async = false; 
                jq.getJSON('<%=webapp%>/echarts/echartTest!getLine.action', function(datas) {
				//取出json的值
					option =  datas;
				});
                myChart.setOption(option);
                myChart.hideLoading();
                
            }
            
             function drawPie() {
                var myChart = echarts.init(document.getElementById('pie'));
                myChart.showLoading({
				    text: '正在努力的读取数据中...',    //loading话术
				})
                var option =  "";
                
				jq.ajaxSettings.async = false; 
                jq.getJSON('<%=webapp%>/echarts/echartTest!getPie.action', function(datas) {
				//取出json的值
					option =  datas;
				});
				 
                myChart.setOption(option);
                myChart.hideLoading();
                
            }
           
    </script>
  </body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script src="<%=webapp %>/web/echarts/build/source/echarts.js" type="text/javascript"></script>
  </head>
  <body>  
  <div id="bar" style="position: absolute; left:15px; top: 261px;width:1300px;height:300px;border:1px solid #dddddd;margin:10px auto;"></div>
  <div id="pie" style="position: absolute; left:15px; top: 5px;width:648;height:250px;border:1px solid #dddddd;margin:10px auto;"></div>
  <div id="line" style="position: absolute; left:668px; top: 5px;width:648;height:250px;border:1px solid #dddddd;margin:10px auto;"></div>
  <script type="text/javascript">
		var jq = jQuery.noConflict();
        require.config({
            paths: {
                echarts: '../web/echarts/build/dist' //引用资源文件夹路径，注意路径
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/line',   // 按需加载所需图表，用到什么类型就加载什么类型，这里不需要考虑路径    
                'echarts/chart/bar',   // 按需加载所需图表，用到什么类型就加载什么类型，这里不需要考虑路径            
                'echarts/chart/pie'   // 按需加载所需图表，用到什么类型就加载什么类型，这里不需要考虑路径            
            ],
            DrawCharts
          
        );     
        
          function DrawCharts(ec) {
             drawPie(ec);          
             drawLine(ec);      
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
                jq.getJSON('<%=webapp%>/echarts/echartTest!getBar.action', function(datas) {
				//取出json的值
					option =  datas;
				});
				 
                myChart.setOption(option);
                myChart.hideLoading();
                
            }
            
             function drawLine(ec) {
                var myChart = ec.init(document.getElementById('line'));
                myChart.showLoading({
				    text: '正在努力的读取数据中...',    //loading话术
				})
                var ecConfig = require('echarts/config');
                var option =  "";
                
				jq.ajaxSettings.async = false; 
                jq.getJSON('<%=webapp%>/echarts/echartTest!getLine.action', function(datas) {
				//取出json的值
					option =  datas;
				});
                myChart.setOption(option);
                myChart.hideLoading();
                
            }
            
             function drawPie(ec) {
                var myChart = ec.init(document.getElementById('pie'));
                myChart.showLoading({
				    text: '正在努力的读取数据中...',    //loading话术
				})
                var ecConfig = require('echarts/config');
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
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.wgsoft.diary.model.DiaryComments"
import="java.util.*"%>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">
body,div,h3,ul,li,p {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

ul {
	list-style-type: none;
}

body {
	color: #333;
	font: 12px/ 1.5 \5b8b\4f53;
}

#msgBox {
	width: 100%;
	background: #fff;
	border-radius: 5px;
	margin: 10px auto;
	padding-top: 10px;
}


#msgBox form {
	padding: 0 20px 15px;
}

#userName,#conBox {
	color: #777;
	border: 1px solid #d0d0d0;
	border-radius: 6px;
	background: #fff  repeat-x;
	padding: 3px 5px;
	font: 14px/ 1.5 arial;
}

#userName.active,#conBox.active {
	border: 1px solid #7abb2c;
}

#userName {
	height: 20px;
}

#conBox {
	width: 100%;
	resize: none;
	height: 65px;
	overflow: auto;
}

#msgBox form div {
	position: relative;
	color: #999;
	margin-top: 2px;
}

#msgBox img {
	border-radius: 3px;
}

#face {
	position: absolute;
	top: 0;
	left: 172px;
}

#face img {
	margin-left:5px;
	width: 10px;
	height: 10px;
	cursor: pointer;
	margin-right: 6px;
	opacity: 0.5;
	filter: alpha(opacity = 50);
}

#face img.hover,#face img.current {
	width: 18px;
	height: 18px;
	border: 1px solid #f60;
	opacity: 1;
	filter: alpha(opacity = 100);
}
 

#msgBox form .maxNum {
	font: 26px/ 30px Georgia, Tahoma, Arial;
	padding: 0 5px;
}

#msgBox .list {
	padding: 10px;
}

#msgBox .list h3 {
	position: relative;
	height: 33px;
	font-size: 14px;
	font-weight: 120;
	background: #1A7BC9;
	border: 1px solid #dee4e7;
	border-radius: 3px;
}

#msgBox .list h3 span {
	position: absolute;
	left: 6px;
	top: 6px;
	background: #fff;
	line-height: 28px;
	display: inline-block;
	padding: 0 15px;
	border-radius: 3px;
}

#msgBox .list ul {
	overflow: hidden;
	zoom: 1;
}

#msgBox .list ul li {
	float: left;
	clear: both;
	width: 100%;
	border-bottom: 1px dashed #d8d8d8;
	padding: 10px 0;
	background: #fff;
	overflow: hidden;
}

#msgBox .list ul li.hover {
	background: #f5f5f5;
}

#msgBox .list .userPic {
	float: left;
	width: 10px;
	height: 10px;
	display: inline;
	margin-left: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

#msgBox .list .content {
	float: left;
	width: 80%;
	font-size: 12px;
	margin-left: 10px;
	font-family: arial;
	word-wrap: break-word;
}

#msgBox .list .userName {
	display: inline;
	padding-right: 10px;
}

#msgBox .list .userName a {
	/**margin-left:30px;**/
	color: #2b4a78;
}

#msgBox .list .msgInfo {
	display: inline;
	word-wrap: break-word;
}

#msgBox .list .times {
	color: #889db6;
	font: 12px/ 18px arial;
	margin-top: 5px;
	overflow: hidden;
	zoom: 1;
}

#msgBox .list .times span {
	margin-top: 3px;
	float: left;
}

#msgBox .list .times a {
	float: right;
	color: #889db6;
	display: none;
}

.tr {
	overflow: hidden;
	zoom: 1;
}

.tr p {
	float: right;
	line-height: 30px;
}

.tr * {
	float: left;
}
</style>
		<script type="text/javascript">
		
		
/*-------------------------- +
  获取id, class, tagName
 +-------------------------- */
var get = {
	byId: function(id) {
		return typeof id === "string" ? document.getElementById(id) : id
	},
	byClass: function(sClass, oParent) {
		var aClass = [];
		var reClass = new RegExp("(^| )" + sClass + "( |$)");
		var aElem = this.byTagName("*", oParent);
		for (var i = 0; i < aElem.length; i++) reClass.test(aElem[i].className) && aClass.push(aElem[i]);
		return aClass
	},
	byTagName: function(elem, obj) {
		return (obj || document).getElementsByTagName(elem)
	}
};
/*-------------------------- +
  事件绑定, 删除
 +-------------------------- */
var EventUtil = {
	addHandler: function (oElement, sEvent, fnHandler) {
		oElement.addEventListener ? oElement.addEventListener(sEvent, fnHandler, false) : (oElement["_" + sEvent + fnHandler] = fnHandler, oElement[sEvent + fnHandler] = function () {oElement["_" + sEvent + fnHandler]()}, oElement.attachEvent("on" + sEvent, oElement[sEvent + fnHandler]))
	},
	removeHandler: function (oElement, sEvent, fnHandler) {
		oElement.removeEventListener ? oElement.removeEventListener(sEvent, fnHandler, false) : oElement.detachEvent("on" + sEvent, oElement[sEvent + fnHandler])
	},
	addLoadHandler: function (fnHandler) {
		this.addHandler(window, "load", fnHandler)
	}
};
/*-------------------------- +
  设置css样式
  读取css样式
 +-------------------------- */
function css(obj, attr, value)
{
	switch (arguments.length)
	{
		case 2:
			if(typeof arguments[1] == "object")
			{	
				for (var i in attr) i == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + attr[i] + ")", obj.style[i] = attr[i] / 100) : obj.style[i] = attr[i];
			}
			else
			{	
				return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj, null)[attr]
			}
			break;
		case 3:
			attr == "opacity" ? (obj.style["filter"] = "alpha(opacity=" + value + ")", obj.style[attr] = value / 100) : obj.style[attr] = value;
			break;
	}
};

var jq = jQuery.noConflict();
jq(function() {
	 jq('#face').hide();
	 reSetLiCss();
});

/**
* 根据li元素的多少来设置名称和评论内容的位置
*
**/
var reSetLiCss = function(){
	var liObject = jq('li');
	 if(liObject.length >= 6){
	 	jq('.content').css('margin-top','0px');
	 	jq('.userName').css('margin-left','22px');
	 }
}
/**
 * 
 * 保存数据
 * @return
 */
function addDiscuss(){
	var ids = null;
	 var comments = jq('#conBox').val();
	 var diaryid = jq('#diaryid').val();
	 jq.ajaxSettings.async = false; 
	 jq.getJSON(programName + '/diary/diaryDiscuss!discussDiarys.action?comments='+comments+'&diaryid='+diaryid, function(re_datas) {
			ids = re_datas;
	 });
	 return ids;
}

/**
 * 
 * 删除数据
 * @return
 */
function delDiscussComment(commentid){
	var ids = null;
	 jq.getJSON(programName + '/diary/diaryDiscuss!delDiarysComments.action?commentid='+commentid, function(re_datas) {
			rows = re_datas;
	 });
	 return ids;
}


/**
 * 
 * 检查评论人员是否未登录人员
 * @return
 */
 function checkUserName(userName){
  if(jq('#userName').val()==userName){
  	return true;
  }else{
  	return false;
  }
}
 
 
 
EventUtil.addLoadHandler(function ()
{
	var oMsgBox = get.byId("msgBox");
	var oUserName = get.byId("userName");
	var oConBox = get.byId("conBox");
	var oSendBtn = get.byId("sendBtn");
	var oMaxNum = get.byClass("maxNum")[0];
	var oCountTxt = get.byClass("countTxt")[0];
	var oList = get.byClass("list")[0];
	var oUl = get.byTagName("ul", oList)[0];
	var aLi = get.byTagName("li", oList);
	var aFtxt = get.byClass("f-text", oMsgBox);
	var aImg = get.byTagName("img", get.byId("face"));
	var bSend = false;
	var timer = null;
	var oTmp = "";
	var i = 0;
	var maxNum = 140;
	
	//禁止表单提交
	EventUtil.addHandler(get.byTagName("form", oMsgBox)[0], "submit", function () {return false});
	
	//为广播按钮绑定发送事件
	EventUtil.addHandler(oSendBtn, "click", fnSend);
	
	//为Ctrl+Enter快捷键绑定发送事件
	EventUtil.addHandler(document, "keyup", function(event)
	{
		var event = event || window.event;
		event.ctrlKey && event.keyCode == 13 && fnSend()
	});
	
	//发送广播函数
	function fnSend ()
	{
		var reg = /^\s*$/g;
		if(reg.test(oUserName.value))
		{
			alert("\u8bf7\u586b\u5199\u60a8\u7684\u59d3\u540d");
			oUserName.focus()
		}
		else if(!/^[u4e00-\u9fa5\w]{2,8}$/g.test(oUserName.value))
		{
			alert("\u59d3\u540d\u75312-8\u4f4d\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf\u3001\u6c49\u5b57\u7ec4\u6210\uff01");
			oUserName.focus()
		}
		else if(reg.test(oConBox.value))
		{
			alert("\u968f\u4fbf\u8bf4\u70b9\u4ec0\u4e48\u5427\uff01");
			oConBox.focus()
		}
		else if(!bSend)
		{
			alert("\u4f60\u8f93\u5165\u7684\u5185\u5bb9\u5df2\u8d85\u51fa\u9650\u5236\uff0c\u8bf7\u68c0\u67e5\uff01");
			oConBox.focus()
		}
		else
		{
			var oLi = document.createElement("li");
			var oDate = new Date();
			
			
			//save 
			var commentid = addDiscuss();
			 
			oLi.innerHTML = "<input id='commentid' type='hidden'  value='"+commentid+"' /><div class=\"userPic\"><img src=\"" + get.byClass("current", get.byId("face"))[0].src + "\" style='width: 20px;height: 20px;'></div>\
							 <div class=\"content\">\
							 	<div class=\"userName\"><a href=\"javascript:;\">" + oUserName.value + "</a>:</div>\
								<div class=\"msgInfo\">" + oConBox.value.replace(/<[^>]*>|&nbsp;/ig, "") + "</div>\
								<div class=\"times\"><span><%=DateUtil.getNowDateByFormat(DateUtil.YNYMDRHMS)%></span><a class=\"del\" href=\"javascript:;\">\u5220\u9664</a></div>\
							 </div>";
			
			//插入元素
			aLi.length ? oUl.insertBefore(oLi, aLi[0]) : oUl.appendChild(oLi);
			
			//重置表单
			get.byTagName("form", oMsgBox)[0].reset();
			for (i = 0; i < aImg.length; i++) aImg[i].className = "";
			aImg[0].className = "current";
			
			//将元素高度保存
			var iHeight = oLi.clientHeight - parseFloat(css(oLi, "paddingTop")) - parseFloat(css(oLi, "paddingBottom"));
			var alpah = count = 0;
			css(oLi, {"opacity" : "0", "height" : "0"});	
			timer = setInterval(function ()
			{
				css(oLi, {"display" : "block", "opacity" : "0", "height" : (count += 8) + "px"});
				if (count > iHeight)
				{
					clearInterval(timer);
					css(oLi, "height", iHeight + "px");
					timer = setInterval(function ()
					{
						css(oLi, "opacity", (alpah += 10));
						alpah > 100 && (clearInterval(timer), css(oLi, "opacity", 100))
					},30)
				}
			},30);
			//调用鼠标划过/离开样式
			liHover();
			//调用删除函数
			delLi()
			//样式重置
			reSetLiCss();
		}
	};
	
	
	//事件绑定, 判断字符输入
	EventUtil.addHandler(oConBox, "keyup", confine);	
	EventUtil.addHandler(oConBox, "focus", confine);
	EventUtil.addHandler(oConBox, "change", confine);
	
	//输入字符限制
	function confine ()
	{
		var iLen = 0;		
		for (i = 0; i < oConBox.value.length; i++) iLen += /[^\x00-\xff]/g.test(oConBox.value.charAt(i)) ? 1 : 0.5;
		oMaxNum.innerHTML = Math.abs(maxNum - Math.floor(iLen));	
		maxNum - Math.floor(iLen) >= 0 ? (css(oMaxNum, "color", ""), oCountTxt.innerHTML = "\u8fd8\u80fd\u8f93\u5165", bSend = true) : (css(oMaxNum, "color", "#f60"), oCountTxt.innerHTML = "\u5df2\u8d85\u51fa", bSend = false)
	}
	//加载即调用
	confine();		
	
	//li鼠标划过/离开处理函数
	function liHover()
	{
		for (i = 0; i < get.byTagName("li", oList).length; i++)
		{
			//li鼠标划过样式
			EventUtil.addHandler(get.byTagName("li", oList)[i], "mouseover", function (event)
			{
				this.className = "hover";
				oTmp = get.byClass("times", this)[0];
				var aA = get.byTagName("a", oTmp);
				var userName = "";
				//如果第一个没有取到，说明是异步增加的，不是页面初始化，则从0开始取
				if(this.childNodes[5]!=null){
					 userName = this.childNodes[5].childNodes[1].childNodes[1].text;
				}else{
					 userName = this.childNodes[3].childNodes[1].childNodes[0].text;
				}
				//为同一个人才可以进行删除
				if(!checkUserName(userName)){
					this.className = "";
					var oA = get.byTagName("a", get.byClass("times", this)[0])[0];
					oA.style.display = "none"	
				}else{
					if (!aA.length)
					{
						var oA = document.createElement("a");					
						oA.innerHTML = "删除";
						oA.className = "del";
						oA.href = "javascript:;";
						oTmp.appendChild(oA)
					}
					else
					{
						aA[0].style.display = "block";
					}
				}
			});

			//li鼠标离开样式
			EventUtil.addHandler(aLi[i], "mouseout", function ()
			{
				this.className = "";
				var oA = get.byTagName("a", get.byClass("times", this)[0])[0];
				oA.style.display = "none"	
			})
		}
	}
	liHover();
	
	//删除功能
	function delLi()
	{
		var aA = get.byClass("del", oUl);
		
		for (i = 0; i < aA.length; i++)
		{	
			aA[i].onclick = function ()
			{	
				var oParent = this.parentNode.parentNode.parentNode;
				var commentid = oParent.childNodes[1].value;
				//如果第一个没有取到，说明是异步增加的，不是页面初始化，则从0开始取
				if(typeof(commentid)=='undefined'){
					commentid = oParent.childNodes[0].value;
				}
				//定义删除时调用的方法
				delDiscussComment(commentid);
				var alpha = 100;
				var iHeight = oParent.offsetHeight;
				timer = setInterval(function ()
				{
					css(oParent, "opacity", (alpha -= 10));
					if (alpha < 0)
					{
						clearInterval(timer);						
						timer = setInterval(function ()
						{
							iHeight -= 10;
							iHeight < 0 && (iHeight = 0);
							css(oParent, "height", iHeight + "px");
							iHeight == 0 && (clearInterval(timer), oUl.removeChild(oParent))
						},30)
					}	
				},30);			
				this.onclick = null	
			}			
		}
	}
	delLi();
	
	//输入框获取焦点时样式
	for (i = 0; i < aFtxt.length; i++)
	{
		EventUtil.addHandler(aFtxt[i], "focus", function ()	{this.className = "active"});		
		EventUtil.addHandler(aFtxt[i], "blur", function () {this.className = ""})
	}
	
	//格式化时间, 如果为一位数时补0
	function format(str)
	{
		return str.toString().replace(/^(\d)$/,"0$1")
	}
	
	//头像
	for (i = 0; i < aImg.length; i++)
	{
		aImg[i].onmouseover = function ()
		{
			this.className += " hover"
		};
		aImg[i].onmouseout = function ()
		{
			this.className = this.className.replace(/\s?hover/,"")
		};
		aImg[i].onclick = function ()
		{
			for (i = 0; i < aImg.length; i++) aImg[i].className = "";
			this.className = "current"	
		}
	}
});
</script>
	</head>
	<body>
		<div id="msgBox">
			<input id="diaryid" value="<%=request.getAttribute("diaryid") %>" type="hidden">
			<form> 
				<div>
					<input id="userName" type="hidden"   value="<%=request.getAttribute("username")  %>" />
					<p id="face">
						<img src="<%=webapp%>/web/diary/discuss/img/face.jpg" class="current" />
					</p>
				</div>
				<div>
					<textarea id="conBox" class="f-text"></textarea>
				</div>
				<div class="tr">
					<p>
						<span class="countTxt">还能输入</span><strong class="maxNum">140</strong><span>个字</span>
						<a id="sendBtn"  href="#" class="easyui-linkbutton c5">发送</a>
					</p>
				</div>
			</form>
			<div class="list">
				<h3>
					<span>评论信息</span>
				</h3>
				<ul>
						<%
						
							List<DiaryComments> comments = (List<DiaryComments>)request.getAttribute("comments");
							if(comments!=null&&comments.size()>0){
								for(DiaryComments comment:comments){
								
						%>
					<li>
						<input type="hidden" id="commentid" value="<%=comment.getCommentid() %>"/>
						<div class="userPic">
							<img  src="<%=webapp%>/web/diary/discuss/img/face.jpg" style="width: 20px;height: 20px;" />
						</div>
						<div class="content">
							<div class="userName">
								<a href="javascript:;"><%=comment.getUsername() %></a>:
							</div>
							<div class="msgInfo">
								<%=comment.getComments() %>
							</div>
							<div class="times">
								<span><%=DateUtil.date2String(comment.getCommdate(),DateUtil.YNYMDRHMS) %></span><a class="del" href="javascript:;">删除</a>
							</div>
						</div>	
					</li>
						<%
								}
							}
						%>	
				</ul>
			</div>
		</div>

	</body>
</html>
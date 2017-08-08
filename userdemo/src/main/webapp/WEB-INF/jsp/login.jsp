<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>首页</title>
  <script type="text/javascript" src="js/core.js"></script>
  <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <link rel="stylesheet"
          href="js/jquery-easyui-1.2.6/themes/default/easyui.css" type="text/css" />
    <link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/icon.css"
          type="text/css" />
    <script type="text/javascript"
            src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/messages_cn.js"></script>
    <script type="text/javascript" src="js/core.js"></script>
    <!-- <script type="text/javascript" src="js/checkCode.js"></script> -->
   <!-- <script type="text/javascript" src="js/check.js"></script>-->



    <script type="text/javascript">
        //-------------------
        function changeImg(){	$("#codeImg").attr("src", "user/code.do?timestamp=" + (new Date()).valueOf());}
        
        //-------------------
         function checkname(){
        	var username = $("#username").val();
        	$.post("user/usercheck.do",
					{
						username:username
					},
					function(responseText){
						//$("#errorAccount").html(responseText);
						$("#cnresult").show();
						$("#cnresult").text(responseText);
					});
        }   
        
    </script>
</head>

<body>

<form action="user/login.do" method="post" id="loginForm">


    账号：<input type="text" name="username" id="username" onblur="checkname()"/>
    <span id="cnresult"></span>
    <br> 
    密码：<input type="password" name="password" value="" /></br>
    <span id="errorcontent"></span>
		
    验证码：<input type="text" id="code" name="code"/>
    <img id="codeImg" alt="验证码" src="user/code.do" onclick="changeImg();"/>
    <br>

    <input type="submit"  value="登录" />
    <br>
    <c:if test="${!(empty requestScope.message)}">
    	
    	<font color="red">${message}</font>
    </c:if>


</form>


<!-- <a href="/springmvc04/user/reg.do">111</a> -->
</body>
</html>

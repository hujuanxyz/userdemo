<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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

<title>用户注册</title>




    <script type="text/javascript" src="js/jquery.js"></script>
    <link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/default/easyui.css" type="text/css"/>
    <link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/icon.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/messages_cn.js"></script>


<script type="text/javascript">
	$(function() {

		$().ready(function() {

			$("#signupForm").validate({

				rules : {

					username : {
						required : true,
						minlength : 4,
						maxlength : 16
					},
					email : {
						required : true,
						email : true

					},
					password : {
						required : true,
						minlength : 3,
						maxlength : 16

					},
					repassword : {
						required : true,
						minlength : 3,
						maxlength : 16,
						equalTo : "#password"

					}
				},
				messages : {

					username : {
						required : "请输入用户名",
						minlength : jQuery.format("用户名至少4位！"),
						maxlength : jQuery.format("用户名不能大于16位！")

					},

					email : {
						required : "请输入Email地址",
						email : "请输入正确的email地址"

					},
					password : {
						required : "请输入密码",
						minlength : jQuery.format("密码不能小于{0}个字符"),
						maxlength : jQuery.format("密码不能大于16个字符")
					},
					repassword : {
						required : "请输入确认密码",
						minlength : "确认密码不能小于3个字符",
						maxlength : "确认密码不能大于16个字符",
						equalTo : "两次输入密码不一致不一致"
					}

				}

			});
		});

	})
</script>

</head>

<body>
	<div id="myDiv" class="easyui-panel" style="width:380px;height:230px;" title="用户注册">

		<form id="signupForm" method="post" action="/userdemo/user/addUser.do">
			<p>
				<label for="username">用 户 名：</label> <input id="username"
					name="username" />
			</p>

			<p>
				<label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label> <input
					id="email" name="email" />
			</p>

			<p>
				<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label> <input
					id="password" name="password" type="password" />
			</p>

			<p>
				<label for="repassword">确认密码：</label> <input id="repassword"
					name="repassword" type="password" />
			</p>

			<p style="text-align:center">
				<input class="submit" type="submit" value="注册" />&nbsp;<input
					type="reset" value="重置">
			</p>
		</form>

	</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
</head>
<body bgcolor="#ffe4c4">
<center>
<h1>请输入用户信息</h1>
 <form action="update.do" method="post"> 
    <table border="1">
       <tr><td>用户名:</td><td><input id="username" type="text" name="username"></td></tr> 
       <tr><td>email:</td><td><input id="email" type="text" name="email"></td></tr>      
       <tr><td>密码：</td><td><input id="password" type="password" name="password"></td></tr>
       <tr><td>status:</td><td><input id="status" type="text" name="status"></td></tr>
     <!--   <tr><td>userName</td><td><input type="text" name="username" value="<%=request.getParameter("username")%>"></td></tr>
      <tr><td>password</td><td><input type="password" name="password" value="<%=request.getParameter("password")%>"></td></tr>
      <tr><td>userCountry</td><td><input type="text" name="usercountry" value="<%=request.getParameter("usercountry")%>"></td></tr>-->
    </table>
    <input type="submit" value="修改提交"> 

       <!--<td><a href="update.do?"><font
                color="blue">修改提交</font></a></td>-->
                
  </form>
</center>
</body>
</html>
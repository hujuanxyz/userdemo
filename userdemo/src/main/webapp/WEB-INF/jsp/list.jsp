<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>列表</title>
</head>
<body>
<c:if test="${empty requestScope.users }">
  <font color="red">没有员工信息！</font>
</c:if>
<c:if test="${!(empty requestScope.users)}">
  <table border="1" cellpadding="10" cellspacing="0">
    <tr>
      <th>id</th>
      <th>userName</th>
      <th>email</th>
      <th>password</th>
      <th>status</th>
      <th colspan="2">OP</th>
    </tr>
    <c:forEach items="${requestScope.users }" var="user" varStatus="s">
      <tr>
        <td>${user.id}</td>
        <td>${user.username }</td>
        <td>${user.email }</td>
        <td>${user.password}</td>
        <td>${user.status}</td>
        
        </td>
        <td><a 
               href="edit.do?id=${user.id}"><font
                color="blue">edit</font></a></td>
                
        <td><a
               href="delete.do?id=${user.id}"><font
                color="blue">delete</font></a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<br>
<!-- <a href="addUser.do">addUser</a> -->

<a href="${pageContext.request.contextPath}">goto login</a> 
</body>
</html>
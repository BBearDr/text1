<%--
  Created by IntelliJ IDEA.
  User: chenjx
  Date: 2017/9/21
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="Java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>标题</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
      <%@page import="com.session.util" %>
 </head>
 <body>
    <form action="${pageContext.request.contextPath}/servlet/DoformServlet" method="post">
        <input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
        用户:<input type="text" name="username">
        <input type="submit" value="回来">
    </form>
 </body>
</html>
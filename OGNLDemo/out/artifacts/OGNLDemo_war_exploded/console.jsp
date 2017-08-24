<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: chenjx
  Date: 2017/7/24
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
</head>
<body>
<ul>
    <%--<li>获得user数据：<s:property value="user.name"/></li>--%>
    <li>获得List数据：<s:property value="users"/></li>
    <li>List中的某个属性的集合：<s:property value="users.{name}"/></li>
    <li>List中的某个属性的集合特定值：<s:property value="users[0].{name}"/></li>
    <li>List中的长度：<s:property value="users.size"/></li>
    <hr/>
    <li>过滤：<s:property value="map.{?#this.key==1}[0]"/></li>
        <li>过滤2：<s:property value="users.{^#this.name==张三2}.{name}"/></li>
</ul>
</body>
</html>
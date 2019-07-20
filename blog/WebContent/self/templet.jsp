<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String cpath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>smile in the world</title>
<link rel="stylesheet" href="<%=cpath%>/cascade.css">
<link rel="stylesheet" href="<%=cpath%>/layui/css/layui.css">
<script src = "<%=cpath%>/layui/layui.js" charset="UTF-8"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header layui-bg-black">
    	<div class="layui-logo">Picture</div>
    	<%@include file="header.jsp" %>
	</div>
	<div class="layui-side layui-bg-green">
	<!-- side -->
	<h1>side</h1>
	</div>
	<div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">内容主体区域</div>
  	</div>
	<div class="layui-footer">
    <!-- 底部固定区域 -->
    <%@include file="footer.jsp" %>>
    © smileInTheWorld.com- 底部固定区域
	</div>
</div>
</body>
</html>

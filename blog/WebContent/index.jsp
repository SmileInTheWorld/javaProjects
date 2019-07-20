<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<% String cpath = request.getContextPath(); %>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>smile in the world</title>
<link rel="stylesheet" href="<%=cpath%>/cascade.css">
<link rel="stylesheet" href="<%=cpath%>/layui/css/layui.css">
</head>
<body>
<div class = "outer-wrap">
<div class = "login-panel">
<form class = "layui-form" method = "post" action = "<%=cpath%>/Login">
	<div class="layui-form-item" style = "margin-top:30px">
	  <label class="layui-form-label">用户名</label>
	  <div class="layui-input-inline">
		<input type="text" name="username" required lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" autocomplete="off" class="layui-input">    
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">密码</label>
	  <div class="layui-input-inline">
		<input type="password" name="password" required lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" autocomplete="off" class="layui-input">    
	  </div>
	</div>
	<div class="layui-form-item" style="text-align:center">
	  <div class="layui-input-block">
	  <button class="layui-btn" lay-submit lay-filter="login">登&nbsp;&nbsp;&nbsp;录</button>
	  </div>
	</div>
</form>
</div>
</div>
<script src = "<%=cpath%>/layui/layui.js" charset="UTF-8"></script>
<script>
<%
	String loginResult = (String)request.getSession().getAttribute("loginResult");
	if(null != loginResult && !loginResult.equals("login success")){
%>
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.msg("<%=loginResult%>", {
			time: 1500,
			icon: 2
		});
	});              
<%	
	}
%>
</script>
</body>
</html>
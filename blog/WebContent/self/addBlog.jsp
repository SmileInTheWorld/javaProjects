<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page 
		import="com.smileintheworld.blog.dao.Category"
		import="java.util.List"
%>

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
	
	<div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
		<form class="layui-form" method="post" action="<%=cpath%>/AddBlog">
			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>
    			<div class="layui-input-inline">
    				<input name="title" type="text" name="username" lay-verify="required" lay-reqtext="标题不能为空" placeholder="请输入标题" autocomplete="off" class="layui-input">
    			</div>
    		</div>
			<div class="layui-form-item">
				<label class="layui-form-label">类别</label>
    			<div class="layui-input-inline">
      				<select id="category" name="category" lay-filter="category">
        			<option value="">请选择一个类别</option>
        			</select>
    			</div>
  			</div>
  			<div class="layui-form-item layui-form-text">
    			<label class="layui-form-label">内容</label>
    			<div class="layui-input-block">
    				<textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="content"></textarea>
    			</div>
  			</div>
  			<div class="layui-form-item">
  				<div class="layui-input-block">
  					<input name = "method" style="display:none" value="insert" />
  					<button class="layui-btn" lay-submit lay-filter="save">保存</button>
  					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
  				</div>
  			</div>
    	</form>
    </div>
  	</div>
	<div class="layui-footer">
    <!-- 底部固定区域 -->
    <%@include file="footer.jsp" %>
    © smileInTheWorld.com- 底部固定区域
	</div>
</div>
<script>
layui.use(['form', 'layedit', 'jquery'], function(){
  var $ = layui.$;
<%	List<Category> cl = (List<Category>)session.getAttribute("category_list");
	session.removeAttribute("category_list");
	if(null != cl){ for(Category e: cl){
%>
        $("#category").children().last().after('<option value="'
        				+'<%=e.getId().intValue()%>">'+ '<%=e.getName()%>'
        				+' </option>');		
<% } } %>
  var layedit = layui.layedit;
  var layform = layui.form;
  layedit.build('content'); //建立编辑器
  //layform.render('select','category');
  layform.render();
  layform.on('submit(save)', function(data){
	  console.log(data.form);
	  console.log(data.field);
  });
});
</script>
</body>
</html>

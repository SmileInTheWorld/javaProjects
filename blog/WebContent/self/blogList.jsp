<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page 
		import="com.smileintheworld.blog.dao.Category"
		import="java.util.List"
		import="java.util.Iterator"
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
	<div class="layui-side layui-bg-green">
	<!-- side -->
	<h1>side</h1>
		<ul id="side-nav" class="layui-nav layui-nav-tree">
			<% List<Category> cl = (List<Category>)session.getAttribute("category_list");
				List<Integer> bn = (List<Integer>)session.getAttribute("blognum");
				session.removeAttribute("category_list");
				session.removeAttribute("blognum");
				if(null != cl && null !=bn){
					Iterator it = bn.iterator();
					for(Category e : cl){
						if(it.hasNext()){
			%>
				 			<li id="<%=e.getId()%>" class="layui-nav-item">
				 				<a href="javascript:;"><%=e.getName()+ "(" + it.next() +")"%></a>
				 			</li>
			<%}}}%>
		</ul>
	</div>
	<div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	<table id="bloglist" lay-filter="bloglist"></table>
    </div>
  	</div>
	<div class="layui-footer">
    <!-- 底部固定区域 -->
    <%@include file="footer.jsp" %>>
    © smileInTheWorld.com- 底部固定区域
	</div>
</div>
<script type="text/html" id="handle">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	layui.use(['jquery','table'], function(){
		var $ = layui.$;
		var laytable = layui.table;
		var layer = layui.layer;
		
		//监听click事件，并render对应table
		$("#side-nav li").click(function(){
			var text = $(this).children().text();
			laytable.render({
						elem: "#bloglist"
						,height: 312
						,url: "<%=cpath%>/BlogList"
						,page: true //开启分页
						,where: {
									method: 'show_bloglist',
									category_id: $(this).attr("id"),
									blognum: text.substring(1+text.indexOf("("),text.length-1), 
							}
						,cols: [[ //表头
							{field: 'title', title: '标题', miniwidth: 80, sort: true, fixed: 'left'}
							,{field: 'blog_id', title: 'ID', hide:true} 
							,{field: 'created_time', title: '创建时间', width: 200, sort: true} 
							,{field: 'user', title: '作者', width:80, sort: true} 
							,,{fixed: 'right', title:'操作', toolbar: '#handle', width:150}
							]]
			});
		});
		//监听工具事件
		laytable.on('tool(bloglist)', function(obj){
			//console.log(obj);
			var blog_id = obj.data.id;
			switch(obj.event){
				case "detail":
						window.location.href="<%=cpath%>/BlogList?method=to_showblog&blog_id=" +blog_id;
					break;
				case "edit":
						window.location.href="<%=cpath%>/AddBlog?method=to_updateblog&blog_id=" +blog_id;
					break;
				case "del":
						$.get("<%=cpath%>/AddBlog?method=delete_blog&blog_id=" +blog_id, function(data, status){
							if("success" == status && "delete success"){
								obj.del();
							}else{
							}
						});
					break;
				default:
					break;
			}
			
		});
	});
</script>
</body>
</html>

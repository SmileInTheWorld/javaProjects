<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  首页、博文目录、个人中心-->
<ul class="layui-nav layui-bg-green layui-layout-left">
  <li class="layui-nav-item layui-this"><a href="<%=request.getContextPath()%>/BlogList?method=to_overview">首页</a></li>
  <li class="layui-nav-item"><a href="<%=request.getContextPath()%>/BlogList?method=to_bloglist">产品</a></li>
  <li class="layui-nav-item"><a href="<%=request.getContextPath()%>/AddBlog?method=to_addblog">配置</a></li>
</ul>
<ul class="layui-nav layui-bg-green layui-layout-right">
  <li class="layui-nav-item">
    <a href="javascript:;">个人中心</a>
    <dl class="layui-nav-child">
      <dd><a href="">基本资料</a></dd>
      <dd><a href="">安全设置</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item"><a href="">退出</a></li>
</ul>
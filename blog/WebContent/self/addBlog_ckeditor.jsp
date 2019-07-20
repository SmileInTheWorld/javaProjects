<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% String cpath = request.getContextPath(); %>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>请输入博客内容</h1>
<form method="post" action="<%=cpath%>/AddBlog">
    <table>
        <tr>
            <td>主题：</td>
            <td>
                <input name="title" type="text" id="tile"/>
            </td>
        </tr>
        <tr>
            <td>类别：</td>
            <td>
                <select name = "category_id" >
                    <option value="1">study</option>
                    <option value="2">myself</option>
                    <option value="3">get</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                <textarea name="content" cols="60" rows="8" id="content"> &lt;p&gt;This is some sample content.&lt;/p&gt;</textarea>
            </td>
        </tr>
        <tr>
            <td><input type="reset" name="rbutton" id="rbutton" value="重置"/></td>
            <td><input type="submit" name="sbutton" id="sbutton" value="提交"/></td>
        </tr>
    </table>
</form>
<script>
    ClassicEditor
        .create(document.querySelector('#content' ))
        .catch( error => {
            console.error( error );
        });
</script>

</body>
</html>
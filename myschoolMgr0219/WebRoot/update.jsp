<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="StudentServlet.do?option=update" method="post">
		<table>
			<tr>
				<td>学号</td>
				<td><input type="text" name="studentNo" value="${stu.studentNo}" /></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="studentName" value="${stu.studentName}" /></td>
			</tr>
			<tr>
				<td>性别</td>
				<c:if test="${stu.sex eq '男' or stu.sex eq 'M'}">
					<td>
						<label>男</label><input type="radio" name="sex" value="男" checked="checked" />
						<label>女</label><input type="radio" name="sex" value="女" />
					</td>
				</c:if>
				<c:if test="${stu.sex eq '女' or stu.sex eq 'F'}">
					<td>
						<label>男</label><input type="radio" name="sex" value="男"  />
						<label>女</label><input type="radio" name="sex" value="女" checked="checked" />
					</td>
				</c:if>
			</tr>
			<tr>
				<td>年级</td>
				<td>
					<select name="gradeId">
						<option value="-1">----请选择----</option>
						<c:choose>
							<c:when test="${stu.gradeId == 1}">
								<option value="1" selected="selected">S1</option>
								<option value="2">S2</option>
								<option value="3">Y2</option>
							</c:when>
							<c:when test="${stu.gradeId == 2}">
								<option value="1">S1</option>
								<option value="2" selected="selected">S2</option>
								<option value="3">Y2</option>
							</c:when>
							<c:otherwise>
								<option value="1">S1</option>
								<option value="2">S2</option>
								<option value="3" selected="selected">Y2</option>
							</c:otherwise>	
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="borndate" value="${stu.borndate}" /></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="text" name="email" value="${stu.email}" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="修改" />
					<input type="button" value="取消" />
				</td>
			</tr>
		</table>    
    </form>
  </body>
</html>

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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	
		
	
	</script>
  </head>
  
  <body>
  	<form action="">
  		<table id="tab" border="1" width="800">
  			<tr>
  				<td>学号</td>
  				<td>学生姓名</td>
  				<td>性别</td>
  				<td>年级</td>
  				<td>出生日期</td>
  				<td>操作</td>
  			</tr>
  			<c:forEach var="stuList" items="${stuList}">
  			<tr>
  				<td>${stuList.studentNo}</td>
  				<td>${stuList.studentName}</td>
  				<td>${stuList.sex}</td>
  				<c:if test="${stuList.gradeId == 1}">
  					<td>S1</td>
  				</c:if>
  				<c:if test="${stuList.gradeId == 2}">
  					<td>S2</td>
  				</c:if>
  				<c:if test="${stuList.gradeId == 3}">
  					<td>Y2</td>
  				</c:if>
  				<td>${stuList.borndate}</td>
  				<td>
  					<a onclick="del('${stuList.studentNo}')">删除</a>
  					<a href="StudentServlet.do?stuNo=${stuList.studentNo}&option=findById">修改</a>
  					<a id="del" onclick="delAjax('${stuList.studentNo}',this)">ajax删除</a>
  				</td>
  			</tr>
  			</c:forEach>
  			<tr>
  				<td colspan="6">
  					<a href="add.jsp">新增</a>
  					<!--
  					<a onblur="">
  						<select name="pageSize"  >
		    				<option onclick="blur()" value="10"  >10</option>
		    				<option onclick="blur()" value="15">15</option>
		    				<option onclick="blur()" value="20">20</option>
    					</select>
  					</a>
  					  -->
  					<a href="StudentServlet.do?count=0">首页</a>
  					<a href="StudentServlet.do?count=${count-1}">上一页</a>
  					${count}/${totalCount}
  					<a href="StudentServlet.do?count=${count+1}">下一页</a>
  					<a href="StudentServlet.do?count=${totalCount}">尾页</a>
  				</td>
  			</tr>
  		</table>
  	</form>
    
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#tab tr:odd").css("background-color","gray");
  	})
  
  	function blur(){
  		window.location.href = "StudentServlet.do?pageSize=" ;
  	}
  	
	function del(stuNo){
	
		var flag = window.confirm("是否删除");
		
		if(flag == true){
			window.location.href = "StudentServlet.do?option=del&stuNo="+stuNo;
		}
	}  
	
	function delAjax(stuNo,element){
		var flag = window.confirm("是否删除");
		if(flag == true){
			$.ajax({
				url:"StudentServlet.do",
				data:"option=delAjax&stuNo="+stuNo,
				datatype:"text",
				type:"post",
				success:function(result){
					if(result > 0){
						alert("删除成功！！！");
						$(element).parent().parent().remove();
					}else{
						alert("删除失败！！！");
					}
				}
			})
		}
	}
  	/*
  	$(function(){
  	
  		$("#del").click(function(){
  			//通过jquery的节点操作获取stuNo
  			//var stuNo = $("").
  		
  			$.ajax({
				url:"StudentServlet.do",
				data:"option=delAjax&stuNo="+stuNo,
				datatype:"text",
				type:"post",
				success:function(result){
					if(result > 0){
						alert("删除成功！！！");
						$(this).parent().parent().remove();
					}else{
						alert("删除失败！！！");
					}
				}
			})
  		})
  	})
  	*/
  	
  	
  </script>
</html>

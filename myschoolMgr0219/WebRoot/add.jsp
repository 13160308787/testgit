<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
  </head>
  
  <body>
    <form action="StudentServlet.do?option=add" method="post">
		<table>
			<tr>
				<td>学号</td>
				<td><input type="text" onblur="ckStuNo()" id="studentNo" name="studentNo" /></td>
				<td id="ckStuNoText"></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="studentName" id="studentName"/></td>
				<td></td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<label>男</label><input type="radio" name="sex" value="男" checked="checked"/>
					<label>女</label><input type="radio" name="sex" value="女" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td>年级</td>
				<td>
					<select name="gradeId" id="gradeId">
						<option value="-1" >----请选择----</option>
						<option value="1">S1</option>
						<option value="2">S2</option>
						<option value="3">Y2</option>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="borndate" id="borndate"/></td>
				<td></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="text" name="email" id="email"/></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="button" onclick="student()" value="新增" />
					<input type="button" value="取消" />
				</td>
			</tr>
		</table>    
    </form>
  </body>
  <script type="text/javascript">
  
  	function ckStuNo(){
  	
  		//当失去焦点的时候验证当前填写的学号在我们的数据库中是否存在
  		
  		//1.获取要验证的学号 获取文本框的值  .val()  获取标签之间的内容 .html()或者.text()
  		var stuNo = $("#studentNo").val();
  		//2.通过ajax传递请求
  		$.ajax({
  			//如果提交的地址和参数写在一起的话 别忘记了?
  			//如果分开来写，则问号不可以加
  			url:"StudentServlet.do?option=ckStuNo&stuNo="+stuNo,
  			type:"post",
  			datatype:"text",
  			success:function(result){
  				if(result == 0){
  					$("#ckStuNoText").html("学号可用");
  				}else{
  					$("#ckStuNoText").html("学号不可用");
  				}
  				
  			}
  		})
  	}
  	function student(){
  		
  		
  		if($("#studentNo").val()==null||$("#studentNo").val()==""){
  			alert("请输入学号");
  		}else if($("#studentName").val()==null||$("studentName").val()==""){
  			alert("请输入姓名");
  		}else if($("#gradeId").val()==-1){
  			alert("请选择年级");
  		}
  	}
  
  </script>
</html>


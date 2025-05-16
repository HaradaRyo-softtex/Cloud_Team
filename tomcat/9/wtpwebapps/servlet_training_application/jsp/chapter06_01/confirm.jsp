<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>confirm.jsp</title>
</head>
<body>
	<h2>Chapter06_01_confirm</h2>
	<form action="${pageContext.request.contextPath}/Chapter06_01/complete" method="post">
		学籍番号：${student.studentId}<br> 
		名前：${student.name}<br>
		性別：${student.gender}<br> 
		学年：${student.grade}年<br>
		<input type="hidden" name="studentId" value="${student.studentId}" /> 
		<input type="hidden" name="name" value="${student.name}" /> 
		<input type="hidden" name="gender" value="${student.gender}" /> 
		<input type="hidden" name="grade" value="${student.grade}" /> 
		<input type="submit" value="更新確定" />
	</form>
</body>
</html>

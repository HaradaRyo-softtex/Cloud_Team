<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>練習問題8</title>
</head>
<body>
	<article class="main">
		<h2>Chapter08_03</h2>
		<form action="<%=request.getContextPath()%>/Chapter08_03" method="post">
			<label>学籍番号:</label><input type="text" name="studentId" required /><br>
			<label>名前:</label><input type="text" name="name" required /><br>
			<label>性別:</label> 
			<input type="radio" name="gender" value="1"checked />男性 
			<input type="radio" name="gender" value="2" />女性<br>
			<label>学年:</label> 
			<select name="grade">
				<option value="1">1年</option>
				<option value="2">2年</option>
				<option value="3">3年</option>
			</select><br>
			<input type="submit" value="登録" />
		</form>
	</article>
</body>
</html>
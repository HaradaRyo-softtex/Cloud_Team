<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッションカウント</title>
</head>
<body>
	<h2>現在のカウント：</h2>
	<p>
		<%=session.getAttribute("count")%>
	</p>
</body>
</html>

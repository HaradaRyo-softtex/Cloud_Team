<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scriptlet test</title>
</head>
<body>
	<%
		String countStr = "回目です";
		for (int i = 1; i <= 5; i++) {
	%>
	<%=i + countStr%>
		<br />
	<%
	}
	%>
</body>
</html>
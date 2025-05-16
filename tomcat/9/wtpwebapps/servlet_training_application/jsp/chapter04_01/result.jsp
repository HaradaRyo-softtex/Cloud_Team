<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Dog" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<%=request.getContextPath()%>/css/style.css"
		rel="stylesheet" />
	<title>Chapter04_01</title>
</head>
<body>
	名前： ${dog.dogName}<br/>
	年齢： ${dog.age}歳<br/>
	体重： ${dog.weight} kg<br/>
</body>
</html>

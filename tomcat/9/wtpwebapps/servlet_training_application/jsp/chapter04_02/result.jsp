<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Dog" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="<%=request.getContextPath()%>/css/style.css"
		rel="stylesheet" />
	<title>Chapter04_02</title>
</head>
<body>
	名前： ${dog.dogName}<br/>
	年齢： ${dog.age}歳<br/>
	体重： ${dog.weight} kg<br/>
	
	<c:choose>
		<c:when test="${dog.weight >= 10.0 }">
			<p>もうちょっと痩せなさい</p>
		</c:when>
		<c:when test="${dog.weight <= 5.0 }">
			<p>もう少し太りましょう</p>
		</c:when>
	</c:choose>
</body>
</html>

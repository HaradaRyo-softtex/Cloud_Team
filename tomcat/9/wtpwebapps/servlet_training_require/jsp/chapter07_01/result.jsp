<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter07_01</title>
</head>
<body>
	<h2>選んだ好きな音楽ジャンル</h2>
	<c:forEach var="MusicName" items="${MusicList}">
		<p>${MusicName}</p>
	</c:forEach>
	<form
		action="<%=request.getContextPath()%>/jsp/chapter07_01/input.jsp"
		method="post">
		<input type="submit" value="戻る" />
	</form>
</body>
</html>
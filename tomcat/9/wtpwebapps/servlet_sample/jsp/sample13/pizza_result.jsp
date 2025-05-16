<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>シェアードピザ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<article class="main">
		<h2>${message}</h2>
		<form action="<%=request.getContextPath()%>/connect13" method="get">
			<input type="submit" value="接続画面に戻る" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
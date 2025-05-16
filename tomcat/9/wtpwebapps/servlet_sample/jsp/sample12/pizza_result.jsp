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
		<h2>${typeName}メニューのラインナップです！</h2>
		<c:forEach var="foodName" items="${foodList}">
			<p>${foodName}</p>
		</c:forEach>
		<form
			action="<%=request.getContextPath()%>/jsp/sample12/pizza_search.jsp"
			method="post">
			<input type="submit" value="検索画面に戻る" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
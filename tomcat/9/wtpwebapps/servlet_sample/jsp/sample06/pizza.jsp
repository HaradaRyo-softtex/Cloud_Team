<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>シェアードピザへようこそ！</h2>
		<%=request.getAttribute("pizza")%>が注文されました。
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
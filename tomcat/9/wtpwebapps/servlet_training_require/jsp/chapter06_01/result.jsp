<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter06_01</title>
</head>
<body>

	<article class="main">
		<h2>Chapter06_01</h2>
			<p>結果:<%= request.getAttribute("message") %></p>
	</article>
	
</body>
</html>
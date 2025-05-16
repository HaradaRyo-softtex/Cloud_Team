<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter05_04</title>
</head>
<body>

	<article class="main">
		<h2>Chapter05_04</h2>
			<p>メッセージ:<%= request.getAttribute("message") %></p>
	</article>
	
</body>
</html>
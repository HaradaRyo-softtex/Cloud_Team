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
		<form action="<%=request.getContextPath()%>/Chapter05_04">
			メッセージ：<input type="text" name="message" />
			<input type="submit" value="送信" />
		</form>
	</article>
	
</body>
</html>
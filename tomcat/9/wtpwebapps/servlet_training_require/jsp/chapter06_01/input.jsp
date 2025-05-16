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
		<h3>次のうち、1964年の東京オリンピックで一番金メダルを獲得したのはどこの国でしょう？</h3>
		<form action="<%=request.getContextPath()%>/Chapter06_01" method="post">
			<input type="radio" name="country" value="1">日本<br>
			<input type="radio" name="country" value="2">ロシア<br>
			<input type="radio" name="country" value="3">アメリカ<br>
			<input type="radio" name="country" value="4">中国<br>
			<input type="submit" value="送信" />
		</form>
	</article>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter05_03</title>
</head>
<body>

	<article class="main">
		<h2>Chapter05_02</h2>
		<p>年齢確認をします。年齢を入力してください</p>
		<form action="<%=request.getContextPath()%>/Chapter05_03" method="get">
			<input type="number" name="age" />
			<input type="submit" value="送信" />
		</form>
	</article>
	
</body>
</html>
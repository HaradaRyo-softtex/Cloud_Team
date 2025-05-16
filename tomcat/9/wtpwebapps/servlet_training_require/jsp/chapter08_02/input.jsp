<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>練習問題8</title>
</head>
<body>
	<article class="main">
		<h2>Chapter08_02</h2>
		<form action="<%=request.getContextPath()%>/Chapter08_02" method="get">
			学籍番号:<input type="text" name="SearchName" />
			<input type="submit" value="検索" />
		</form>
	</article>
</body>
</html>
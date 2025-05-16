<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter06_02</title>
</head>
<body>

	<article class="main">
		<h2>Chapter06_02</h2>
		<h3>犬のデータを入力してください</h3>
		<form action="<%=request.getContextPath()%>/Chapter06_02" method="post">
			名前：<input type="text" name="dogName"><br>
			年齢：<input type="number" name="age"><br>
			体重：<input type="text" name="weight"><br>
			<input type="submit" value="送信" />
		</form>
	</article>

</body>
</html>

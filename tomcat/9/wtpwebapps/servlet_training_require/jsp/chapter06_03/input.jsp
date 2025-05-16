<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter06_03</title>
</head>
<body>

	<article class="main">
		<h2>Chapter06_03</h2>
		<form action="<%=request.getContextPath()%>/Chapter06_03" method="post">
			名前：<input type="text" name="name"><br>
			パスワード：<input type="password" name="pass"><br>
			<input type="submit" value="ログイン" />
		</form>
	</article>

</body>
</html>

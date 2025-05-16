<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
</head>
<body>
	<h2>登録結果</h2>
	<c:choose>
		<c:when test="${result == 'true'}">
			<p>登録が成功しました。</p>
		</c:when>
		<c:otherwise>
			<p>登録に失敗しました。</p>
		</c:otherwise>
	</c:choose>
</body>
</html>

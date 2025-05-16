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
		<h2>Sample18</h2>
		<h3>削除したい商品の商品番号を入力してください。</h3>
		<form action="<%=request.getContextPath()%>/check18" method="post">
			<p>
				商品番号：<input type="text" name="foodId" />
			</p>
			<input type="submit" value="削除確認" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
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
		<h2>商品情報を削除しました。</h2>
		<form
			action="<%=request.getContextPath()%>/jsp/sample18/pizza_delete.jsp"
			method="post">
			<input type="submit" value="入力画面に戻る" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
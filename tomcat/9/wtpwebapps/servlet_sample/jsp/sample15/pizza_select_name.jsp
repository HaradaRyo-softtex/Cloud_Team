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
		<h2>Sample15</h2>
		<h3>商品名でメニューを検索します。</h3>
		<form action="<%=request.getContextPath()%>/selectname15" method="get">
			<input type="text" name="foodName" /> <input type="submit"
				value="検索" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

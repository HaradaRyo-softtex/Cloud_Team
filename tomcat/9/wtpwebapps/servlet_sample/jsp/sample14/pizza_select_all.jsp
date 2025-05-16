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
		<h2>Sample14</h2>
		<h3>商品を全件出力します。</h3>
		<form action="<%=request.getContextPath()%>/selectall14" method="get">
			<input type="submit" value="全件出力" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
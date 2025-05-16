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
		<h2>Sample12</h2>
		<h3>検索したいメニューの種類を選んでください。</h3>
		<form action="<%=request.getContextPath()%>/search12" method="post">
			<select name="type">
				<option value="001">ピザ</option>
				<option value="002">パスタ</option>
				<option value="003">サイドメニュー</option>
				<option value="004">ドリンク</option>
			</select> <input type="submit" value="検索" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
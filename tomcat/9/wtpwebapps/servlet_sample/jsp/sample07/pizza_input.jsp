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
		<h2>Sample07</h2>
		<h3>食べたいピザを入力してください。</h3>
		<form action="<%=request.getContextPath()%>/bakery07" method="post">
			<select name="item">
				<option value="A001">みんなのマルゲリータ</option>
				<option value="A002">ジューシーソーセージ</option>
				<option value="A003">トロピカーナ</option>
				<option value="A004">明太シーフード</option>
				<option value="A005">冬のファンタジー</option>
			</select> <input type="submit" value="注文" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

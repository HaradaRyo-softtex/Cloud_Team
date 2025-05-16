<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2>商品名検索の結果</h2>
		<table>
			<tr>
				<th>商品番号</th>
				<th>商品名</th>
				<th>価格（円）</th>
				<th>商品の種類</th>
			</tr>
			<c:forEach var="food" items="${foodList}">
				<tr>
					<td>${food.foodId}</td>
					<td>${food.foodName}</td>
					<td>${food.price}</td>
					<td>${food.type.typeName}</td>
				</tr>
			</c:forEach>
		</table>
		<form
			action="<%=request.getContextPath()%>/jsp/sample15/pizza_select_name.jsp"
			method="post">
			<input type="submit" value="前の画面に戻る" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

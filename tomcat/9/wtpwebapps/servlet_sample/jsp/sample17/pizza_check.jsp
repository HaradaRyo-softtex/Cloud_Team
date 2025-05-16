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
		<h2>以下の内容に変更します。よろしいですか？</h2>
		<table>
			<tr>
				<th>商品番号</th>
				<th>商品名</th>
				<th>価格（円</th>
				<th>商品の種類</th>
			</tr>
			<tr>
				<td>${food.foodId}</td>
				<td>${food.foodName}</td>
				<td>${food.price}</td>
				<td>${food.type.typeName}</td>
			</tr>
		</table>
		<form action="<%=request.getContextPath()%>/update17" method="get">
			<input type="hidden" name="foodId" value="${food.foodId}" /> <input
				type="hidden" name="foodName" value="${food.foodName}" /> <input
				type="hidden" name="price" value="${food.price}" /> <input
				type="hidden" name="typeId" value="${food.type.typeId}" /> <input
				type="submit" value="変更確定" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

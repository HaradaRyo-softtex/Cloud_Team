<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.bean1.FoodData"%>
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
		<h2>シェアードピザへようこそ！</h2>
		<c:if test="${food.foodId != null}"> 
			商品番号：${food.foodId}<br /> 
			商品名  ：${food.foodName}<br /> 
			価格    ：${food.price}<br /> 
			を注文しました。 
		</c:if>
		<c:if test="${food.foodId == null}">
			<h3 class="error">※申し訳ございません。こちらの商品はただいま在庫切れになって おります。</h3>
			<form
				action="<%=request.getContextPath()%>/jsp/sample11/pizza_input.jsp"
				method="post">
				<input type="submit" value="別の商品を選択する" />
			</form>
		</c:if>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.bean1.FoodData"%>
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
		<p><%=session.getAttribute("loginName")%>さん</p>
		<h2>シェアードピザへようこそ！</h2>
		<%
		FoodData food = (FoodData) request.getAttribute("food");
		%>
		商品番号：<%=food.getFoodId()%><br /> 
		商品名 ：<%=food.getFoodName()%><br />
		価格 ：<%=food.getPrice()%><br /> 
		が注文されました。 
		<br />
		<form action="<%=request.getContextPath()%>/bakery09" method="post">
			<input type="submit" value="ログアウト" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

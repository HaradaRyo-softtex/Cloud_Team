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
		<h2>Sample17</h2>
		<h3>変更したい商品情報を入力してください。</h3>
		<form action="<%=request.getContextPath()%>/check17" method="get">
			<p>
				商品番号：<input type="text" name="foodId" />
			</p>
			<p>
				商品名：<input type="text" name="foodName" />
			<p>
			<p>
				価格（円）：<input type="text" name="price" />
			</p>
			<p>
				商品の種類：<select name="typeId">
					<option value="001">pizza</option>
					<option value="002">pasta</option>
					<option value="003">side</option>
					<option value="004">drink</option>
				</select>
			</p>
			<input type="submit" value="変更確認" />
		</form>
	</article>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
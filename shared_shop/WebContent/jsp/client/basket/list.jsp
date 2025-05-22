<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title><%=Constant.TOP%> | <%=Constant.SHOP_TITLE%></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/basket.css">
</head>
<body class="user index">
	<%@ include file="/jsp/common/header.jsp"%>
	<%@ include file="/jsp/common/navi.jsp"%>

	<div class="container side_wrap">
		<%@ include file="/jsp/common/sidebar.jsp"%>
		<article class="main">
			<c:if test="${empty basket}">
				<p style="font-size: 16px;"><%=MSGConstant.MSG_BASKET_LIST_NONE%></p>
			</c:if>

			<c:if test="${not empty basket}">
				<h2 class="title">買い物かご</h2>

				<!-- 追加時在庫エラー -->
				<c:if test="${not empty sessionScope.addErrorItemName}">
					<p class="error">
						「${sessionScope.addErrorItemName}」<%=MSGConstant.MSG_BASKET_STOCK_SHORT%></p>
					<c:remove var="addErrorItemName" scope="session" />
				</c:if>

				<!-- 表示時に在庫0の警告 -->
				<c:forEach var="item" items="${basket}">
					<c:if test="${item.stock == 0}">
						<p class="error">
							「${item.name}」<%=MSGConstant.MSG_BASKET_STOCK_NONE%></p>
					</c:if>
				</c:forEach>

				<table class="basket_table">
					<thead>
						<tr>
							<th>商品名</th>
							<th>数量</th>
							<th>操作</th>
							<th>在庫状況</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${basket}">
							<tr>
								<td><a
									href="${pageContext.request.contextPath}/item/detail?id=${item.id}">${item.name}</a></td>
								<td>${item.orderNum}</td>
								<td>
									<form action="${pageContext.request.contextPath}/basket/delete"
										method="post">
										<input type="hidden" name="itemId" value="${item.id}" /> 
										<input type="submit" value="削除" class="btn-red" />
									</form>
								</td>
								<td><c:choose>
										<c:when test="${item.stock >= 6}">在庫あり</c:when>
										<c:when test="${item.stock >= 1 && item.stock <= 5}">在庫数: ${item.stock}</c:when>
										<c:otherwise>在庫切れ</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="actions">
					<a href="${pageContext.request.contextPath}/checkout"
						class="btn-white">ご注文のお手続き</a>
					<form action="${pageContext.request.contextPath}/basket/deleteAll"
						method="post">
						<input type="submit" value="<%=MSGConstant.MSG_BASKET_EMPTY%>" class="btn-red" />
					</form>
				</div>
			</c:if>
		</article>
	</div>

	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>

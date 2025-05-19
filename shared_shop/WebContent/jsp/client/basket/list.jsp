<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/head.jsp"%>
<title><%=Constant.TOP%> | <%=Constant.SHOP_TITLE%></title>
</head>
<body class="user index">
	<%@ include file="/jsp/common/header.jsp"%>
	<%@ include file="/jsp/common/navi.jsp"%>

	<div class="container side_wrap">
		<%@ include file="/jsp/common/sidebar.jsp"%>
		<article class="main">
			<!-- 買い物かごが空の場合 -->
			<c:if test="${empty basket}">
				<p><%=MSGConstant.MSG_BASKET_EMPTY%></p>
			</c:if>

			<!-- 買い物かごに商品がある場合 -->
			<c:if test="${not empty basket}">
				<h2 class="title">買い物かご</h2>
				<table class="basket_table">
					<thead>
						<tr>
							<th>商品名</th>
							<th>価格</th>
							<th>数量</th>
							<th>小計</th>
							<th>削除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${basket}">
							<tr>
								<td>${item.name}</td>
								<td>${item.price}</td>
								<td>
									<form action="${pageContext.request.contextPath}/basket/update"
										method="post">
										<input type="number" name="quantity" value="${item.orderNum}"
											min="1" max="${item.stock}" /> <input type="hidden"
											name="itemId" value="${item.id}" /> <input type="submit"
											value="更新" />
									</form>
								</td>
								<td>${item.price * item.orderNum}</td>
								<td>
									<form action="${pageContext.request.contextPath}/basket/delete"
										method="post">
										<input type="hidden" name="itemId" value="${item.id}" /> <input
											type="submit" value="削除" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="total">
					<p>合計金額: ${total}</p>
				</div>

				<div class="actions">
					<a href="${pageContext.request.contextPath}/checkout" class="btn">ご注文のお手続き</a>
					<form action="${pageContext.request.contextPath}/basket/clear"
						method="post">
						<input type="submit" value="買い物かごを空にする" />
					</form>
				</div>
			</c:if>
		</article>
	</div>

	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>

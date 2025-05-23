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

			<!-- 買い物かごが空の場合の表示 -->
			<c:if test="${empty basket}">
				<p><%=MSGConstant.MSG_BASKET_LIST_NONE%></p>
			</c:if>

			<!-- 買い物かごに商品がある場合の表示 -->
			<c:if test="${not empty basket}">
				<h2 class="title">買い物かご</h2>

				<!-- 商品追加時に在庫切れエラーが発生した場合のメッセージ表示 -->
				<c:if test="${not empty sessionScope.addErrorItemName}">
					<p class="error">
						「${sessionScope.addErrorItemName}」<%=MSGConstant.MSG_BASKET_STOCK_SHORT%></p>
					<!-- メッセージ表示後にセッションから削除 -->
					<c:remove var="addErrorItemName" scope="session" />
				</c:if>

				<!-- 買い物かご表示時に在庫0のアイテムに対して警告を表示 -->
				<c:forEach var="item" items="${basket}">
					<c:if test="${item.stock == 0}">
						<p class="error">
							「${item.name}」<%=MSGConstant.MSG_BASKET_STOCK_NONE%></p>
					</c:if>
				</c:forEach>

				<!-- 買い物かごの商品一覧表示 -->
				<table class="list_table basket">
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
								<!-- 商品詳細ページへのリンク付き商品名 -->
								<td>
									<a href="${pageContext.request.contextPath}/item/detail?id=${item.id}">
										${item.name}
									</a>
								</td>

								<!-- 注文数量 -->
								<td>${item.orderNum}</td>

								<!-- 商品削除ボタン（POST） -->
								<td>
									<form action="${pageContext.request.contextPath}/basket/delete" method="post">
										<input type="hidden" name="itemId" value="${item.id}" />
										<input type="submit" value="削除" class="delete" />
									</form>
								</td>

								<!-- 在庫状況の表示 -->
								<td>
									<c:choose>
										<c:when test="${item.stock >= 6}">在庫あり</c:when>
										<c:when test="${item.stock >= 1 && item.stock <= 5}">在庫数: ${item.stock}</c:when>
										<c:otherwise>在庫切れ</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- 注文手続きボタン（注文情報入力画面へ遷移） -->
				<form action="${pageContext.request.contextPath}/order/address/input" method="post">
					<input type="submit" value="ご注文のお手続き" class="" />
				</form>

				<!-- 買い物かごを空にするボタン -->
				<form action="${pageContext.request.contextPath}/basket/deleteAll" method="post">
					<input type="submit" value="<%=MSGConstant.MSG_BASKET_EMPTY%>" class="delete" />
				</form>

			</c:if>
		</article>
	</div>

	<!-- フッターの読み込み -->
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>

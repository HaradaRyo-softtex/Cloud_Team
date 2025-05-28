@@ -0,0 +1,56 @@
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title>お気に入り商品一覧 | <%=Constant.SHOP_TITLE%>
</title>
</head>
<body class="user index">
	<%@include file="/jsp/common/header.jsp"%>
	<%@include file="/jsp/common/navi.jsp"%>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp"%>
		<article class="main">
			<h2 class="title">お気に入り商品一覧</h2>
			<br>

			<div class="list_btn_wrap">
				<c:if test="${favoriteItems == null || favoriteItems.size() == 0}">
					<p><%=MSGConstant.MSG_ITEM_CLIENT_LIST_NONE%></p>
				</c:if>
			</div>

			<c:if test="${favoriteItems.size() > 0}">
				<table class="list_table item_list">
					<tr>
						<th><%=Constant.DATA_ITEM_IMAGE%></th>
						<th><%=Constant.DATA_ITEM_NAME%></th>
						<th><%=Constant.DATA_ITEM_PRICE%></th>
						<th><%=Constant.CATEGORY%></th>
					</tr>
					<c:forEach var="itemBean" items="${favoriteItems}">
						<tr>
							<td><img
								src="<%= request.getContextPath() %>/img/${itemBean.image}"
								style="max-width: 100px; max-height: 100px; width: auto; height: auto;" />
							</td>
							<td><a
								href="<%= request.getContextPath() %>/item/detail?id=${itemBean.id}">
									<c:out value="${itemBean.name}" />
							</a></td>
							<td>${itemBean.price}</td>
							<td><c:out value="${itemBean.categoryName}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
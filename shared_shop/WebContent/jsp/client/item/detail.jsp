<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title><%=Constant.ITEM%>詳細 | <%=Constant.SHOP_TITLE%></title>
</head>
<body class="user index">
	<%@include file="/jsp/common/header.jsp"%>
	<%@include file="/jsp/common/navi.jsp"%>

	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp"%>

		<article class="main">
			<h2 class="title">
				<%=Constant.ITEM%>詳細
			</h2>

			<div class="item_info_form_area">
				<div class="item_detail_area">
					<!-- 商品画像とお気に入りボタン -->
					<div class="item_image_area" style="position: relative; width: 45%;">
						<img src="<%=request.getContextPath()%>/img/${itemDetailBean.image}" 
						     style="width: 100%; max-width: 300px; height: auto; max-height: 300px;" />

						<!-- お気に入りボタン -->
						<button id="favoriteButton"
						        data-item-id="${itemDetailBean.id}"
						        data-logged-in="${user != null}"
						        style="position: absolute; top: 8px; background: none; border: none; padding: 0; cursor: pointer;">
							<img id="favoriteImg"
							     src="<%=request.getContextPath()%>/img/${itemDetailBean.favorite ? "heart_pink.png" : "heart.png"}"
							     alt="お気に入り"
							     style="width: 32px; height: 32px; transition: transform 0.2s ease;" />
						</button>
					</div>

					<!-- 商品詳細 -->
					<dl class="item_detail_list">
						<dt><%=Constant.DATA_ITEM_NAME%></dt>
						<dd><c:out value="${itemDetailBean.name}" /></dd>

						<dt><%=Constant.DATA_ITEM_PRICE%></dt>
						<dd>${itemDetailBean.price}</dd>

						<dt><%=Constant.DATA_ITEM_STOCK%></dt>
						<dd>
							<c:if test="${itemDetailBean.stock==0}">
								<%=Constant.STOCK_ZERO%>
							</c:if>
							<c:if test="${itemDetailBean.stock>0 && itemDetailBean.stock<=5}">
								${itemDetailBean.stock}
							</c:if>
							<c:if test="${itemDetailBean.stock>5}">
								<%=Constant.STOCK_RICH%>
							</c:if>
						</dd>

						<dt><%=Constant.CATEGORY%></dt>
						<dd><c:out value="${itemDetailBean.categoryName}" /></dd>

						<dt><%=Constant.DATA_DESCRIPTION%></dt>
						<dd><c:out value="${itemDetailBean.description}" /></dd>
					</dl>
				</div>
			</div>

			<div class="detail_button_area">
				<c:if test="${itemDetailBean.stock>0 && user==null}">
					<form action="<%=request.getContextPath()%>/login">
						<input type="submit" value="買い物かごに入れる" />
					</form>
				</c:if>

				<c:if test="${itemDetailBean.stock>0 && user!=null}">
					<form action="<%=request.getContextPath()%>/basket/add" method="post">
						<input type="hidden" name="id" value="${itemDetailBean.id}" />
						<input type="hidden" name="name" value="${itemDetailBean.name}" />
						<input type="hidden" name="stock" value="${itemDetailBean.stock}" />
						<input type="submit" value="買い物かごに入れる" />
					</form>
				</c:if>

				<form action="<%=request.getContextPath()%>/item/list" class="user_info_form">
					<input type="hidden" name="sortType" value="${param.sortType}" />
					<input type="submit" value="戻る" class="back_button" />
				</form>
			</div>
		</article>
	</div>

	<%@include file="/jsp/common/footer.jsp"%>

	<!-- 変数をJSへ渡す -->
	<script>const contextPath = "<%=request.getContextPath()%>";</script>

	<!-- 外部JS -->
	<script src="<%=request.getContextPath()%>/javascript/favorite-toggle.js"></script>
</body>
</html>

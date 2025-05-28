<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<style>
/* お気に入りリンクアイコン */
.header-favorite-link {
	text-decoration: none;
	display: inline-block;
	margin-right: 8px;
	transition: filter 0.2s ease, background-color 0.2s ease;
	padding: 4px;
	border-radius: 5px;
}

.header-favorite-link:hover {
	background-color: #12526d; /* 背景色を濃く */
}

.header-favorite-link:hover img {
	filter: brightness(1.5);
}

/* アイコンサイズ */
.favorite-icon-small {
	width: 20px;
	height: 20px;
}

</style>
<header>
	<div class="container">
		<h1 class="site_title">
			<%=Constant.SHOP_TITLE%>
		</h1>
		<div class="user_info">
			<c:choose>
				<c:when test="${user==null}">
					<a href="<%=request.getContextPath()%>/login" class="user-link">
						<%=Constant.LOGIN%>
					</a>
					|
					<a href="<%=request.getContextPath()%>/user/regist/input" class="user-link">
						<%=Constant.CLIENT_REGIST%>
					</a>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${user.authority==Constant.AUTH_CLIENT}">
							<%-- お気に入りページリンク先 --%>
							<a href="<%=request.getContextPath()%>/favorite/list"
							   class="header-favorite-link">
								<img src="<%=request.getContextPath()%>/img/heart.png"
								     alt="お気に入り一覧"
								     class="favorite-icon-small">
							</a>
							<a href="<%=request.getContextPath()%>/user/detail" class="user-link">
								<c:out value="${user.name}"/>
							</a>さん
						</c:when>
						<c:otherwise>
							<a href="<%=request.getContextPath()%>/admin/user/detail?id=${user.id}" class="user-link">
								<c:out value="${user.name}"/>
							</a>さん
						</c:otherwise>
					</c:choose>
					|
					<a href="<%=request.getContextPath()%>/logout" class="user-link">
						<%=Constant.LOGOUT%>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>

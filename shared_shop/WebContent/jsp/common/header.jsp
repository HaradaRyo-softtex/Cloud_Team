<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<header>
	<div class="container">
		<h1 class="site_title">
			<%=Constant.SHOP_TITLE%>
		</h1>
		<div class="user_info">
		<c:choose>
			<c:when test="${user==null}">
			<a href="<%=request.getContextPath()%>/login">
				<%=Constant.LOGIN%>
			</a>
			|
			<a href="<%=request.getContextPath()%>/user/regist/input">
				<%=Constant.CLIENT_REGIST%>
			</a>
			</c:when>
			<c:otherwise>
				<c:choose>
				<c:when test="${user.authority==Constant.AUTH_CLIENT}">
					<a href="<%=request.getContextPath()%>/user/detail">
						<c:out value="${user.name}"/>
					</a>さん
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/admin/user/detail?id=${user.id}">
						<c:out value="${user.name}"/>
					</a>さん
				</c:otherwise>
				</c:choose>
				|
				<a href="<%=request.getContextPath()%>/logout">
					<%=Constant.LOGOUT%>
				</a>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>

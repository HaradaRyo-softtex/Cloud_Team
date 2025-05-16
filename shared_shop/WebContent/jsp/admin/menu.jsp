<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>  
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>  
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ADMIN_MENU%> | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body class="admin admin_menu">
	<%@include file="/jsp/common/header.jsp" %>

	<article class="container content">
		<dl class="admin_menu_list">
			<dt class="title no_shadow">
				<%=Constant.ADMIN_MENU%>
			</dt>
			<dd class="link_area">
				<a href="<%=request.getContextPath()%>/admin/user/list">
					<%=Constant.USER%>一覧表示
				</a>
			</dd>
			<c:if test="${user.authority==Constant.AUTH_ADMIN}">
			<dd class="link_area">
				<a href="<%=request.getContextPath()%>/admin/order/list">
					<%=Constant.ORDER%>一覧表示
				</a>
			</dd>
			<dd class="link_area">
				<a href="<%=request.getContextPath()%>/admin/item/list">
					<%=Constant.ITEM%>一覧表示
				</a>
			</dd>
			<dd class="link_area">
				<a href="<%=request.getContextPath()%>/admin/category/list">
					<%=Constant.CATEGORY%>一覧表示
				</a>
			</dd>
			</c:if>
		</dl>
	</article>
	<%@include file="/jsp/common/footer.jsp" %>

</body>
</html>
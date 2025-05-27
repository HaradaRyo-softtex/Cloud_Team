<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.TOP%> | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user index">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<c:if test="${itemBeanList.size()==0}">

			 <%--閲覧できる商品情報がない --%>
				<p><%=MSGConstant.MSG_ITEM_CLIENT_LIST_NONE %>
			</c:if>
			<c:if test="${itemBeanList.size()>0}">
			 <%--閲覧できる商品情報がある --%>
				<h2 class="title">
				<c:if test="${sortType==Constant.SORT_HOTSELL}">
					<%=Constant.SORT_HOTSELL_TITLE %>
				</c:if>
				<c:if test="${sortType!=Constant.SORT_HOTSELL}">
					<%=Constant.SORT_LATEST_TITLE %>
				</c:if>	
				</h2>
				<div class="item_wrap">
			      <c:forEach var="itemBean" items="${itemBeanList}" begin="0" end="9">
			        <div class="item">
						<div class="item_name">	<c:out value="${itemBean.name}"/></div>
						<a href="<%=request.getContextPath()%>/item/detail?id=${itemBean.id}">
							<img src="<%=request.getContextPath()%>/img/${itemBean.image}" />
						</a>
					</div>
			      </c:forEach>  
				</div>
			</c:if>
		       
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
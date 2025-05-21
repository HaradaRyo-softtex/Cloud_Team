<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="jp.co.sss.shop.constant.Constant" %>
<nav class="navi_area">
	<ul class="navi_menu">
		<li>
			<a href="<%=request.getContextPath()%>/">
				<%=Constant.TOP%>
			</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/item/list?sort=<%=Constant.SORT_LATEST%>" >
				<%=Constant.ITEM%>一覧
				<input type="hidden" name="sortType" value="${Constant.SORT_LATEST }">
			</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/basket/list" >
				<%=Constant.BASKET%>
			</a>
		</li>
		<c:if test="${user!=null}">
		<li>
			<a href="<%=request.getContextPath()%>/order/list">
				<%=Constant.ORDER%>一覧
			</a>
		</li>
		</c:if>
	</ul>
</nav>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>    
<aside class="admin_menu" >
	<div>
		<h2 class="title">
			<%=Constant.ADMIN_MENU%>
		</h2>
		<ul class="menu">
			<li>
				<a href="<%=request.getContextPath()%>/admin/user/list" class="display_list">
					<%=Constant.USER%>一覧表示
				</a>
			</li>
			<c:if test="${user.authority==Constant.AUTH_ADMIN}">
			<li>
				<a href="<%=request.getContextPath()%>/admin/order/list" class="display_list">
					<%=Constant.ORDER%>一覧表示
				</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/admin/item/list" class="display_list">
					<%=Constant.ITEM%>一覧表示
				</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/admin/category/list" class="display_list">
					<%=Constant.CATEGORY%>一覧表示
				</a>
			</li>
			</c:if>
		</ul>
	</div>
</aside>


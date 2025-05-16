<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="jp.co.sss.shop.constant.Constant" %>
<aside class="search">
	<div class="search_area">
		<h2 class="title">
			<%=Constant.CATEGORY%>検索
		</h2>
		<div class="form">
			<form action="<%=request.getContextPath()%>/item/list">
				<c:choose>
				<c:when test="${sortType==null}" >
					<input type="hidden" name="sort" value="<%=Constant.SORT_LATEST%>" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="sort" value="${sortType}" />
				</c:otherwise>
				</c:choose>
				<select name="categoryId">
					<option value="<%=Constant.CATEGORY_SELECT_NONE_NO%>">
							<%=Constant.CATEGORY_SELECT_NONE%>
					</option>
				<c:forEach var="category" items="${categoryList}">
					<option value="${category.id}" <c:if test="${categoryId==category.id}">SELECTED</c:if>>
							<c:out value="${category.name}"/>
					</option>
				</c:forEach>
				</select>

				<input type="submit" value="検索" />
			</form>
		</div>
	</div>
</aside>


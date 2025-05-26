<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="jp.co.sss.shop.constant.Constant" %>
<style>/* バナー画像リンクのスタイル */
.special_link {
  display: block;
  text-align: center;
  margin-bottom: 16px; /* 下に間隔を空ける */
}.special_link img {
  width: 180px;
  height: 130px;
  object-fit: contain;
  background-color: #f8f8f8; /* 背景色追加で空白が気にならないように */
  display: block;
  margin: 0 auto 16px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
  transition: transform 0.3s ease, box-shadow 0.3s ease, filter 0.3s ease;
}



.special_link:hover img {
  transform: scale(1.02);
  box-shadow: 0 8px 16px rgba(0,0,0,0.3);
  filter: brightness(1.05); /* ほんの少し明るく */
}
</style>
<aside class="search">
	<div class="search_area">
		<h2 class="title">
			<%=Constant.CATEGORY%>検索
		</h2>
		<div class="form">
			<form action="<%=request.getContextPath()%>/item/list">
				<c:choose>
				<c:when test="${sortType==null}" >
					<input type="hidden" name="sortType" value="<%=Constant.SORT_LATEST%>" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="sortType" value="${sortType}" />
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
		
		<div>
			<a href="<%=request.getContextPath()%>/orizinal/list?categoryId=2" class="special_link">
				<img src="<%=request.getContextPath()%>/img/bnr_250030_180_130.webp" alt="新鮮な野菜・果物" />
			</a>
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/orizinal/list?seasonType=1" class="special_link">
				<img src="<%=request.getContextPath()%>/img/vegetable.jpg" alt="新鮮な野菜" />
			</a>
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/orizinal/list?seasonType=2" class="special_link">
				<img src="<%=request.getContextPath()%>/img/test.webp" alt="新鮮な果物" />
			</a>
		</div>
		
	</div>
</aside>


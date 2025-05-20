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
		<%=Constant.ITEM%>一覧 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body class="client  item_list">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
	<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.ITEM%>一覧
			</h2>

			<div class="list_btn_wrap">
				<div>
				<c:if test="${Constant.ITEM_RECORD_MAX <= count}">
				<br />
				<%--カテゴリ情報のレコード件数が最大件数を超えていた場合エラーメッセージ表示 --%>
					<ul class="error_list">
						<li>
							<span>
							<%=Constant.ITEM+MSGConstant.MSG_CANNOT_REGIST%>
							</span>
						</li>
					</ul>

				</c:if>			
				<c:if test="${itemBeanList==null || itemBeanList.size()==0}">
					<p><%=Constant.ITEM+MSGConstant.MSG_ITEM_CLIENT_LIST_NONE %></p>
				</c:if>
				</div>
				
			</div>
			<c:if test="${itemBeanList.size()>0}">
			<table class="list_table item_list">
				<tr>
					<th>
						<%=Constant.DATA_ITEM_IMAGE%>
					</th>
					<th>
						<%=Constant.DATA_ITEM_NAME%>
					</th>
					<th>
						<%=Constant.DATA_ITEM_PRICE%>
					</th>
					<th>
						<%=Constant.CATEGORY%>
					</th>
					
				</tr>
				<c:forEach var="itemBean" items="${itemBeanList}">
				<tr>
					<td>
						<img src="<%=request.getContextPath()%>/img/${itemBean.image}" />
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/item/detail?id=${itemBean.id}">
							<c:out value="${itemBean.name}"/>
						</a>
					</td>
					<td>
						${itemBean.price}
					</td>
					<td>
						<c:out value="${itemBean.categoryName}"/>
					</td>
					
				</tr>
			    </c:forEach>
			</table>
			</c:if>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp" %>

</body>
</html>
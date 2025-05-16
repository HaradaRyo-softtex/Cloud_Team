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
		<%=Constant.ORDER%>一覧 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin order_list_admin">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.ORDER%>一覧
			</h2>
			<div class="list">
				<c:if test="${orderBeanList.size()==0}">
				<p><%=Constant.ORDER+MSGConstant.MSG_ADMIN_LIST_NONE %></p>	
				</c:if>
				<c:if test="${orderBeanList.size()>0}">
				<table class="list_table order">
					<tr>
						<th>
							<%=Constant.DATA_ID%>
						</th>
						<th>
							<%=Constant.USER+Constant.DATA_USERNAME%>
						</th>
						<th>
							<%=Constant.DATA_ORDER_DAY%>
						</th>
						<th>
							<%=Constant.DATA_PAYMETHOD%>
						</th>
						<th>
							<%=Constant.DATA_TOTAL%>
						</th>
					</tr>
				<c:forEach var="orderBean" items="${orderBeanList}">
				<tr>
					<td>
						${orderBean.id}
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/admin/order/detail?id=${orderBean.id}">
							<c:out value="${orderBean.userName}" />
						</a>
					</td>
					<td>
						${orderBean.insertDate}
					</td>
					<td>
						${orderBean.payMethod}
					</td>
					<td>
						${orderBean.total}
					</td>		
				</tr>
			    </c:forEach>
				</table>
				</c:if>
			</div>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
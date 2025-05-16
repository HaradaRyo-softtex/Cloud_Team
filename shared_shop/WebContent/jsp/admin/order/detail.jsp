<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ORDER%>詳細 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin order_detail_admin">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.ORDER%>詳細
			</h2>
			<table class="detail_table payment">
				<tr>
					<th>
						<%=Constant.USER+Constant.DATA_USERNAME%>
					</th>
					<td>
						<c:out value="${orderDetailBean.userName}"/>
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_ORDER_DAY%>
					</th>
					<td>
						${orderDetailBean.insertDate}
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_PAYMETHOD%>
					</th>
					<td>
						<span>
						${orderDetailBean.payMethodStr}
						</span>
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_ADDRESSEE_POSTALCODE%>
					</th>
					<td>
						${orderDetailBean.postalCode}
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_ADDRESSEE_ADDRESS%>
					</th>
					<td>
						<c:out value="${orderDetailBean.address}"/>
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_ADDRESSEE_NAME%>
					</th>
					<td>
						<c:out value="${orderDetailBean.name}"/>
					</td>
				</tr>
				<tr>
					<th>
						<%=Constant.DATA_ADDRESSEE_PHONENUMBER%>
					</th>
					<td>
						${orderDetailBean.phoneNumber}
					</td>
				</tr>
			</table>
			<table class="list_table detail">
				<tr>
					<th>
						<%=Constant.DATA_ITEM_NAME%>
					</th>
					<th>
						<%=Constant.DATA_UNIT_PRICE %>
					</th>
					<th>
						<%=Constant.DATA_ORDER_NUM %>
					</th>
					<th>
						<%=Constant.DATA_SUBTOTAL %>
					</th>
				</tr>
				<c:forEach var="orderItemBean" items="${orderItemBeanList}">
				<tr>
					<td>
						<c:out value="${orderItemBean.name}"/>
					</td>
					<td>
						${orderItemBean.price}
					</td>
					<td>
						${orderItemBean.orderNum}
					</td>
					<td>
						${orderItemBean.subtotal}
					</td>		
				</tr>
			    </c:forEach>
				<tr>
					<td colspan="2"></td>
					<td class="total">
						<%=Constant.DATA_TOTAL %>
					</td>
					<td class="total">
						${orderDetailBean.total} 
					</td>
				</tr>
			</table>
			<form action="<%=request.getContextPath()%>/admin/order/list" class="detail_button_area">
				<input type="submit" value="戻る" />
			</form>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
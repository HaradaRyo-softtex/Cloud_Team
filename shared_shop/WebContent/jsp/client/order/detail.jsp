<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title><%=Constant.ORDER%>詳細 | <%=Constant.SHOP_TITLE%></title>
</head>
<body  class="user index">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title" style="font-size:24px;">
				<%=Constant.ORDER%>詳細
			</h2>

			<table class="detail_table payment">
				<c:forEach var="order" items="${orderDetailsList}">
					<tr>
						<th><%=Constant.DATA_ORDER_DAY%></th>
						<td><c:out value="${order.insert_date}" /></td>
					</tr>

					<tr>
						<th><%=Constant.DATA_PAYMETHOD%></th>
						<td>
							<c:choose>
								<c:when test="${order.pay_method==1}">クレジットカード</c:when>
								<c:when test="${order.pay_method==2}">銀行振り込み</c:when>
								<c:when test="${order.pay_method==3}">着払い</c:when>
								<c:when test="${order.pay_method==4}">電子マネー</c:when>
								<c:when test="${order.pay_method==5}">コンビニ決済</c:when>
								<c:otherwise>不明</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_POSTALCODE%></th>
						<td>${order.postal_code}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_ADDRESS%></th>
						<td>${order.address}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_NAME%></th>
						<td>${order.name}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_PHONENUMBER%></th>
						<td>${order.phone_number}</td>
					</tr>
				</c:forEach>
			</table>

			<!-- ここで total を初期化 -->
			<c:set var="total" value="0" />

			<table class="list_table detail">
				<tr>
					<th><%=Constant.DATA_ITEM_NAME%></th>
					<th><%=Constant.DATA_UNIT_PRICE%></th>
					<th><%=Constant.DATA_ORDER_NUM%></th>
					<th><%=Constant.DATA_SUBTOTAL%></th>
				</tr>
	
				<c:forEach var="orderItem" items="${orderItemList}">
					<tr>
						<td><c:out value="${orderItem.name}" /></td>
						<td>${orderItem.price}</td>
						<td>${orderItem.quantity}</td>
						<td>${orderItem.sum}</td>
						<c:set var="total" value="${total + orderItem.sum}" />
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2"></td>
					<td class="total"><%=Constant.DATA_TOTAL%></td>
					<td class="total"><c:out value="${total}" /></td>
				</tr>
			</table>
			<form action="<%=request.getContextPath()%>/order/list"
				class="detail_button_area">
				<input type="submit" value="戻る" />
			</form>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>

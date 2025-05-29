<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title><%=Constant.ORDER%>登録確認| <%=Constant.SHOP_TITLE%></title>
</head>
<body class="user index">
	<%@include file="/jsp/common/header.jsp"%>
	<%@include file="/jsp/common/navi.jsp"%>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp"%>
		<article class="main">
			<h2 class="title">注文内容最終確認</h2>
			<br />
			<c:forEach var="message" items="${messageList}">
				<p>${message}</p>


			</c:forEach>
			<%
			session.removeAttribute("messageList");
			%>

			<table class="list_table item_detail">
				<tr>
					<th>商品名</th>
					<th>商品画像</th>
					<th>単価</th>
					<th>数量</th>
					<th>小計</th>
				</tr>
				<!-- スコープの値をセットする (forEach)-->
				<tr>
					<c:forEach var="i" items="${itemDetailBeanList}">
						<c:if test="${i.stock>0}">
							<td>${i.name}</td>
							<td><img src="<%=request.getContextPath() %>/img/${i.image}"
								alt="画像" /></td>
							<td>${i.price}</td>


							<c:forEach var="b" items="${basket}">
								<c:if test="${i.id == b.id}">
									<td>${b.orderNum}</td>
									<c:set value="${b.orderNum*i.price}" var="subtotal"></c:set>
									<td><c:out value="${subtotal}"></c:out></td>
									<c:set value="${total + subtotal}" var="total"></c:set>
								</c:if>

							</c:forEach>
				</tr>
				</c:if>
				</c:forEach>

				<tr>
					<td colspan="3"></td>
					<td class="total"><%=Constant.DATA_TOTAL%></td>
					<td class="total"><c:out value="${total}"></c:out>
				</tr>
			</table>
			<div class="user_info_form_area">
				<table class="detail_table address">
					<caption class="table_caption"><%=Constant.ADDRESSEE%></caption>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_POSTALCODE%></th>
						<td>${orderform.postalCode}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_ADDRESS%></th>
						<td>${orderform.address}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_NAME%></th>
						<td>${orderform.name}</td>
					</tr>
					<tr>
						<th><%=Constant.DATA_ADDRESSEE_PHONENUMBER%></th>
						<td>${orderform.phoneNumber}</td>
					</tr>
				</table>
				<table class="detail_table pay">
					<caption class="table_caption">
						お<%=Constant.DATA_PAYMETHOD%></caption>
					<tr>
						<th>お<%=Constant.DATA_PAYMETHOD%></th>
						<td>
							<!-- EL式に直す  --> <span><c:choose>
									<c:when test="${orderform.payMethod == 1}"><%=Constant.PAYMETHOD_CREDIT_STR%></c:when>
									<c:when test="${orderform.payMethod == 2}"><%=Constant.PAYMETHOD_BANK_STR%></c:when>
									<c:when test="${orderform.payMethod == 3}"><%=Constant.PAYMETHOD_ONARRIVAL_STR%></c:when>
									<c:when test="${orderform.payMethod == 4}"><%=Constant.PAYMETHOD_EMONEY_STR%></c:when>
									<c:when test="${orderform.payMethod == 5}"><%=Constant.PAYMETHOD_CONVENIENCE_STR%></c:when>
								</c:choose></span>

						</td>
					</tr>
				</table>

				<c:if test="${zeroAll== null || zeroAll ==false}">
					<form method="post"
						action="<%=request.getContextPath()%>/order/regist/complete">
						<input type="hidden" name="backflg" value="off" /> <input
							class="send_button" type="submit" value="ご注文の確定" />
					</form>
				</c:if>
				<%
				session.removeAttribute("zeroAll");
				%>
				<form method="post"
					action="<%=request.getContextPath()%>/order/payment/input"
					class="update">
					<input type="hidden" name="backflg" value="on" /> <input
						class="back_button" type="submit" value="戻る" />
				</form>
			</div>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
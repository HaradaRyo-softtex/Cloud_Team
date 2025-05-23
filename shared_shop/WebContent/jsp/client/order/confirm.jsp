<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title><%=Constant.ORDER%>お届け先入力| <%=Constant.SHOP_TITLE%></title>
</head>
<body  class="user index">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
			<article class="main">
				<h2 class="title">
					注文内容最終確認
				</h2>
				<br />
				<table class="list_table item_detail">
					<tr>
						<th>商品名</th>
						<th>商品画像</th>
						<th>単価</th>
						<th>数量</th>
						<th>小計</th>
					</tr>
					<!-- スコープの値をセットする -->
					<c:forEach var="" items="${ }">
					<tr>
						<td>${ }</td>
						<td><img src="${ }" /></td>
						<td>${ }</td>
						<td>${ }</td>
						<td>${ }</td>
						<c:set value="${ } " var="total"></c:set>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="3"></td>
						<td class="total"><%=Constant.DATA_TOTAL%></td>
						<td class="total"><c:out value="${total}"></c:out></td>
					</tr>
				</table>
				<div class="user_info_form_area">
					<table class="detail_table address">
						<caption class="table_caption"><%= Constant.ADDRESSEE %></caption>
						<tr>
							<th><%= Constant.DATA_ADDRESSEE_POSTALCODE %></th>
							<td>${ }</td>
						</tr>
						<tr>
							<th><%= Constant. DATA_ADDRESSEE_ADDRESS%></th>
							<td>${ }</td>
						</tr>
						<tr>
							<th><%= Constant.DATA_ADDRESSEE_NAME %></th>
							<td>${ }</td>
						</tr>
						<tr>
							<th><%= Constant.DATA_ADDRESSEE_PHONENUMBER %></th>
							<td>${ }</td>
						</tr>
					</table>
					<table class="detail_table pay">
						<caption class="table_caption">お<%= Constant. DATA_PAYMETHOD%></caption>
						<tr>
							<th>お<%= Constant.DATA_PAYMETHOD %></th>
							<td>
								<span>${ }</span>
							</td>
						</tr>
					</table>
					<form method="post" action="/order/regist/complete" >
						<input type="hidden" name="backflg" value="0" />
						<input class="send_button" type="submit" value="ご注文の確定" />
					</form>
					<form method="post" action="/order/payment/input" class="update">
						<input type="hidden" name="backflg" value="1" />
						<input class="back_button" type="submit" value="戻る" />
					</form>
				</div>
			</article>
		</div>
		<%@include file="/jsp/common/footer.jsp"%>
	</body>
</html>
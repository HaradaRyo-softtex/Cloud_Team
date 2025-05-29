<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title>支払方法選択画面| <%=Constant.SHOP_TITLE%></title>
</head>
<body  class="user index">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
			<article class="main">
				<h2 class="title">
					お支払い方法選択
				</h2>
				<div class="user_info_form_area">
					<form method="post" action="<%=request.getContextPath()%>/order/payment/input">
						<ul class="payment_method_select">
							<li>
								<label class="radio_label"><input type="radio" name="payMethod" value="<%= Constant.PAYMETHOD_CREDIT %>" checked="checked"/><%= Constant.PAYMETHOD_CREDIT_STR %></label>
							</li>
							<li>
								<label class="radio_label"><input type="radio" name="payMethod" value="<%= Constant.PAYMETHOD_BANK %>" /><%= Constant.PAYMETHOD_BANK_STR %></label>
							</li>
							<li>
								<label class="radio_label"><input type="radio" name="payMethod" value="<%= Constant.PAYMETHOD_ONARRIVAL %>" /><%= Constant.PAYMETHOD_ONARRIVAL_STR %></label>
							</li>
							<li>
								<label class="radio_label"><input type="radio" name="payMethod" value="<%= Constant.PAYMETHOD_EMONEY %>"/><%= Constant.PAYMETHOD_EMONEY_STR %></label>
							</li>
							<li>
								<label class="radio_label"><input type="radio" name="payMethod" value="<%= Constant.PAYMETHOD_CONVENIENCE %>"/><%= Constant.PAYMETHOD_CONVENIENCE_STR %></label>
							</li>
						</ul>
						<input type="hidden" name="backflg" value="off" />
						<input type="submit" class="send_button" value="次へ" />
					</form>
					<form method="post" action="<%=request.getContextPath()%>/order/address/input" class="update">
						<input type="hidden" name="backflg" value="on" />
						<input type="submit" class="back_button" value="戻る" />
					</form>
				</div>
			</article>
		</div>
		<%@include file="/jsp/common/footer.jsp"%>
	</body>
</html>
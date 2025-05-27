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
					注文完了
				</h2>
				<p class="complete_message">
					ご注文ありがとうございます。
					<br />
					注文手続きが完了しました。
				</p>
				<p class="complete_link">
					<a href="/shared_shop/">
						トップへ戻る
					</a>
				</p>
			</article>
		</div>
		<%@include file="/jsp/common/footer.jsp"%>
	</body>
</html>
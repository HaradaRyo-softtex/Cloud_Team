<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %> 
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.CATEGORY%>登録完了 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin category_regist_complete">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
			<article class="main">
				<h2 class="title">
					<%=Constant.CATEGORY%>登録完了
				</h2>
				<p class="complete_message">
					<%=Constant.CATEGORY+MSGConstant.MSG_REGIST_COMPLETE %>
				</p>
				<p class="complete_link">
					<a href="<%=request.getContextPath()%>/admin/category/list">
						<%=Constant.CATEGORY+MSGConstant.MSG_BACK_TO_LIST%>
					</a>
				</p>
			</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
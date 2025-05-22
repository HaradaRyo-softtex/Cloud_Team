<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
  <!--削除完了画面 -->
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>削除完了 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user user_delete">
<%@include file="/jsp/common/navi.jsp" %>
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>削除完了
			</h2>
			<p class="complete_message">
				<%=Constant.USER+MSGConstant.MSG_DELETE_COMPLETE%>
			</p>
			<p class="complete_link">
				<a href="<%=request.getContextPath()%>/top">
					<%=MSGConstant.MSG_BACK_TO_TOP%>
				</a>
			</p>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
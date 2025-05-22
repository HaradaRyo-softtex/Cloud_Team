<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
  <!--変更完了画面 -->
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>変更完了 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user user_update">
   
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>変更完了
			</h2>
			<p class="complete_message">
				<%=Constant.USER+MSGConstant.MSG_UPDATE_COMPLETE%>
			</p>
			<p class="complete_link">
				<a href="<%=request.getContextPath()%>/user/detail">
					<%=Constant.USER+MSGConstant.MSG_BACK_TO_DETAIL%>
				</a>
			</p>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
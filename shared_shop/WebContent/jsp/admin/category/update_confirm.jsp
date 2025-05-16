<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>    
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.CATEGORY%>変更確認 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin category_update_confirm">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
			<article class="main">
				<h2 class="title">
					<%=Constant.CATEGORY%>変更確認
				</h2>
				<p class="confirm_message">
					<%=MSGConstant.MSG_UPDATE_CONFIRM %>
				</p>
				<div class="user_info_form_area">
					<ul class="input_list">
						<li>
							<span class="input_title">
								<%=Constant.DATA_CATEGORY_NAME%>
							</span>
							<span class="input_value">
								<c:out value="${categoryForm.name}"/>
							</span>
						</li>
						<li>
							<span class="input_title">
								<%=Constant.DATA_DESCRIPTION%>
							</span>
							<span class="input_value">
								<c:out value="${categoryForm.description}"/>
							</span>
						</li>
					</ul>
					<form action="<%=request.getContextPath()%>/admin/category/update/complete" method="post" class="user_info_form">
						<input type="submit" value="登録" class="send_button" />
					</form>
					<form action="<%=request.getContextPath()%>/admin/category/update/input" method="post">
						<input type="hidden" name="backflg" value="<%=Constant.BACKFLG_ON%>" />
						<input type="submit" value="戻る" class="back_button" />
					</form>
				</div>
			</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
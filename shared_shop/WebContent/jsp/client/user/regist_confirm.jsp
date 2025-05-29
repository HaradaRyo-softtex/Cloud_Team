<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>

<!DOCTYPE html>
<html>
<head>
  <!--新規確認画面 -->
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>登録確認 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user user_regist">
<%@include file="/jsp/common/navi.jsp" %>
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>登録確認
			</h2>
			<p class="check_message">
				<%=MSGConstant.MSG_REGIST_CONFIRM %>
			</p>
			<div class="user_info_form_area">
				<ul class="input_list">
					<li>
						<span class="input_title">
							<%=Constant.DATA_EMAIL%>
						</span>
						<span class="input_value">
							${userForm.email}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_USERNAME%>
						</span>
						<span class="input_value">
							<c:out value="${userForm.name}" />
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_POSTALCODE%>
						</span>
						<span class="input_value">
							${userForm.postalCode}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_ADDRESS%>
						</span>
						<span class="input_value">
							<c:out value="${userForm.address}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_PHONENUMBER%>
						</span>
						<span class="input_value">
							${userForm.phoneNumber}
						</span>
					</li>
					
				</ul>
				
				<form method="post" action="<%=request.getContextPath()%>/user/regist/complete" class="user_info_form">
					
					<input type="submit" value="登録" class="send_button" />
					
				</form>
				<form method="post" action="<%=request.getContextPath()%>/user/regist/input">
					<input type="hidden" name="backflg" value="<%=Constant.BACKFLG_ON%>" />
					<input type="submit" value="戻る" class="back_button" />
				</form>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
  <!--削除確認画面 -->
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>退会確認 | <%=Constant.SHOP_TITLE%><%--削除から退会に喜田が変更 --%>
	</title>
</head>
<body  class="user user_delete">
<%@include file="/jsp/common/navi.jsp" %>
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>退会確認<%--削除から退会に喜田が変更 --%>
			</h2>
			<p class="check_message">
				<%=MSGConstant.MSG_CLIENT_DELETE_COMFIRM%><%--削除から退会に喜田が変更 --%>
			</p>
			<div class="user_info_form_area">
				<ul class="input_list">
					<li>
						<span class="input_title">
							<%=Constant.DATA_EMAIL %>
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
							<c:out value="${userForm.name}"/>
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
							<%=Constant.DATA_POSTALCODE%>
						</span>
						<span class="input_value">
							${userForm.phoneNumber}
						</span>
					</li>
					
				</ul>
				<form action="<%=request.getContextPath()%>/user/delete/complete" method="post" class="user_info_form">
					<input type="hidden" name="id" value="${userForm.id}" />
					<input type="submit" value="退会" class="send_button delete" />
				</form>
				<form action="<%=request.getContextPath()%>/user/detail">
					<input type="hidden" name="id" value="${userForm.id}" />
					<input type="submit" value="戻る" class="back_button" />
				</form>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
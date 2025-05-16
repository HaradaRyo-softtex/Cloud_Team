<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>詳細 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin user_detail_admin">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>詳細
			</h2>
			<div class="user_detail_area">
				<ul class="input_list">
					<li>
						<span class="input_title">
							<%=Constant.DATA_EMAIL%>
						</span>
						<span class="input_value">
							${userDetailBean.email}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_USERNAME%>
						</span>
						<span class="input_value">
							<c:out value="${userDetailBean.name}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_POSTALCODE%>
						</span>
						<span class="input_value">
							${userDetailBean.postalCode}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_ADDRESS%>
						</span>
						<span class="input_value">
							<c:out value="${userDetailBean.address}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_PHONENUMBER%>
						</span>
						<span class="input_value">
							${userDetailBean.phoneNumber}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_AUTHORITY%>
						</span>
						<span class="input_value">
							<span>
							${userDetailBean.authorityStr}
							</span>
						</span>
					</li>
				</ul>
			</div>
			<div class="detail_button_area">
				<form action="<%=request.getContextPath()%>/admin/user/list">
					<input type="submit" value="戻る" />
				</form>
				<form method="post" action="<%=request.getContextPath()%>/admin/user/update/input">
					<input type="hidden" name="id" value="${userDetailBean.id}" />
					<input type="submit" value="変更" />
				</form>

				<%--ログインユーザの詳細情報表示の場合、ログインユーザ自身の削除できないようにする --%>
				<form action="<%=request.getContextPath()%>/admin/user/delete/confirm" method="post">
					<input type="hidden" name="id" value="${userDetailBean.id}" />
					<input type="submit" value="削除" class="delete" <c:if test="${user.id==userDetailBean.id}"> disabled </c:if>/>
				</form>
				<%--</c:if> --%>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
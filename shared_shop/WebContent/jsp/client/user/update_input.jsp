<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<!DOCTYPE html>
<html>
<head>
   <!--変更入力画面 -->
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>変更入力 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user user_update">
<%@include file="/jsp/common/navi.jsp" %>
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>変更入力
			</h2>
			<p class="input_message">
				<%=MSGConstant.MSG_UPDATE_INPUT %>
			</p>
			<div class="user_info_form_area">
			<c:if test="${errorMessageList!=null}">
				<ul class="error_list">
				<c:forEach var="errorMsg" items="${errorMessageList}">
					<li>
						${errorMsg}
					</li>
				</c:forEach>
				</ul>
			</c:if>

			<form method="post" action="<%=request.getContextPath()%>/user/update/input" class="user_info_form">
					<br />
					<ul class="input_list">
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_EMAIL%>
								</span>
								<input type="text" name="email" value="<c:out value="${userForm.email}"/>"/>
							</label>
						</li>
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_PASSWORD%>
								</span>
								<input type="password" name="password" value=""/>
							</label>
						</li>
						
					<!-- パスワード再確認	 -->
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_RECHECKPASSWORD%>
								</span>
								<input type="password" name="newpassword" value=""/>
							</label>
						</li>
					<!-- パスワード再確認(ここまで追加分) -->
						
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_USERNAME%>
								</span>
								<input type="text" name="name" value="<c:out value="${userForm.name}"/>"/>
							</label>
						</li>
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_POSTALCODE%>
								</span>
								<input type="text" name="postalCode" value="<c:out value="${userForm.postalCode}"/>"/>
							</label>
						</li>
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_ADDRESS%>
								</span>
								<textarea name="address" rows="6"><c:out value="${userForm.address}"/></textarea>
							</label>
						</li>
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_PHONENUMBER%>
								</span>
								<input type="text" name="phoneNumber" value="${userForm.phoneNumber}"/>
							</label>
						</li>
						
							<div class="input">
							<%--ログインユーザの権限がシステム管理者の場合のみ権限を設定できるようにする --%>
							<c:if test="${user.authority==Constant.AUTH_SYSTEM}">
							<c:choose>
								<%--入力値がある場合は、入力値に合わせて表示時点で選択されている項目を変更する --%>
								<c:when test="${userForm.authority==Constant.AUTH_CLIENT}">
									<input type="radio" name="authority" value="<%=Constant.AUTH_SYSTEM%>"/>
									<%=Constant.AUTH_SYSTEM_STR%>
									<input type="radio" name="authority" value="<%=Constant.AUTH_CLIENT%>"  checked="checked"/>
									<%=Constant.AUTH_CLIENT_STR%>
								</c:when>	
								<c:when test="${userForm.authority==Constant.AUTH_SYSTEM}">
									<input type="radio" name="authority" value="<%=Constant.AUTH_SYSTEM%>" checked="checked"/>
									<%=Constant.AUTH_SYSTEM_STR%>
									<input type="radio" name="authority" value="<%=Constant.AUTH_CLIENT%>"  />
									<%=Constant.AUTH_CLIENT_STR%>
								</c:when>	
								<c:otherwise><%--選択されている権限がシステム管理者、運用管理者以外の場合 --%>
								<span class="input_value">
									<input type="hidden" name="authority" value="<%=Constant.AUTH_CLIENT%>"/>
									<%=Constant.AUTH_CLIENT_STR%>
								</span>
								</c:otherwise>	
							</c:choose>
							</c:if>
							</div>
						</li>
					</ul>
					<input type="hidden" name="id" value="${userForm.id}"/>
					<input type="hidden" name="backflg" value="<%=Constant.BACKFLG_OFF%>" />
					<input type="submit" value="確認" class="send_button" />
				</form>
				<form action="<%=request.getContextPath()%>/client/user/detail">
					<input type="hidden" name="id" value="${userForm.id}"/>
					<input type="submit" value="戻る" class="back_button" />
				</form>
			</div>
		</article>

		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.USER%>一覧 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin user_list">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.USER%>一覧
			</h2>
			<c:if test="${count < Constant.USER_RECORD_MAX}">
		<%--会員情報のレコード件数が最大件数未満の場合のみ新規登録を可能とする --%>
			<form action="<%=request.getContextPath()%>/admin/user/regist/input" class="sign_up_form">
				<input type="submit" value="<%=Constant.USER%>新規登録" class="menu_button" />
			</form>
			</c:if>
			<c:if test="${Constant.USER_RECORD_MAX <= count}">
			<br/>
			<%--会員情報のレコード件数が最大件数を超えていた場合エラーメッセージ表示 --%>
				<ul class="error_list">
					<li>
						<span>
						<%=Constant.USER+MSGConstant.MSG_CANNOT_REGIST%>
						</span>
					</li>
				</ul>
			</c:if>
			<table class="list_table user_table">
				<tr>
					<th>
						<%=Constant.DATA_ID%>
					</th>
					<th>
						<%=Constant.DATA_USERNAME%>
					</th>
					<th>
						<%=Constant.DATA_EMAIL%>
					</th>
					<th>
						<%=Constant.LIST_TITLE_ACTION%>
					</th>
				</tr>
				<c:forEach var="userBean" items="${userBeanList}">
				<tr>
					<td>
						${userBean.id}
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/admin/user/detail?id=${userBean.id}">
							<c:out value="${userBean.name}"/>
						</a>
					</td>
					<td>
						${userBean.email}
					</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/admin/user/update/input">
							<input type="hidden" name="id" value="${userBean.id}" />
							<input type="submit" value="変更" />
						</form>

						<form method="post" action="<%=request.getContextPath()%>/admin/user/delete/confirm">
							<input type="hidden" name="id" value="${userBean.id}" />
							<input type="submit" value="削除" class="delete" <c:if test="${user.id==userBean.id}"> disabled </c:if> />
						</form>
						<%--</c:if> --%>
					</td>
				</tr>
			    </c:forEach>
			</table>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
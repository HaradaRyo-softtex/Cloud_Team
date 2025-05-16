<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %> 
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.LOGIN%> | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user login">
	<%@include file="/jsp/common/header.jsp" %>
	<article class="container content">
		<h2 class="page_title">
			<%=Constant.LOGIN%>
		</h2>
		<p class="description">
			<%=MSGConstant.MSG_LOGIN_INPUT%>
		</p>
		<form method="post" action="<%=request.getContextPath()%>/login">
		<c:if test="${errorMessageList!=null}">
			<ul class="error_list">
			<c:forEach var="errorMsg" items="${errorMessageList}">
				<li>
					<span>
					${errorMsg}
					</span>
				</li>
			</c:forEach>
			</ul>
		</c:if>
			<br />
			<table class="login_form">
				<tbody>
					<tr>
						<th class="label">
							<%=Constant.DATA_EMAIL%>
						</th>
						<td class="input">
							<input type="text" name="email" value="${email}"/>
						</td>
					</tr>
					<tr>
						<th class="label">
							<%=Constant.DATA_PASSWORD%>
						</th>
						<td class="input">
							<input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<th class="label"></th>
						<td class="input">
							<input type="submit" value="ログイン" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<p class="complete_link">
			<a href="<%=request.getContextPath()%>/">
				<%=MSGConstant.MSG_BACK_TO_TOP%>
			</a>
		</p>
	</article>

	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
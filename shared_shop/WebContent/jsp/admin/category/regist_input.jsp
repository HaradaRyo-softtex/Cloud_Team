<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>    
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.CATEGORY%>登録入力 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin category_regist_input">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.CATEGORY%>登録入力
			</h2>
			<p class="input_message">
				<%=MSGConstant.MSG_REGIST_INPUT%>
			</p>
			<div class="user_info_form_area">
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
				<form method="post" action="<%=request.getContextPath()%>/admin/category/regist/input" class="user_info_form">
					<br />
					<ul class="input_list">
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_CATEGORY_NAME%>
								</span>
								<input type="text" name="name" value="<c:out value="${categoryForm.name}"/>" id="name"/>
							</label>
						</li>
						<li>
							<label>
								<span class="input_title">
									<%=Constant.DATA_DESCRIPTION%>
								</span>
								<textarea name="description" rows="6"><c:out value="${categoryForm.description}"/></textarea>
							</label>
						</li>
					</ul>
					<input type="hidden" name="backflg" value="<%=Constant.BACKFLG_OFF%>" />
					<input type="submit" value="確認" class="send_button" />
				</form>
				<form action="<%=request.getContextPath()%>/admin/category/list">
					<input type="submit" value="戻る" class="back_button" />
				</form>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
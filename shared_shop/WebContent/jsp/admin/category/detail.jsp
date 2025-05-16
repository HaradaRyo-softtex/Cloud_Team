<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.CATEGORY%>詳細 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin category_detail">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.CATEGORY%>詳細
			</h2>
			<div class="user_detail_area">
				<ul class="input_list">
					<li>
						<span class="input_title">
							<%=Constant.DATA_CATEGORY_NAME%>
						</span>
						<span class="input_value">
							<c:out value="${category.name}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_DESCRIPTION%>
						</span>
						<span class="input_value">
							<c:out value="${category.description}" />
						</span>
					</li>
				</ul>
			</div>
			<div class="detail_button_area">
			
				<form action="<%=request.getContextPath()%>/admin/category/list" class="user_info_form">
					<input type="submit" value="戻る"  class="back_button" />
				</form>
				<form method="post" action="<%=request.getContextPath()%>/admin/category/update/input">
					<input type="hidden" name="id" value="${category.id}" />
					<input type="submit" value="変更" />
				</form>
				<form method="post" action="<%=request.getContextPath()%>/admin/category/delete/confirm">
					<input type="hidden" name="id" value="${category.id}" />
					<input type="submit" value="削除" class="delete" />
				</form>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
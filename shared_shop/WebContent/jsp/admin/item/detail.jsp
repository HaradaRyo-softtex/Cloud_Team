<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ITEM%>詳細 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin item_detail_admin">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
			<article class="main">
				<h2 class="title">
					<%=Constant.ITEM%>詳細
				</h2>
				<div class="item_info_form_area">
					<div class="item_detail_area">
						<div class="item_image_area">
							<img src="<%=request.getContextPath()%>/img/${itemDetailBean.image}" />
						</div>
						<dl class="item_detail_list">
							<dt>
								<%=Constant.DATA_ITEM_NAME%>
							</dt>
							<dd>
								<c:out value="${itemDetailBean.name}"/>
							</dd>
							<dt>
								<%=Constant.DATA_ITEM_PRICE%>
							</dt>
							<dd>
								${itemDetailBean.price}
							</dd>
							<dt>
								<%=Constant.DATA_ITEM_STOCK%>
							</dt>
							<dd>
								${itemDetailBean.stock}
							</dd>
							<dt>
								<%=Constant.CATEGORY%>
							</dt>
							<dd>
								<c:out value="${itemDetailBean.categoryName}"/>
							</dd>
							<dt>
								<%=Constant.DATA_DESCRIPTION%>
							</dt>
							<dd><c:out value="${itemDetailBean.description}"/></dd>
						</dl>
					</div>
				</div>
				<div class="detail_button_area">
				
				<form action="<%=request.getContextPath()%>/admin/item/list" class="user_info_form">
					<input type="submit" value="戻る"  class="back_button" />
				</form>
				<form method="post" action="<%=request.getContextPath()%>/admin/item/update/input">
					<input type="hidden" name="id" value="${itemDetailBean.id}" />
					<input type="submit" value="変更" />
				</form>
				<form method="post" action="<%=request.getContextPath()%>/admin/item/delete/confirm">
					<input type="hidden" name="id" value="${itemDetailBean.id}" />
					<input type="submit" value="削除" class="delete" />
				</form>
				</div>
			</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ITEM%>登録確認 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="admin item_regist_confirm">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.ITEM%>登録確認
			</h2>
			<p class="confirm_message">
				<%=MSGConstant.MSG_REGIST_CONFIRM %>
			</p>
			<div class="user_info_form_area">
				<ul class="input_list">
					<li>
						<span class="input_title">
							<%=Constant.DATA_ITEM_NAME%>
						</span>
						<span class="input_value">
							<c:out value="${itemForm.name}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_ITEM_PRICE%>
						</span>
						<span class="input_value">
							${itemForm.price}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.CATEGORY%>
						</span>
						<span class="input_value">
							<c:out value="${itemForm.categoryName}"/>
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_ITEM_STOCK%>
						</span>
						<span class="input_value">
							${itemForm.stock}
						</span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_DESCRIPTION%>
						</span>
						<span class="input_value"><c:out value="${itemForm.description}"/></span>
					</li>
					<li>
						<span class="input_title">
							<%=Constant.DATA_ITEM_IMAGE%>
						</span>
						<span class="input_value item_input_area">
							<c:if test="${itemForm.image!=null && itemForm.image.length()>0}">
								<img src="<%=request.getContextPath()%>/img/${itemForm.image}" />
							</c:if>
							<c:if test="${itemForm.image==null || itemForm.image.length()==0}">
								<img src="<%=request.getContextPath()%>/img/<%=Constant.FILENAME_NOIMAGE%>" />
							</c:if>
						</span>
					</li>
				</ul>
				<form action="<%=request.getContextPath()%>/admin/item/regist/complete" method="post" class="user_info_form">
					<input type="submit" value="登録" class="send_button" />
				</form>
				<form action="<%=request.getContextPath()%>/admin/item/regist/input" method="post">
					<input type="hidden" name="backflg" value="<%=Constant.BACKFLG_ON%>" />
					<input type="submit" value="戻る"  class="back_button" />
				</form>
			</div>
		</article>
		</div>
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
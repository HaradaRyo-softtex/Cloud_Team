<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.Constant" %>
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ITEM%>一覧 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body class="admin  item_list_admin">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
	<%@include file="/jsp/common/sidebar_admin.jsp" %>
		<article class="main">
			<h2 class="title">
				<%=Constant.ITEM%>一覧
			</h2>

			<div class="list_btn_wrap">
				<div>
				<c:if test="${Constant.ITEM_RECORD_MAX <= count}">
				<br />
				<%--カテゴリ情報のレコード件数が最大件数を超えていた場合エラーメッセージ表示 --%>
					<ul class="error_list">
						<li>
							<span>
							<%=Constant.ITEM+MSGConstant.MSG_CANNOT_REGIST%>
							</span>
						</li>
					</ul>

				</c:if>			
				<c:if test="${itemBeanList==null || itemBeanList.size()==0}">
					<p><%=Constant.ITEM+MSGConstant.MSG_ADMIN_LIST_NONE %></p>
				</c:if>
				</div>
				<c:if test="${count < Constant.ITEM_RECORD_MAX}">
				<form action="<%=request.getContextPath()%>/admin/item/regist/input" class="sign_up_form">
					<input type="submit" value="<%=Constant.ITEM%>新規登録" class="menu_button" />
				</form>
				</c:if>
			</div>
			<c:if test="${itemBeanList.size()>0}">
			<table class="list_table item_list">
				<tr>
					<th>
						<%=Constant.DATA_ID%>
					</th>
					<th>
						<%=Constant.DATA_ITEM_NAME%>
					</th>
					<th>
						<%=Constant.DATA_ITEM_PRICE%>
					</th>
					<th>
						<%=Constant.CATEGORY%>
					</th>
					<th>
						<%=Constant.LIST_TITLE_ACTION%>
					</th>
				</tr>
				<c:forEach var="itemBean" items="${itemBeanList}">
				<tr>
					<td>
						${itemBean.id}
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/admin/item/detail?id=${itemBean.id}">
							<c:out value="${itemBean.name}"/>
						</a>
					</td>
					<td>
						${itemBean.price}
					</td>
					<td>
						<c:out value="${itemBean.categoryName}"/>
					</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/admin/item/update/input">
							<input type="hidden" name="id" value="${itemBean.id}" />
							<input type="submit" value="変更" />
						</form>
						<form method="post" action="<%=request.getContextPath()%>/admin/item/delete/confirm">
							<input type="hidden" name="id" value="${itemBean.id}" />
							<input type="submit" value="削除" class="delete" />
						</form>
					</td>
				</tr>
			    </c:forEach>
			</table>
			</c:if>
		</article>
	</div>
	<%@include file="/jsp/common/footer.jsp" %>

</body>
</html>
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
		<%=Constant.CATEGORY%>一覧 | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body class="admin  category_list">
	<%@include file="/jsp/common/header.jsp" %>
	<div class="container side_wrap">
	<%@include file="/jsp/common/sidebar_admin.jsp" %>
	<article class="main">
		<h2 class="title">
			<%=Constant.CATEGORY%>一覧
		</h2>
		<div class="list_btn_wrap">
			<div>
			<c:if test="${Constant.CATEGORY_RECORD_MAX <= count}">
			<%--カテゴリ情報のレコード件数が最大件数を超えていた場合エラーメッセージ表示 --%>
			<br />
				<ul class="error_list">
					<li>
						<span>
						<%=Constant.CATEGORY+MSGConstant.MSG_CANNOT_REGIST%>
						</span>
					</li>
				</ul>
			</c:if>
			<c:if test="${categoryList==null || categoryList.size()==0}">
				<p><%=Constant.CATEGORY+MSGConstant.MSG_ADMIN_LIST_NONE %></p>
			</c:if>
			</div>
			<c:if test="${count < Constant.CATEGORY_RECORD_MAX}">
			<%--カテゴリ情報のレコード件数が最大件数未満の場合のみ新規登録を可能とする --%>
			<form  action="<%=request.getContextPath()%>/admin/category/regist/input" class="sign_up_form">
				<input type="submit" value="<%=Constant.CATEGORY%>新規登録" class="menu_button" />
			</form>
			</c:if>
		</div>
		<c:if test="${categoryList.size()>0}">
		<table class="list_table user" >
			<tr>
				<th>
					<%=Constant.DATA_ID%>
				</th>
				<th>
					<%=Constant.DATA_CATEGORY_NAME%>
				</th>
				<th>
					<%=Constant.LIST_TITLE_ACTION%>
				</th>
			</tr>
			<c:forEach var="category" items="${categoryList}">
				<tr>
				<td>
					${category.id}
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/category/detail?id=${category.id}">
						<c:out value="${category.name}"/>
					</a>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/admin/category/update/input" method="post">
						<input type="hidden" name="id" value="${category.id}"  />
						<input type="submit" value="変更" />
					</form>
					<form action="<%=request.getContextPath()%>/admin/category/delete/confirm" method="post">
						<input type="hidden" name="id" value="${category.id}" />
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
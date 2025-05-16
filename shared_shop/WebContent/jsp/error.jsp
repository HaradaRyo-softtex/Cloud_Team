<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="jp.co.sss.shop.constant.MSGConstant" %> 
<%session.invalidate(); %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/jsp/common/head.jsp" %>
	<title>
		<%=Constant.ERROR%> | <%=Constant.SHOP_TITLE%>
	</title>
</head>
<body  class="user login">
	<%@include file="/jsp/common/header.jsp" %>
		<article class="container content">
			<div class="container">
				<p>
					<%=MSGConstant.MSG_ERROR_SYS%><br /><br /><br />
				</p>
			</div>
		</article>
	<%@include file="/jsp/common/footer.jsp" %>
	</body>
</html>

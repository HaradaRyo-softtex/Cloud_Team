<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jp.co.sss.shop.constant.MSGConstant"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/common/head.jsp"%>
<title><%=Constant.ORDER%>お届け先入力| <%=Constant.SHOP_TITLE%></title>
</head>
<body  class="user index">
	<%@include file="/jsp/common/header.jsp" %>
	<%@include file="/jsp/common/navi.jsp" %>
	<div class="container side_wrap">
		<%@include file="/jsp/common/sidebar.jsp" %>
		<article class="main">
			<h2 class="title" style="font-size:24px;">
				<%=Constant.ORDER%>お届け先入力
			</h2>
				<p class="input_message">
				お届け先情報を入力してください。
				</p>
				<div class="user_info_form_area">
					<form action="<%=request.getContextPath() %>/order/address/input" method="post" >
						<br />
						<ul class="input_list">
							<li>
								<label>
									<span class="input_title">
										郵便番号
									</span>
									<input type="text" name="postalCode" value=""/>
								</label>
							</li>
							<li>
								<label>
									<span class="input_title">
										住所
									</span>
									<textarea name="address" rows="6"></textarea>
								</label>
							</li>
							<li>
								<label>
									<span class="input_title">
										氏名
									</span>
									<input type="text" name="name" value=""/>
								</label>
							</li>
							<li>
								<label>
									<span class="input_title">
										電話番号
									</span>
									<input type="text" name="phoneNumber" value=""/>
								</label>
							</li>
						</ul>
						<input type="submit" value="次へ" class="send_button" />
						<input type="hidden" name="backflg" value="0" />
					</form>
					<form action="<%=request.ContextPath() %>/basket/list">
						<input type="submit" value="戻る" class="back_button" />
					</form>
				</div>
			</article>
		</div>
		<%@include file="/jsp/common/footer.jsp"%>
	</body>
</html>
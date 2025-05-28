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
        <%=Constant.ORDER%>一覧 | <%=Constant.SHOP_TITLE%>
    </title>
</head>
<body class="user index">
    <%@include file="/jsp/common/header.jsp" %>
    <%@include file="/jsp/common/navi.jsp" %>
    <div class="container side_wrap">
        <%@include file="/jsp/common/sidebar.jsp" %>

        <article class="main">
            <h2 class="title" style="font-size:24px;">
                <%=Constant.ORDER%>一覧
            </h2>
            <div class="list">
                <c:if test="${empty orderSumList}">
                    <p><%=Constant.ORDER + MSGConstant.MSG_ADMIN_LIST_NONE %></p>    
                </c:if>

                <c:if test="${not empty orderSumList}">
                    <table class="list_table order">
                        <tr>
                            <th><%=Constant.DATA_ORDER_DAY%></th>
                            <th><%=Constant.DATA_PAYMETHOD%></th>
                            <th><%=Constant.DATA_TOTAL%></th>
                        </tr>

                        <c:forEach var="orderSum" items="${orderSumList}">
                            <tr>
                                <td>
                                	<a href="<%=request.getContextPath()%>/order/detail?orderId=${orderSum.order_id}">
                                        <c:out value="${orderSum.date}" />
                                    </a>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${orderSum.pay_method == 1}">クレジットカード</c:when>
                                        <c:when test="${orderSum.pay_method == 2}">銀行振り込み</c:when>
                                        <c:when test="${orderSum.pay_method == 3}">着払い</c:when>
                                        <c:when test="${orderSum.pay_method == 4}">電子マネー</c:when>
                                        <c:when test="${orderSum.pay_method == 5}">コンビニ決済</c:when>
                                        <c:otherwise>不明</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:out value="${orderSum.sum}" />
                                </td>        
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </article>
    </div>
    <%@include file="/jsp/common/footer.jsp" %>
</body>
</html>

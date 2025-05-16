<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>update.jsp</title>
</head>
<body>
	<h2>Chapter06_01_update</h2>

    <form action="${pageContext.request.contextPath}/Chapter06_01/confirm" method="post">
    
        <!-- 学籍番号（表示のみ、値はhiddenで送信） -->
        学籍番号：${student.studentId}<br>
        <input type="hidden" name="studentId" value="${student.studentId}" />

        <!-- 名前 -->
        名前：
        <input type="text" name="name" value="${student.name}" /><br>

        <!-- 性別（ラジオボタン） -->
        性別：
        <input type="radio" name="gender" value="男性"
            <c:if test="${student.gender == '男性'}">checked</c:if>> 男性
        <input type="radio" name="gender" value="女性"
            <c:if test="${student.gender == '女性'}">checked</c:if>> 女性<br>

        <!-- 学年（セレクトボックス） -->
        学年：
        <select name="grade">
            <c:forEach var="g" begin="1" end="4">
                <option value="${g}"
                    <c:if test="${student.grade == g}">selected</c:if>>${g}年
                </option>
            </c:forEach>
        </select><br>

        <!-- 送信ボタン -->
        <br>
        <input type="submit" value="確認" />
    </form>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- これを追加 -->
<html>
<head>
<title>学生一覧</title>
</head>
<body>
	<h2>学生番号の検索結果</h2>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>性別</th>
			<th>学年</th>
		</tr>
		<c:forEach var="student" items="${studentList}">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.name}</td>
				<td>${student.gender}</td>
				<td>${student.grade}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>

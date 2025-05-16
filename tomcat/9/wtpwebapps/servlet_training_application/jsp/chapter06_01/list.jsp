<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>list.jsp</title>
</head>
<body>
	<h2>Chapter06_01_list</h2>
	<table border="1">
		<tr>
			<th>学籍番号</th>
			<th>名前</th>
			<th>性別</th>
			<th>学年</th>
			<th></th>
		</tr>
		<c:forEach var="student" items="${studentList}">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.name}</td>
				<td>${student.gender}</td>
				<td>${student.grade}</td>
				<td>
					<form action="<%=request.getContextPath()%>/Chapter06_01/update" method="post">
						<input type="hidden" name="studentId" value="${student.studentId}" />
						<input type="submit" value="更新" />
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>

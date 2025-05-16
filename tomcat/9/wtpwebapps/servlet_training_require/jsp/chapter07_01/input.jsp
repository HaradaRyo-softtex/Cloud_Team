<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<title>Chapter07_01</title>
</head>
<body>

	<article class="main">
		<h2>Chapter07_01</h2>
		<p>好きな音楽ジャンルを選んでください（複数選択可能）</p>
		<form action="<%=request.getContextPath()%>/Chapter07_01" method="post">
			<input type="checkbox" name="genre" value="ロック" />ロック<br>
			<input type="checkbox" name="genre" value="ポップス" />ポップス<br>
			<input type="checkbox" name="genre" value="ジャズ" />ジャズ<br>
			<input type="checkbox" name="genre" value="クラシック" />クラシック<br>
			<input type="checkbox" name="genre" value="演歌" />演歌<br>
			<input type="submit" value="送信" />
		</form>
	</article>

</body>
</html>

<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>ファイルアップロードサンプル</title>
	</head>
	<body>
		<h1>ファイルをアップロードするサンプル</h1>
		<form action="<%=request.getContextPath() %>/fileUpload" method="POST" enctype="multipart/form-data">
			<p>送りたいメッセージ<input type="text" name="message"/></p>
			<p><input type="file" name="image" /></p>
			<p><input type="submit" value="アップロード" /></p>
		</form>
	</body>
</html>
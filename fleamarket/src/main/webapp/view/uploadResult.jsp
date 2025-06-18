<%@page contentType="text/html; charset=UTF-8"%>

<%
String errorMessage = (String)request.getAttribute("errorMessage");
String message = (String)request.getAttribute("message");
String fileName = (String)request.getAttribute("fileName");
%>

<html>
	<head>
		<title>アップロード結果を表示する</title>
	</head>
	<body>
		<div style="text-align:center">
			<% if(!errorMessage.equals("")){ %>
				<!-- エラー発生時 -->
				<p><%= errorMessage %></p>
			<%} else {%>
				<!-- アップロード成功時 -->
				<p><%= message %></p>
				<img src="<%=request.getContextPath() %>/file/<%= fileName %>">
			<%} %>
		</div>
	</body>
</html>
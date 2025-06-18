<%@page contentType="text/html; charset=UTF-8"%>
<%
String error = (String) request.getAttribute("error");
String cmd = (String) request.getAttribute("cmd");
%>

<html>
<head>
<title>error</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>


	<p style="text-align: center">●●エラー●●</p>

	<p style="text-align: center"><%=error%></p>



	<div style="text-align: center">
		<%
		if (cmd.equals("menu")) {
		%>
		<a href="<%=request.getContextPath()%>/view/menu.jsp"
			style="text-align: center">メニュー</a>
		<%
		} else if (cmd.equals("list")) {
		%>
		<a href="<%=request.getContextPath()%>/list"
			style="text-align: center">書籍一覧</a>
		<%
		} else if (cmd.equals("logout")) {
		%>
		<a href="<%=request.getContextPath()%>/logout"
			style="text-align: center">ログアウト</a>
		<%
		} else if (cmd.equals("listUser")) {
		%>
		<a href="<%=request.getContextPath()%>/listUser"
			style="text-align: center">ユーザー一覧</a>
		<%
		}
		%>

	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
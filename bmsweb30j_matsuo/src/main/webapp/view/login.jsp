<%@page contentType="text/html; charset=UTF-8"%>
<%
String message = (String) request.getAttribute("message");

String user = ""; //ユーザーを管理する変数
String pass = ""; //パスワードを管理する変数

Cookie[] userCookie = request.getCookies(); //クッキーの取得

//クッキーがあるか判定
if (userCookie != null) {
	for (int i = 0; i < userCookie.length; i++) {
		//クッキーからユーザー情報の取得
		if (userCookie[i].getName().equals("userid")) {
	user = userCookie[i].getValue();
		}
		//クッキーからパスワード情報の取得
		if (userCookie[i].getName().equals("password")) {
	pass = userCookie[i].getValue();
		}
	}
}
%>

<html>
<head>
<title>login</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>


	<hr
		style="margin-botext-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<form action="<%=request.getContextPath()%>/login" method="post">
			<table style="margin: auto">
				<%
				if (message != null) {
				%>
				<tr>
					<td colspan=2><%=message%></td>
				</tr>
				<%
				}
				%>

				<tr>

					<th style="background-color: #6666ff; width: 200px">ユーザー</th>
					<td><input type=text size="30" name="userid" value=<%=user%>></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">パスワード</th>
					<td><input type=password size="30" name="password" value=<%=pass%>></input></td>

				</tr>

				<tr>

					<td colspan=2 style="text-align: center"><input type="submit"
						name="login" value="ログイン"></input></td>

				</tr>


			</table>
	</div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,jakarta.servlet.http.HttpSession"%>

<html>
<head>
<title>書籍管理メニュー画面</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<%@ include file="/common/userInfo.jsp"%>

	<p style="text-align: center; font-size: 24px">MENU</p>
	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">

	<div style="margin-bottom: 350px">
		<table style="margin: auto; border: 0;">
			<tr>
				<td><a href="<%=request.getContextPath()%>/list">【書籍一覧】</a></td>
			</tr>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/view/insert.jsp">【書籍登録】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("一般ユーザー")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/showCart">【カート状況確認】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/insertIniData">【初期データ登録】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/showOrderedItem">【購入状況確認】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("一般ユーザー")) {
			%>
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/showHistoryOrderedItem">【購入履歴】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/showSalesByMonth">【売上げ状況】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/view/insertUser.jsp">【ユーザー登録】</a></td>
			</tr>
			<%
			}
			%>
			<%
			if (auth.equals("管理者")) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/listUser">【ユーザー一覧】</a></td>
			</tr>
			<%
			}
			%>
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/view/changePassword.jsp">【パスワード変更】</a></td>
			</tr>
			<tr>
				<td><a href="<%=request.getContextPath()%>/logout">【ログアウト】</a></td>
			</tr>

		</table>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
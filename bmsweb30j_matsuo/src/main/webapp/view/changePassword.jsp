<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.User,jakarta.servlet.http.HttpSession"%>
<%
User user = (User) session.getAttribute("user");
%>
<html>
<head>
<title>changePassword</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">パスワード変更</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<form action="<%=request.getContextPath()%>/changePassword"
			method="get">
			<table style="margin: auto">
				<tr>

					<th style="background-color: #6666ff; width: 200px">ユーザー</th>
					<td><%=user.getUserid()%></td>

				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">旧パスワード</th>
					<td><input type=text size="30" name="nowpass"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">新パスワード</th>
					<td><input type=text size="30" name="newpass1"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">新パスワード（確認用）</th>
					<td><input type=text size="30" name="newpass2"></input></td>

				</tr>

				<tr>

					<td colspan=2 style="text-align: center"><input type="submit"
						name="change" value="変更"></input></td>

				</tr>

				</form>
			</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
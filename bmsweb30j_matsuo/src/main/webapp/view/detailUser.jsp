<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,util.MyFormat,jakarta.servlet.http.HttpSession"%>
<%
User user = (User) request.getAttribute("user");
String userid = user.getUserid();
String password = user.getPassword();
String email = user.getEmail();
String authority = user.getAuthority();

String auth = null;

if (authority.equals("1")) {
	auth = "一般ユーザー";
} else if (authority.equals("2")) {
	auth = "管理者";
}


%>
<html>
<head>
<title>detailUser</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/insertUser.jsp">ユーザー登録</a>]
			</td>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/listUser">ユーザー一覧</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">ユーザー詳細情報</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">
		<table style="margin: auto">

			<tr>
				<td style="text-align: center">
					<form action="<%=request.getContextPath()%>/detailUser">
						<input type="submit" name="detailUser" value="変更"></input> <input
							type="hidden" name="cmd" value="updateUser"> <input
							type="hidden" name="userid" value="<%=userid%>">
					</form>
				</td>
				<td style="text-align: center">
					<form action="<%=request.getContextPath()%>/deleteUser">
						<input type="submit" name="deleteUser" value="削除"></input> <input
							type="hidden" name="userid" value="<%=userid%>">
					</form>
				</td>
				
			</tr>
			<tr>

				<th style="background-color: #6666ff; width: 200px">ユーザー</th>
				<td style="background-color: #66ffc9; width: 200px"><%=userid%></td>

			</tr>

			<tr>

				<th style="background-color: #6666ff; width: 200px">パスワード</th>
				<td style="background-color: #66ffc9; width: 200px"><%=password%></td>

			</tr>

			<tr>

				<th style="background-color: #6666ff; width: 200px">Eメール</th>
				<td style="background-color: #66ffc9; width: 200px"><%=email%></td>

			</tr>
			<tr>

				<th style="background-color: #6666ff; width: 200px">権限</th>
				<td style="background-color: #66ffc9; width: 200px"><%=auth%></td>

			</tr>
		</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
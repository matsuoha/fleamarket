<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,util.MyFormat"%>
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
<title>updateUser</title>
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
			<td style="text-align: center; width: 508px; font-size: 24px;">ユーザー変更</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<form action="<%=request.getContextPath()%>/updateUser">
			<table style="margin: auto">

				<tr>
					<th style=wifth: 200px></th>
					<th style="width: 200px"">変更前情報</th>
					<th style="width: 200px"">変更後情報</th>
				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">ユーザー</th>
					<td style="background-color: #66ffc9; width: 200px"><%=userid%></td>
					<td style="width: 200px""><%=userid%></td>
					<input type="hidden" name="userid" value=<%=userid%>>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">パスワード</th>
					<td style="background-color: #66ffc9; width: 200px"><%=password%></td>
					<td><input type=password size="30" name="newpass1"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">パスワード（確認用）</th>
					<td style="background-color: #66ffc9; width: 200px"></td>
					<td><input type=password size="30" name="newpass2"></input></td>

				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">Eメール</th>
					<td style="background-color: #66ffc9; width: 200px"><%=email%></td>
					<td><input type=text size="30" name="email"></input></td>

				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">権限</th>
					<td style="background-color: #66ffc9; width: 200px"><%=auth%></td>
					<td><select name="authority">
							<option value=""></option>
							<option value="general">一般ユーザー</option>
							<option value="admin">管理者</option>
					</select></td>

				</tr>


				<td colspan=3 style="text-align: center"><input type="submit"
					name="updateUser" value="変更完了"></input></td>

				</form>
			</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
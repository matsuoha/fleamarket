<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<html>
<head>
<title>insertUser</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/listUser">ユーザー一覧</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">ユーザー登録</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<form action="<%=request.getContextPath()%>/insertUser" method="get">
			<table style="margin: auto">
				<tr>

					<th style="background-color: #6666ff; width: 200px">ユーザー</th>
					<td><input type=text size="30" name="userid"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">パスワード</th>
					<td><input type=password size="30" name="newpass1"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">パスワード（確認用）</th>
					<td><input type=password size="30" name="newpass2"></input></td>

				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">Eメール</th>
					<td><input type=text size="30" name="email"></input></td>

				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">権限</th>
					<td><select name="authority">
							<option value=""></option>
							<option value="1">一般ユーザー</option>
							<option value="2">管理者</option>
					</select></td>

				</tr>

				<tr>

					<td colspan=2 style="text-align: center"><input type="submit"
						name="insertUser" value="登録"></input></td>

				</tr>

				</form>
			</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
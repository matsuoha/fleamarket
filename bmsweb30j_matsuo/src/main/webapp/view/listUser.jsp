<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Book,util.MyFormat,jakarta.servlet.http.HttpSession,bean.User"%>
<html>
<head>
<title>listUser</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/insertUser.jsp">ユーザー登録</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">ユーザー一覧</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<table style="margin: auto">
			<tr>
				<td>
					<form action="<%=request.getContextPath()%>/listUser">
						ユーザー<input type=text size="30" name="searchUserid"></input> <input
							type="submit" value="検索"></input>
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/listUser">
						<input type="submit" name="searchall" value="全件表示"></input>
					</form>
				</td>
			</tr>
		</table>

		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">ユーザー</th>
				<th style="background-color: #6666ff; width: 200px">Eメール</th>
				<th style="background-color: #6666ff; width: 200px">権限</th>
				<th style="background-color: #6666ff; width: 300px" colspan="2"></th>
			</tr>
			<%
			ArrayList<User> list = (ArrayList<User>) request.getAttribute("user_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					User users = (User) list.get(i);
			%>
			<tr>
				<td style="text-align: center; width: 200px"><a
					href="<%=request.getContextPath()%>/detailUser?userid=<%=users.getUserid()%>&cmd=detailUser"><%=users.getUserid()%></a></td>
				<td style="text-align: center; width: 200px"><%=users.getEmail()%></td>
				<%
				if (users.getAuthority().equals("1")) {
				%>
				<td style="text-align: center; width: 200px">一般ユーザー</td>
				<%
				}
				%>
				<%
				if (users.getAuthority().equals("2")) {
				%>
				<td style="text-align: center; width: 200px">管理者</td>
				<%
				}
				%>
				<td style="text-align: center; width: 125px"><a
					href="<%=request.getContextPath()%>/detailUser?userid=<%=users.getUserid()%>&cmd=updateUser">変更</a>
				</td>
				<td style="text-align: left; width: 125px"><a
					href="<%=request.getContextPath()%>/deleteUser?userid=<%=users.getUserid()%>">削除</a>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 250px" colspan="3">&nbsp;</td>
			</tr>
			<%
			}
			%>
		</table>

	</div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
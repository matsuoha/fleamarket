<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book,util.MyFormat,jakarta.servlet.http.HttpSession,bean.User"%>
<%
Book book = (Book) request.getAttribute("book");
String isbn = book.getIsbn();
String title = book.getTitle();
int price = book.getPrice();
MyFormat mf = new MyFormat();

User user = (User) session.getAttribute("user");

String auth = null;

if (user.getAuthority().equals("1")) {
	auth = "一般ユーザー";
} else if (user.getAuthority().equals("2")) {
	auth = "管理者";
}
%>
<html>
<head>
<title>detail</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<%
			if (auth.equals("管理者")) {
			%>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
			</td>
			<%
			}
			%>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">書籍詳細情報</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">
		<table style="margin: auto">

			<tr>

				<%
				if (auth.equals("管理者")) {
				%>
				<td style="text-align: center">
					<form action="<%=request.getContextPath()%>/detail">
						<input type="submit" name="detail" value="変更"></input> <input
							type="hidden" name="cmd" value="update"> <input
							type="hidden" name="isbn" value="<%=book.getIsbn()%>">
					</form>
				</td>
				<td style="text-align: center">
					<form action="<%=request.getContextPath()%>/delete">
						<input type="submit" name="delete" value="削除"></input> <input
							type="hidden" name="isbn" value="<%=book.getIsbn()%>">
					</form>
				</td>
				<%
				}
				%>
			</tr>
			<tr>

				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<td style="background-color: #66ffc9; width: 200px"><%=isbn%></td>

			</tr>

			<tr>

				<th style="background-color: #6666ff; width: 200px">TITLE</th>
				<td style="background-color: #66ffc9; width: 200px"><%=title%></td>

			</tr>

			<tr>

				<th style="background-color: #6666ff; width: 200px">価格</th>
				<td style="background-color: #66ffc9; width: 200px"><%=mf.moneyFormat(price)%></td>

			</tr>
		</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
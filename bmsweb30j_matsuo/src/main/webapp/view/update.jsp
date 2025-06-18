<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book,util.MyFormat"%>
<%
Book book = (Book) request.getAttribute("book");
String isbn = book.getIsbn();
String title = book.getTitle();
int price = book.getPrice();
MyFormat mf = new MyFormat();
%>
<html>
<head>
<title>update</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
			</td>
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

		<form action="<%=request.getContextPath()%>/update">
			<table style="margin: auto">

				<tr>
					<th style=wifth: 200px></th>
					<th style="width: 200px"">変更前情報</th>
					<th style="width: 200px"">変更後情報</th>
				</tr>
				<tr>

					<th style="background-color: #6666ff; width: 200px">ISBN</th>
					<td style="background-color: #66ffc9; width: 200px"><%=isbn%></td>
					<td style="width: 200px""><%=isbn%></td>
					<input type="hidden" name="isbn" value=<%=isbn%>>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">TITLE</th>
					<td style="background-color: #66ffc9; width: 200px"><%=title%></td>
					<td><input type=text size="30" name="title"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">価格</th>
					<td style="background-color: #66ffc9; width: 200px"><%=mf.moneyFormat(price)%></td>
					<td><input type=text size="30" name="price"></input></td>

				</tr>


				<td colspan=3 style="text-align: center"><input type="submit"
					name="update" value="変更完了"></input></td>

				</form>
			</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
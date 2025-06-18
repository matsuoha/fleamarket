<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<%
MyFormat mf = new MyFormat();
%>
<html>
<head>
<title>insertIniData</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">初期データ登録</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<table style="margin: auto">
			<tr>
				<td colspan=3
					style="text-align: left; width: 300px; font-size: 20px;">初期データとして以下のデータを登録しました。</td>
			</tr>


			<tr>
				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<th style="background-color: #6666ff; width: 200px">TITLE</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
			</tr>
			<%
			ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Book book = (Book) list.get(i);
			%>
			<tr>
				<td style="text-align: center; width: 200px"><%=book.getIsbn()%></td>
				<td style="text-align: center; width: 200px"><%=book.getTitle()%></td>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(book.getPrice())%></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
			</tr>
			<%
			}
			%>
		</table>

	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
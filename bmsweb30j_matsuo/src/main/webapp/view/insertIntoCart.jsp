<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book,util.MyFormat,bean.Order"%>
<%
Book book = (Book) request.getAttribute("Book");
String isbn = book.getIsbn();
String title = book.getTitle();
int price = book.getPrice();

Order order = (Order) request.getAttribute("Order");
int quantity = order.getQuantity();

MyFormat mf = new MyFormat();
%>
<html>
<head>
<title>insertIntoCart</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">カート追加</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">
		<table style="margin: auto">

			<td colspan=2
				style="text-align: center; width: 300px; font-size: 20px;">下記の書籍をカートに追加しました。</td>
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
			<tr>

				<th style="background-color: #6666ff; width: 200px">購入数</th>
				<td style="background-color: #66ffc9; width: 200px"><%=quantity%>冊</td>

			</tr>


			<tr>
				<form action="<%=request.getContextPath()%>/showCart">
					<td colspan=2 style="text-align: center"><input type="submit"
						name="showCart" value="カート確認"></input></td>
			</tr>
		</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
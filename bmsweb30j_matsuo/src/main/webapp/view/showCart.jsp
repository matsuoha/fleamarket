<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book,util.MyFormat,bean.Sale"%>
<%
MyFormat mf = new MyFormat();
%>
<html>
<head>
<title>showCart</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">カート内容</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<th style="background-color: #6666ff; width: 200px">TITLE</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
				<th style="background-color: #6666ff; width: 300px">購入数</th>
				<th style="background-color: #6666ff; width: 300px"></th>
			</tr>
			<%
			int total = 0;
			ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Sale sale = (Sale) list.get(i);
					total += sale.getPrice() * sale.getQuantity();
			%>
			<tr>
				<td style="text-align: center; width: 200px"><a
					href="<%=request.getContextPath()%>/detail?isbn=<%=sale.getIsbn()%>&cmd=detail"><%=sale.getIsbn()%></a></td>
				<td style="text-align: center; width: 200px"><%=sale.getTitle()%></td>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(sale.getPrice())%></td>
				<td style="text-align: center; width: 200px"><%=sale.getQuantity()%></td>
				<td style="text-align: center; width: 125px"><a
					href="<%=request.getContextPath()%>/showCart?delno=<%=sale.getIsbn()%>">削除</a>
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
				<td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
			</tr>
			<%
			}
			%>
			<tr>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td
					style="text-align: center; background-color: #6666ff; width: 200px">合計</td>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(total)%></td>
			</tr>
		</table>

		<br>

		<table style="margin: auto">
			<tr style="text-align: center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<form action="<%=request.getContextPath()%>/buyConfirm">
					<td><input type="submit" name="buyConfirm" value="購入"></input></td>
			</tr>
		</table>

	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
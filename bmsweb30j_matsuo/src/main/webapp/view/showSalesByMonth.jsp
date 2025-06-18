<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Book,bean.Sale,util.MyFormat,jakarta.servlet.http.HttpSession,bean.User"%>
<%
MyFormat mf = new MyFormat();

String date = (String) request.getAttribute("dispDate");
if(date.equals("null年null月")){
date = "";
}
%>
<html>
<head>
<title>showSalesByMonth</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<td style="text-align: center; width: 508px; font-size: 24px;"><%=date%>売り上げ状況</td>
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
					<form action="<%=request.getContextPath()%>/showSalesByMonth">
						年<input type=text size="30" name="year"></input> 月<input type=text
							size="30" name="month"></input> <input type="submit"
							name="showSaleByMonth" value="検索"></input>
					</form>
				</td>
			</tr>
		</table>

		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<th style="background-color: #6666ff; width: 200px">Title</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
				<th style="background-color: #6666ff; width: 200px">数量</th>
				<th style="background-color: #6666ff; width: 200px">売り上げ小計</th>
			</tr>
			<%
			ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("sale_list");
			int total = 0;
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Sale books = (Sale) list.get(i);
					total += books.getPrice() * books.getQuantity();
			%>
			<tr>
				<td style="text-align: center; width: 200px"><%=books.getIsbn()%></td>
				<td style="text-align: center; width: 200px"><%=books.getTitle()%></td>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(books.getPrice())%></td>
				<td style="text-align: center; width: 200px"><%=books.getQuantity()%></td>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(books.getPrice() * books.getQuantity())%></td>
			</tr>

			<%
			}
			%>
			<tr>
				<td style="text-align: center; width: 200px"></td>
				<td style="text-align: center; width: 200px"></td>
				<td style="text-align: center; width: 200px"></td>
				<th style="background-color: #6666ff; width: 200px">合計</th>
				<td style="text-align: center; width: 200px"><%=mf.moneyFormat(total)%></td>
			</tr>
			<%
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
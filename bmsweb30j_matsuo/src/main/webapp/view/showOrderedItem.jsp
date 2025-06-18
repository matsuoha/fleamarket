<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderedItem"%>
<html>
<head>
<title>showOrderedItem</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table style="margin: auto; width: 850px">
		<tr>
			<td nowrap style="text-align: center; width: 80px">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td style="text-align: center; width: 508px; font-size: 24px;">購入状況</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<table style="margin: auto">


			<tr>
				<th style="background-color: #6666ff; width: 200px">ユーザー</th>
				<th style="background-color: #6666ff; width: 200px">Title</th>
				<th style="background-color: #6666ff; width: 200px">数量</th>
				<th style="background-color: #6666ff; width: 200px">注文日</th>
			</tr>
			<%
			ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) request.getAttribute("ordered_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					OrderedItem ordered = (OrderedItem) list.get(i);
			%>
			<tr>
				<td style="text-align: center; width: 200px"><%=ordered.getUserid()%></td>
				<td style="text-align: center; width: 200px"><%=ordered.getTitle()%></td>
				<td style="text-align: center; width: 200px"><%=ordered.getQuantity()%></td>
				<td style="text-align: center; width: 200px"><%=ordered.getDate()%></td>
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
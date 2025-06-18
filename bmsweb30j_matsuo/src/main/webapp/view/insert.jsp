<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<html>
<head>
<title>insert</title>
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
			<td style="text-align: center; width: 508px; font-size: 24px;">書籍登録</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>
		</tr>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 250px">

		<form action="<%=request.getContextPath()%>/insert" method="get">
			<table style="margin: auto">
				<tr>

					<th style="background-color: #6666ff; width: 200px">ISBN</th>
					<td><input type=text size="30" name="isbn"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">TITLE</th>
					<td><input type=text size="30" name="title"></input></td>

				</tr>

				<tr>

					<th style="background-color: #6666ff; width: 200px">価格</th>
					<td><input type=text size="30" name="price"></input></td>

				</tr>

				<tr>

					<td colspan=2 style="text-align: center"><input type="submit"
						name="insert" value="登録"></input></td>

				</tr>

				</form>
			</table>
	</div>

	<hr
		style="text-align: center; height: 5px; background-color: blue; width: 950px">

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession,bean.User"%>

<%
User user = (User) session.getAttribute("user");

String auth = null;

if(user.getAuthority().equals("1")) {
	auth = "一般ユーザー";
}else if(user.getAuthority().equals("2")){
	auth = "管理者";
}

if (user == null) {
	request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd", "logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>

<table align="right">
	<tr>
		<td style="text-align: left;">名前：<%=user.getUserid()%>
		</td>
	</tr>
	<tr>
		<td style="text-align: left;">権限：<%=auth %></td>
	</tr>

</table>
<div style="clear:both" ></div>
<table>
<hr
	style="text-align: center; height: 2px; background-color: black; width: 950px">
	</table>
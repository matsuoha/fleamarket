<%@page contentType="text/html; charset=UTF-8"%>

<%
//エラーメッセージの取得
String message = (String)request.getAttribute("message");

//エラーが無いとき
if(message == null){
	message = "";
}
%>

<html>

<head>
	<title>ログイン画面</title>
</head>

<body>
	<div class="site">
        <table>
        <th><h1>神田雑貨店</h1></th>
        <th><p>「雑貨を買いたい人」、「雑貨を売りたい人」を引き合わせる</p></th>
        </table>
    </div>

	<div align="center">
		<h1>ログイン</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<p>ユーザーID<input type="text" size="30" name="userid"></p>
			<p>パスワード<input type="password" size="30" name="password"></p>
			<input type="submit" value="送信">
			<p><a href="userInsert.html">【初めての方はこちら】</a></p>
			
			<%=message %>
			
			
		</form>
	</div>
</body>

</html
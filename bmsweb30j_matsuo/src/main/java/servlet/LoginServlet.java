/*
 * プログラム名：書籍管理システムweb版3.0
 * プログラムの説明：書籍情報(ISBN、書籍名、価格)を一元管理するシステムです。
 * 					選択されたメニューに応じて各処理が実行されます。
 * 作成者：松尾駿
 * 作成日：2025年6月12日
 * 
 */
package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// 入力データ取得
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		try {

			UserDAO objDao = new UserDAO();

			User userInfo = objDao.selectByUser(userid, password);

			// ログインデータをクッキーに登録
			if (userInfo.getUserid() != null) {

				HttpSession session = request.getSession();
				session.setAttribute("user", userInfo);

				Cookie useridCookie = new Cookie("userid", userid);
				useridCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(useridCookie);

				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);

				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

			}
			// ログイン情報が間違っているとき再ログイン
			if (userInfo.getUserid() == null) {

				request.setAttribute("message", "入力データが間違っています！");

				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログインは出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}

	}
}
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
import java.util.ArrayList;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/listUser")
public class ListUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {

			// セッション切れ
			if (user == null) {

				error = "セッション切れの為、ユーザ一覧表示は行えません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			ArrayList<User> userList;

			UserDAO objDao = new UserDAO();

			String searchUserid = request.getParameter("searchUserid");

			if (searchUserid == null) {
				// 全検索メソッド呼び出し
				userList = objDao.selectAll();

			} else {
				// キーワード検索メソッド呼び出し
				userList = objDao.search(searchUserid);
			}

			request.setAttribute("user_list", userList);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ユーザ一覧は表示出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {

			if (error != null) {
				// エラー画面へ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// ユーザー一覧画面へ遷移
			request.getRequestDispatcher("/view/listUser.jsp").forward(request, response);
		}
	}
}
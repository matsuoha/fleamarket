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

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		String userid = request.getParameter("userid");

		try {

			UserDAO objDao = new UserDAO();
			int count = objDao.delete(userid);

			// SQL文の発行に失敗
			if (count == 0) {

				error = "クエリ発行に失敗しました。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);
			}

			request.getRequestDispatcher("/listUser").forward(request, response);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ユーザー削除は行えません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}
}
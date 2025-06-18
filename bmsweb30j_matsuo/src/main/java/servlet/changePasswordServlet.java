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

@WebServlet("/changePassword")
public class changePasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// 入力データ取得
		String nowpass = request.getParameter("nowpass");
		String newpass1 = request.getParameter("newpass1");
		String newpass2 = request.getParameter("newpass2");

		try {

			// セッション切れエラー
			if (user == null) {
				error = "セッション切れの為、パスワード変更は行えません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			// 旧パスワード未入力
			if (nowpass.equals("")) {

				error = "旧パスワードを入力して下さい！";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 新パスワード未入力
			if (newpass1.equals("")) {

				error = "新パスワードを入力して下さい！";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 確認用パスワード未入力
			if (newpass2.equals("")) {

				error = "新パスワード（確認用）を入力して下さい！";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;

			}

			UserDAO objDao = new UserDAO();
			user = objDao.selectByUser(user.getUserid());

			// 旧パスワードが間違っている
			if (!user.getPassword().equals(nowpass)) {

				error = "旧パスワードが間違っています！";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 新パスワードと確認用パスワードが一致しない
			if (!newpass1.equals(newpass2)) {
				error = "新パスワードと確認パスワードが合っていません！";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;
			}

			// パスワード更新
			int count = objDao.updateForPassword(user.getUserid(), newpass1);

			// SQL文の発行に失敗
			if (count == 0) {

				error = "クエリ発行に失敗しました。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			user.setPassword(newpass1);
			session.setAttribute("user", user);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、パスワード変更は行えません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {

			if (error != null) {
				// エラー画面へ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// メニュー画面へ遷移
			request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
		}

	}
}
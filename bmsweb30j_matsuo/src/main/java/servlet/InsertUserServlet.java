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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insertUser")
public class InsertUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// 入力データ取得
		String userid = request.getParameter("userid");
		String newpass1 = request.getParameter("newpass1");
		String newpass2 = request.getParameter("newpass2");
		String email = request.getParameter("email");
		String authority = request.getParameter("authority");

		try {

			// ユーザー名未入力
			if (userid.equals("")) {

				error = "ユーザー入力値不正の為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}
			// パスワード未入力
			if (newpass1.equals("")) {

				error = "パスワード入力値不正の為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}
			// 確認用パスワード未入力
			if (newpass2.equals("")) {

				error = "パスワード(確認用)入力値不正の為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}
			// Eメール未入力
			if (email.equals("")) {

				error = "Ｅメール入力値不正の為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 権限未選択
			if (authority.equals("")) {

				error = "権限が未選択の為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 入力パスワードと確認用パスワードが不一致
			if (!newpass1.equals(newpass2)) {

				error = "入力パスワードがパスワード(確認用)と一致しない為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;
			}

			UserDAO objDao = new UserDAO();
			User user = objDao.selectByUser(userid);

			// ユーザー名が登録済み
			if (user.getUserid() != null) {

				error = "入力ユーザー名は既に使用済みの為、登録できません。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 登録処理
			User objUser = new User();
			objUser.setUserid(userid);
			objUser.setPassword(newpass1);
			objUser.setEmail(email);
			objUser.setAuthority(authority);

			int count = objDao.insert(objUser);

			// SQL文の発行に失敗
			if (count == 0) {
				error = "クエリ発行に失敗しました。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;

			}

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、、ユーザ登録は行えません。";
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
			request.getRequestDispatcher("/listUser").forward(request, response);
		}
	}
}
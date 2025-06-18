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

import bean.OrderedItem;
import bean.User;
import dao.OrderedItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/showHistoryOrderedItem")
public class ShowHistoryOrderedItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {

			// セッション切れ
			if (user == null) {

				error = "セッション切れの為、購入状況の確認は出来ません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			OrderedItemDAO objDao = new OrderedItemDAO();
			ArrayList<OrderedItem> list = objDao.selectByUser(user.getUserid());

			request.setAttribute("ordered_list", list);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、購入状況確認は出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {

			if (error != null) {
				// エラー画面へ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// 購入状況確認画面へ遷移
			request.getRequestDispatcher("/view/showHistoryOrderedItem.jsp").forward(request, response);
		}

	}
}
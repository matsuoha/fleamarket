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

import bean.Book;
import bean.Order;
import bean.Sale;
import bean.User;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ISBN取得
		String isbn = request.getParameter("isbn");
		String quantity = request.getParameter("quantity");

		try {
			// セッション切れ
			if (user == null) {

				error = "セッション切れの為、カートに追加出来ません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			// 特定ISBNの書籍データ取得
			BookDAO objDao = new BookDAO();
			Book Book = objDao.selectByIsbn(isbn);

			// リクエストスコープに登録
			request.setAttribute("Book", Book);

			// ISBN、ユーザーid、数量を設定
			Order order = new Order();
			order.setIsbn(isbn);
			order.setUserid(user.getUserid());
			order.setQuantity(Integer.parseInt(quantity));

			// セッションから注文リスト取得
			ArrayList<Order> list = (ArrayList<Order>) session.getAttribute("order_list");

			// 取得できなかった場合新規配列
			if (list == null) {
				list = new ArrayList<Order>();
			}

			int count = 0;
			for (int i = 0; i < list.size(); i++) {

				if (isbn.equals(list.get(i).getIsbn())) {
					list.get(i).setQuantity(list.get(i).getQuantity() + Integer.parseInt(quantity));
					count++;
				}
			}
			// 配列に追加
			if (count == 0) {
				list.add(order);
			}

			request.setAttribute("Order", order);
			// セッションに登録
			session.setAttribute("order_list", list);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、カートに追加は出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				// エラー画面遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
			// カート中身確認画面に遷移
			request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
		}

	}
}
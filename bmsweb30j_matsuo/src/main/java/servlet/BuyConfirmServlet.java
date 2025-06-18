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
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {
			// セッション切れエラー
			if (user == null) {
				error = "セッション切れの為、購入は出来ません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			// 注文リスト取得
			ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order_list");

			// カートの中身が空
			if (order_list.size() == 0) {
				error = "カートの中に何も無かったので、購入は出来ません。";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;
			}

			BookDAO objBookDao = new BookDAO();
			OrderDAO objOrderDao = new OrderDAO();
			ArrayList<Book> list = new ArrayList<Book>();
			ArrayList<Sale> book_list = new ArrayList<Sale>();

			// 購入書籍情報を配列に格納
			for (int i = 0; i < order_list.size(); i++) {
				Order order = order_list.get(i);
				Book Book = objBookDao.selectByIsbn(order.getIsbn());
				objOrderDao.insert(order);
				list.add(Book);
				Sale bookInfo = new Sale(Book, order.getQuantity());
				book_list.add(bookInfo);
			}

			// メール送信
			SendMail send = new SendMail();
			send.sendMail(user, book_list);

			// リクエストスコープに登録
			request.setAttribute("book_list", book_list);

			// セッションクリア
			session.setAttribute("order_list", null);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、購入は出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {

			if (error != null) {
				// エラー画面へ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// 購入品確認画面へ遷移
			request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
		}

	}
}
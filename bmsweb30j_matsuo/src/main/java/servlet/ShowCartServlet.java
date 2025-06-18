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

@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;
		String delno = request.getParameter("delno");

		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {

			// セッション切れエラー
			if (user == null) {
				error = "セッション切れの為、カート状況は確認出来ません。";
				request.setAttribute("error", error);

				cmd = "logout";
				request.setAttribute("cmd", cmd);

				return;
			}

			ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order_list");

			// delnoがnullじゃないなら該当書籍削除
			if (delno != null) {
				order_list.remove(Integer.parseInt(delno));
			}

			ArrayList<Sale> book_list = new ArrayList<Sale>();

			BookDAO objDao = new BookDAO();
			ArrayList<Book> list = new ArrayList<Book>();

			// 配列に格納
			for (int i = 0; i < order_list.size(); i++) {
				Order order = order_list.get(i);
				Book Book = objDao.selectByIsbn(order.getIsbn());
				list.add(Book);
				Sale bookInfo = new Sale(Book, order.getQuantity());
				book_list.add(bookInfo);
			}

			// リクエストスコープに登録
			request.setAttribute("book_list", book_list);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、カート状況は確認出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				// エラー画面に遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// カート中身表示画面に遷移
			request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
		}
	}
}
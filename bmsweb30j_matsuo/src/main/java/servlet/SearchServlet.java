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
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");

		// 入力値取得
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String price = request.getParameter("price");

		String error = null;
		String cmd = null;

		try {
			BookDAO objDao = new BookDAO();

			ArrayList<Book> list = objDao.search(isbn, title, price);

			request.setAttribute("book_list", list);

			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}
}
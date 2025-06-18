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

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = null;

		Book book = new Book();
		BookDAO objDao = new BookDAO();

		response.setCharacterEncoding("UTF-8");

		// 入力値の取得
		String isbn = request.getParameter("isbn");
		String cmd = request.getParameter("cmd");

		try {

			book = objDao.selectByIsbn(isbn);

			// 表示対象なしエラー
			if (book.getIsbn() == null && cmd.equals("detail")) {

				error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}
			// 更新対象なしエラー
			if (book.getIsbn() == null && cmd.equals("update")) {

				error = "更新対象の書籍が存在しない為、変更画面は表示できませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// リクエストスコープにbookを格納し、detail.jspに遷移
			request.setAttribute("book", book);

			if (cmd.equals("detail")) {

				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
			}

			if (cmd.equals("update")) {

				request.getRequestDispatcher("/view/update.jsp").forward(request, response);
			}

			// DB接続エラー
		} catch (IllegalStateException e) {

			if (cmd.equals("detail")) {
				error = "DB接続エラーの為、書籍詳細は表示できませんでした。";
			}
			if (cmd.equals("update")) {
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
			}
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}

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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		response.setCharacterEncoding("UFT-8");

		// 入力値の取得
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String price = request.getParameter("price");

		try {
			BookDAO BookDaoObj = new BookDAO();
			Book book = new Book();

			// タイトル未入力エラー
			if (title.equals("")) {

				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 価格未入力エラー
			if (price.equals("")) {

				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 更新対象なしエラー
			Book objBook = BookDaoObj.selectByIsbn(request.getParameter("isbn"));

			if (objBook.getIsbn() == null) {

				error = "更新対象の書籍が存在しない為、書籍更新処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 更新処理
			book.setIsbn(isbn);
			book.setTitle(title);
			int price1 = Integer.parseInt(price);
			book.setPrice(price1);

			BookDaoObj.update(book);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍更新処理は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			// 価格不正エラー
		} catch (NumberFormatException e) {

			error = "価格の値が不正の為、書籍更新処理は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "list";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			request.getRequestDispatcher("/list").forward(request, response);
		}
	}
}
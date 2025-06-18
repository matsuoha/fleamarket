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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = null;
		String cmd = null;

		Book book = new Book();
		BookDAO objDao = new BookDAO();

		response.setCharacterEncoding("UTF-8");

		// 入力値の取得
		String getIsbn = request.getParameter("isbn");
		String getTitle = request.getParameter("title");
		String getPrice = request.getParameter("price");

		try {

			// ISBN未入力エラー
			if (getIsbn.equals("")) {

				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// タイトル未入力エラー
			if (getTitle.equals("")) {

				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 価格未入力エラー
			if (getPrice.equals("")) {

				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 登録済みエラー
			Book objBook = objDao.selectByIsbn(request.getParameter("isbn"));

			if (objBook.getIsbn() != null) {

				error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;

			}

			// 書籍登録
			book.setIsbn(request.getParameter("isbn"));
			book.setTitle(request.getParameter("title"));
			int price = Integer.parseInt(request.getParameter("price"));
			book.setPrice(price);

			objDao.insert(book);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			// 価格に数値以外が入力されたときエラー
		} catch (NumberFormatException e) {

			error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "list";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				// エラー画面遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// 一覧画面遷移
			request.getRequestDispatcher("/list").forward(request, response);
		}
	}

}
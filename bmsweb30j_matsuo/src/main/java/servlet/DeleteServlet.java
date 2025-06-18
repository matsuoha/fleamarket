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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		BookDAO BookDaoObj = new BookDAO();

		// 入力ISBN取得
		String isbn = request.getParameter("isbn");

		try {

			// 削除対象なしエラー
			Book objBook = BookDaoObj.selectByIsbn(request.getParameter("isbn"));

			if (objBook.getIsbn() == null) {

				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				request.setAttribute("error", error);

				cmd = "list";
				request.setAttribute("cmd", cmd);

				return;
			}

			// データ削除メソッド呼び出し
			BookDaoObj.delete(isbn);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍削除処理は行えませんでした。";
			request.setAttribute("error", error);

			cmd = "logout";
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
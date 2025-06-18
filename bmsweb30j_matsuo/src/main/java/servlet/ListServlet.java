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

@WebServlet("/list")
public class ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;

		try {

			// 書籍データを取得し、一覧表示画面へ遷移
			BookDAO objDao = new BookDAO();

			ArrayList<Book> bookList = objDao.selectAll();

			request.setAttribute("book_list", bookList);

			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);

			String cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}

	}

}

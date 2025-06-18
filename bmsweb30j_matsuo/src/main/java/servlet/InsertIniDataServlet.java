/*
 * プログラム名：書籍管理システムweb版3.0
 * プログラムの説明：書籍情報(ISBN、書籍名、価格)を一元管理するシステムです。
 * 					選択されたメニューに応じて各処理が実行されます。
 * 作成者：松尾駿
 * 作成日：2025年6月12日
 * 
 */
package servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insertIniData")
public class InsertIniDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		try {

			BookDAO objDao = new BookDAO();
			ArrayList<Book> list = objDao.selectAll();

			// データが既に存在する
			if (list.size() != 0) {

				error = "DBにはデータが存在するので、初期データは登録できません。";
				request.setAttribute("error", error);

				cmd = "menu";
				request.setAttribute("cmd", cmd);

				return;
			}

			// ファイル読み込み
			String path = getServletContext().getRealPath("file\\initial_data.csv");
			Scanner sin = new Scanner(new File(path));

			// テキストデータ読み取り
			while (sin.hasNextLine()) {
				String[] array = sin.nextLine().split(",");
				Book book = new Book();
				book.setIsbn(array[0]);
				book.setTitle(array[1]);
				book.setPrice(Integer.parseInt(array[2]));
				list.add(book);
				objDao.insert(book);
			}

			// リクエストスコープに登録
			request.setAttribute("book_list", list);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、初期データ登録は出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			// priceが数値以外
		} catch (NumberFormatException e) {

			error = "初期データファイルが不備がある為、登録は行えません。";
			request.setAttribute("error", error);

			cmd = "menu";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			// ファイルが見つからない
		} catch (FileNotFoundException e) {

			error = "初期データファイルが無い為、登録は行えません。";
			request.setAttribute("error", error);

			cmd = "menu";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {
			if (error != null) {
				// エラー画面に遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
		// 初期データ登録完了画面遷移
		request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);
	}
}
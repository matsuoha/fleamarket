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

import bean.Sale;
import dao.SaleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showSalesByMonth")
public class ShowSalesByMonthServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		// 入力データ取得
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		try {

			SaleDAO saleDao = new SaleDAO();
			ArrayList<Sale> saleList = saleDao.selectBySales(year, month);

			String dispDate = year + "年" + month + "月";

			request.setAttribute("sale_list", saleList);
			request.setAttribute("dispDate", dispDate);

			request.getRequestDispatcher("/view/showSalesByMonth.jsp").forward(request, response);

			// DB接続エラー
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、売り上げ情報の確認は出来ません。";
			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}

	}

}
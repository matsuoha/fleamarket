package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detailUser")
public class DetailUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = null;
		String cmd = null;

		String userid = request.getParameter("userid");
		String Cmd = request.getParameter("cmd");

		try {

			UserDAO objDao = new UserDAO();
			User user = objDao.selectByUser(userid);

			// 表示対象なしエラー
			if (user.getUserid() == null && Cmd.equals("detailUser")) {

				error = "表示対象のユーザーが存在しない為、詳細画面は表示出来ませんでした。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}
			// 更新対象なしエラー
			if (user.getUserid() == null && Cmd.equals("updateUser")) {

				error = "更新対象のユーザーが存在しない為、変更画面は表示出来ませんでした。";
				request.setAttribute("error", error);

				cmd = "listUser";
				request.setAttribute("cmd", cmd);

				return;

			}

			request.setAttribute("user", user);

			// DB接続エラー
		} catch (IllegalStateException e) {

			if (Cmd.equals("detailUser")) {
				error = "DB接続エラーの為、ユーザー詳細情報は表示できません。";
			}
			if (Cmd.equals("updateUser")) {
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
			}

			request.setAttribute("error", error);

			cmd = "logout";
			request.setAttribute("cmd", cmd);

			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		} finally {

			if (error != null) {
				// エラー画面へ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// ユーザー詳細画面へ遷移
			if (Cmd.equals("detailUser")) {

				request.getRequestDispatcher("/view/detailUser.jsp").forward(request, response);
			}

			// ユーザー更新画面へ遷移
			if (Cmd.equals("updateUser")) {

				request.getRequestDispatcher("/view/updateUser.jsp").forward(request, response);
			}
		}
	}
}
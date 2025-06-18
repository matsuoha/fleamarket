package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/fileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラーメッセージ用変数
		String errorMessage = "";


		try {
			//  フォームの情報を受け取る
			String message = request.getParameter("message");
			request.setAttribute("message", message);
			
			//ファイル取得用の情報を受け取る
			Part filePart = request.getPart("image");
			String uploadDir = "";
			String filePath = "";
			
			//ファイルサイズを元にファイルの有無を確認
			if (filePart.getSize() != 0) {
				String contentDisposition = filePart.getHeader("content-disposition");
				String fileName = "";
				Pattern pattern = Pattern.compile("filename=\"(.*)\"");
				Matcher matcher = pattern.matcher(contentDisposition);
				//抽出したファイル名が存在していれば抽出、なければ空白
				if (matcher.find()) {
					fileName = matcher.group(1);					
				}else {
					fileName = "";
				}
				
				File file_name = new File(fileName);
				
				// ファイル保存先のディレクトリ
				uploadDir = getServletContext().getRealPath("/file").replace("\\", "/");
				//アップロード先のフォルダがなければ作成
				File uploadDirectory = new File(uploadDir);
				if (!uploadDirectory.exists()) {
					uploadDirectory.mkdirs();
				}
				
				// ファイルを指定されたディレクトリに保存
				// （具体的には以下の階層に保存される）
				// C:\ usr\kis_java_pkg_2023\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps 
				filePath = uploadDir + "/" + file_name.getName();
				try (InputStream inputStream = filePart.getInputStream()) {
					Files.copy(inputStream, new File(filePath).toPath(),StandardCopyOption.REPLACE_EXISTING);
				}

				//リクエストスコープにファイル名を設定
				request.setAttribute("fileName", file_name.getName());
				
			}else {
				errorMessage = "ファイルがありません";
			}
			
			
		} catch (Exception e) {
			// その他
			errorMessage = "予期せぬエラーが発生しました";
		} finally {
			// 結果画面へ遷移
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/view/uploadResult.jsp").forward(request, response);
		}

	}

}

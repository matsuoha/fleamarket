package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;

//ユーザー情報のDAO
public class UserDAO {
	// クラスパス
		private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
		// データベースパス
		private static String URL = "jdbc:mariadb://localhost/fleamarketdb";
		// データベースのユーザー名
		private static String USER = "root";
		// データベースのパスワード
		private static String PASSWD = "root123";


// データベースに接続するメソッド
    private static Connection getConnection() {
		Connection con = null;
	    try {
			Class.forName(RDB_DRIVE);
			con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
	    }
    } 

	public User selectByUser(String userid, String password) {

		// 変数宣言
		Connection con = null;
		Statement smt = null;
		// Bookクラスのオブジェクト化
		User user = new User();

		try {
			// DBに接続
			con = UserDAO.getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM userinfo WHERE user =" +
			 "'" + userid + "' AND password='" + password + "'";
			
			ResultSet rs = smt.executeQuery(sql);
			// 検索結果を格納
			while (rs.next()) {
				// ユーザーID
				user.setUser_id(rs.getInt("user_id"));
				// ログインID
				user.setLogin_id(rs.getInt("login_id"));
				// 性
				user.setLast_name(rs.getString("last_name"));
				// 名
				user.setFirst_name(rs.getString("first_name"));
				// 性(かな)
				user.setLast_name_kana(rs.getString("last_name_kana"));
				// 名(かな)
				user.setFirst_name_kana(rs.getString("first_name_kana"));
				// ニックネーム
				user.setNickname(rs.getString("nickname"));
				// 都道府県
				user.setPrefecture(rs.getString("prefecture"));
				// 市区町村
				user.setMunicipalities(rs.getString("municipalities"));
				// その他番地
				user.setAddress(rs.getString("address"));
				// 郵便番号
				user.setPost_code(rs.getString("post_code"));
				// メールアドレス
				user.setMail(rs.getString("mail"));
				// 性別(0.男 1.女 2.その他)
				user.setSex(rs.getInt("sex"));
				// 生年月日
				user.setBirth(rs.getString("birth"));
				// 年齢(Int型)
				user.setAge(rs.getInt("age"));
				// 金融機関名
				user.setBank_name(rs.getString("bank_name"));
				// 支店番号
				user.setBank_num(rs.getString("bank_num"));
				// 口座番号
				user.setAccount_num(rs.getString("account_num"));
				// 口座名義
				user.setAccount_name(rs.getString("account_name"));
				// 登録日時
				user.setUser_registration(rs.getString("user_registration"));
				// 更新日時
				user.setUser_update(rs.getString("user_update"));
				// 表示フラグ(0.表示 1.非表示)
				user.setDisplay_flag(rs.getInt("display_flag"));
				// 凍結(0.しない 1.する)
				user.setFrozen(rs.getInt("frozen"));
			}
			// 例外処理
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return user;
	}

}

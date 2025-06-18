package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Login;
import bean.User;

public class LoginDAO {

	//データベース接続情報
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/fleamarketdb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベースに接続する
	private static Connection getConnection() {
		try {
			//JDBCドライバーをロードする
			Class.forName(RDB_DRIVE);
			//Connectionオブジェクトの生成
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//指定のユーザーの情報を検索し、格納する
	public Login selectByUser(String login_id) {

		Connection con = null;
		Statement smt = null;

		//検索した情報を格納する
		Login login = new Login();

		//SQL文
		String sql = "SELECT * FROM login_info WHERE login_id = '" + login_id + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			if (rs.next()) {
				login.setLogin_id(rs.getInt("login_id"));
				login.setPassword(rs.getString("password"));
				login.setAuthority(rs.getInt("authority"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		//検索結果を返す
		return login;
	}

	//指定されたログインデータを検索し、格納する(ログインの判定をする際に使う)
	public Login selectByUser(int login_id, String password) {

		Connection con = null;
		Statement smt = null;

		//検索した情報を格納する
		Login login = new Login();

		//SQL文
		String sql = "SELECT * FROM login_info WHERE user ='" + login_id + "' AND password='" + password + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			if (rs.next()) {
				login.setLogin_id(rs.getInt("login_id"));
				login.setPassword(rs.getString("password"));
				login.setAuthority(rs.getInt("authority"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		//検索結果を返す
		return login;
	}

	//ログイン情報を取得し、ArrayListオブジェクトに格納
	public ArrayList<Login> selectAll() {

		Connection con = null;
		Statement smt = null;

		//全データを格納するuserListを生成
		ArrayList<Login> loginList = new ArrayList<Login>();

		//SQL文
		String sql = "SELECT * FROM login_info";

		try {

			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			while (rs.next()) {
				Login login = new Login();
				login.setLogin_id(rs.getInt("login_id"));
				login.setPassword(rs.getString("password"));
				login.setAuthority(rs.getInt("authority"));
				loginList.add(login);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return loginList;
	}

	//ログイン者の新規登録
	public int insert(Login login) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		//SQL文
		String sql = "INSERT INTO login_info VALUES('" + login.getLogin_id() + "','" + login.getPassword() + "','"
				+ login.getAuthority() + "')";

		try {

			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

	//該当ユーザーの削除
	public int delete(int login_id) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		//SQL文
		String sql = "DELETE FROM login_info WHERE user = '" + login_id + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

	//ユーザーデータを元にlogin_infoの書籍データの更新
	public int update(Login login) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		//SQL文
		String sql = "UPDATE login_info SET password='" + login.getPassword()
				+ "',authority='" + login.getAuthority() + "' WHERE user='" + login.getLogin_id() + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

	//ユーザーデータを曖昧に検索し、格納する
	public ArrayList<Login> serch(int login_id) {

		Connection con = null;
		Statement smt = null;

		//全データを格納するbookListを生成
		ArrayList<Login> loginList = new ArrayList<Login>();

		//SQL文
		String sql = "SELECT * FROM login_info WHERE login_id LIKE '%" + login_id + "%'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			while (rs.next()) {
				Login login = new Login();
				login.setLogin_id(rs.getInt("login_id"));
				login.setPassword(rs.getString("password"));
				login.setAuthority(rs.getInt("authority"));
				loginList.add(login);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		//検索結果を返す
		return loginList;
	}

	//ユーザーデータを元にuserinfoの書籍データの更新
	public int updateForPassword(int login_id, String password) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		//SQL文
		String sql = "UPDATE login_info SET password = '" + password + "' WHERE user = '" + login_id + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;

public class UserDAO {

	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";

	private static final String URL = "jdbc:mariadb://localhost/mybookdb";

	private static final String USER = "root";

	private static final String PASSWD = "root123";

	// データベース接続
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

	// 特定ユーザーidのユーザー情報取得
	public User selectByUser(String userid) {

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "'";

			ResultSet rs = smt.executeQuery(sql);

			// Userオブジェクトに格納
			while (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));

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
		return user;
	}

	// 特定ユーザーid、パスワードのユーザー情報取得
	public User selectByUser(String userid, String password) {

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQl文発行
			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "' AND password='" + password + "'";

			ResultSet rs = smt.executeQuery(sql);

			// Userオブジェクトに格納
			while (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));

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
		return user;
	}

	public ArrayList<User> selectAll() {

		Connection con = null;
		Statement smt = null;
		ArrayList<User> userList = new ArrayList<User>();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT * FROM userinfo";
			ResultSet rs = smt.executeQuery(sql);

			// リストに書籍情報を格納
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				userList.add(user);
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
		return userList;
	}

	public int insert(User user) {

		Connection con = null;
		Statement smt = null;

		int count = 0;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "INSERT INTO userinfo VALUES('" + user.getUserid() + "','" + user.getPassword() + "','"
					+ user.getEmail() + "','" + user.getAuthority() + "')";

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

	public int delete(String userid) {

		Connection con = null;
		Statement smt = null;

		int count = 0;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "DELETE FROM userinfo WHERE user = '" + userid + "'";
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

	public int update(User user) {

		Connection con = null;
		Statement smt = null;

		int count = 0;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "UPDATE userinfo SET password='" + user.getPassword() + "',email='" + user.getEmail()
					+ "',authority='" + user.getAuthority() + "' WHERE user='" + user.getUserid() + "'";
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

	public ArrayList<User> search(String userid) {

		Connection con = null;
		Statement smt = null;

		ArrayList<User> list = new ArrayList<User>();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT * FROM userinfo WHERE user LIKE '%" + userid + "%'";

			ResultSet rs = smt.executeQuery(sql);

			// リストに書籍データ登録
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				list.add(user);
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
		return list;
	}

	public int updateForPassword(String userid, String password) {

		Connection con = null;
		Statement smt = null;

		int count = 0;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "UPDATE userinfo SET password = '" + password + "' WHERE user = '" + userid + "'";
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

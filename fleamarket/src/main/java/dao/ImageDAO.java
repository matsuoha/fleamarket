package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ImageDAO {

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

	// データ登録
	public void insert(String image, int product_id) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "INSERT INTO photo_info(product_id,image_url,photo_update) VALUES('" + product_id + "','"
					+ image + "' CURDATE())";

			smt.executeUpdate(sql);

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
	}

	// データ削除
	public void delete(int product_id) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "DELETE FROM photo_info WHERE product_id = '" + product_id + "'";
			smt.executeUpdate(sql);

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
	}

	// データ更新
	public void update(int product_id, String image) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "UPDATE photo_info SET " + "image_url='" + image + "',"
					+ "photo_update CURDATE() WHERE product_id='" + product_id + "'";

			smt.executeUpdate(sql);

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
	}
}
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderedItem;
import bean.User;

public class OrderedItemDAO {

	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";

	static final String URL = "jdbc:mariadb://localhost/mybookdb";

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

	// すべてのデータ取得
	public ArrayList<OrderedItem> selectAll() {

		Connection con = null;
		Statement smt = null;
		ArrayList<OrderedItem> list = new ArrayList<OrderedItem>();

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT o.user,b.title,o.date,o.quantity FROM bookinfo b INNER JOIN orderinfo o ON b.isbn=o.isbn";
			ResultSet rs = smt.executeQuery(sql);

			// 配列に格納
			while (rs.next()) {
				OrderedItem ordered = new OrderedItem();
				ordered.setUserid(rs.getString("user"));
				ordered.setTitle(rs.getString("title"));
				ordered.setQuantity(rs.getInt("quantity"));
				ordered.setDate(rs.getString("date"));
				list.add(ordered);
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

	public ArrayList<OrderedItem> selectByUser(String userid) {

		Connection con = null;
		Statement smt = null;

		ArrayList<OrderedItem> list = new ArrayList<OrderedItem>();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT o.user , b.title , o.quantity, o.date FROM bookinfo b, orderinfo o"
					+ " WHERE b.isbn = o.isbn and o.user = '" + userid + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				OrderedItem ordered = new OrderedItem();
				ordered.setUserid(rs.getString("user"));
				ordered.setTitle(rs.getString("title"));
				ordered.setQuantity(rs.getInt("quantity"));
				ordered.setDate(rs.getString("date"));
				list.add(ordered);
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
}

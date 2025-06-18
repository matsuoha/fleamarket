package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Sale;

public class SaleDAO {

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

	public ArrayList<Sale> selectBySales(String year, String month) {

		Connection con = null;
		Statement smt = null;

		ArrayList<Sale> list = new ArrayList<Sale>();

		if (month != null) {
			if (month.length() == 1) {
				month = "0" + month;
			}
		}

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT b.isbn, title, price, sum(quantity) as quantity FROM orderinfo o inner join bookinfo b ON o.isbn=b.isbn "
					+ " WHERE date LIKE '" + year + "-" + month + "%' GROUP BY b.isbn ORDER BY b.isbn";
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Sale sale = new Sale();
				sale.setIsbn(rs.getString("isbn"));
				sale.setTitle(rs.getString("title"));
				sale.setPrice(rs.getInt("price"));
				sale.setQuantity(rs.getInt("quantity"));
				list.add(sale);
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
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Book;

public class BookDAO {

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

	// すべてのデータを取得
	public ArrayList<Book> selectAll() {

		Connection con = null;
		Statement smt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT * FROM bookinfo ORDER BY isbn";
			ResultSet rs = smt.executeQuery(sql);

			// リストに書籍情報を格納
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
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
		return bookList;
	}

	// データ登録
	public void insert(Book book) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "INSERT INTO bookinfo VALUES('" + book.getIsbn() + "','" + book.getTitle() + "',"
					+ book.getPrice() + ")";

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

	// 特定ISBNの書籍データ取得
	public Book selectByIsbn(String isbn) {

		Connection con = null;
		Statement smt = null;

		Book book = new Book();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT isbn,title,price FROM bookinfo WHERE isbn = '" + isbn + "'";

			ResultSet rs = smt.executeQuery(sql);

			// Bookオブジェクトに格納
			while (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
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
		return book;
	}

	// データ削除
	public void delete(String isbn) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn + "'";
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
	public void update(Book book) {

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "UPDATE bookinfo SET " + "title='" + book.getTitle() + "'," + "price=" + book.getPrice()
					+ " WHERE isbn='" + book.getIsbn() + "'";

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

	// データ検索
	public ArrayList<Book> search(String isbn, String title, String price) {

		Connection con = null;
		Statement smt = null;

		ArrayList<Book> list = new ArrayList<Book>();

		try {

			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			String sql = "SELECT isbn,title,price FROM bookinfo " + "WHERE isbn LIKE '%" + isbn + "%' AND title LIKE '%"
					+ title + "%' AND price LIKE '%" + price + "%'";

			ResultSet rs = smt.executeQuery(sql);

			// リストに書籍データ登録
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				list.add(book);
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

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Purchase;

public class PurchaseDAO {
	
	//接続用の情報をフィールドに定数として定義（仮）
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/mybookdb";
	private static String USER = "root";
	private static String PASSWD = "root123";
	
	/*データベース接続を行うメソッド
	 *上記のデータベース接続用定義を基にデータベースへ接続し、
	 *戻り値としてコネクション情報を返す
	 */
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	//データベースから購入した書籍情報の検索を行うメソッド
	//戻り値としてArrayList<OrderedItem>型の変数を利用
	public ArrayList<Purchase> selectAll() {
		//変数宣言
		Connection con = null;
		Statement smt = null;
		
		//return用ArrayList配列のオブジェクトの生成
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		
		//SQL文
		String sql = "SELECT product_info.product_name,purchase_info.quantity,product_info.value,"
				+ "purchase_info.registration_date,user_info.nickname"
				+ "purchase_info.payment_flag FROM user_info "
				+ "JOIN product_info ON user_info.user_id = product_info.user_id"
				+ "JOIN purchase_info ON user_info.user_id = purchase_info.purchaser_id";
		
		try {
			con = getConnection();
			smt = con.createStatement();
			
			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);
			
			//結果セットから購入した書籍情報を取り出し配列(Bookオブジェクト)に格納
			while (rs.next()) {
				Purchase item = new Purchase();
				item.setProduct(rs.getString("product"));
				item.setQuantity(rs.getInt("quantity"));
				item.setValue(rs.getInt("value"));
				item.setRegistration_date(rs.getString("registration_date"));
				item.setNickname(rs.getString("nickname"));
				list.add(item);
			}
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {smt.close();} catch (SQLException ignore) {}
			}
			if (con != null) {
				try {con.close();} catch (SQLException ignore) {}
			}
		}
		return list;
	}

}

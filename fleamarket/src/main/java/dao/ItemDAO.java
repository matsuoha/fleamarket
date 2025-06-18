package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Product;

//商品情報のDAO
public class ItemDAO {
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

	public void insert(Product product) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		int count = 0;
		// SQL文の発行
		String sql = "INSERT INTO product_info VALUES('" + 
		 product.getProduct_id() + "','" + product.getUser_id() + "',"
		 + product.getProduct_name() + "','" + product.getCategory_id()
		 + "','" + product.getExplanation() + "','" + product.getSituation()
		 + "','" + product.getDelivery_time() + "','" + product.getQuantity_stock()
		 + "','" + product.getValue() + "','" + product.getRemarks_column()
		 + "','" + product.getShipping_method() + "','" + 
		 product.getProduct_registration() + "','" + 
		 product.getProduct_update() + ")";
		
		try {
			// 変数宣言
			con = getConnection();
			smt = con.createStatement();

			// SQLをDBへ発行
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
	}

	public ArrayList<Product> selectAll() {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		ArrayList<Product> productList = new ArrayList<Product>();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info ORDER BY product_id";
			// 取り出した情報をrsに代入
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				Product product = new Product();
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

				
				// productListにproductを追加する
				productList.add(product);
			}
			// 例外処理
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
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return productList;
	}
	public Product selectByProduct(String product_name) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		Product product = new Product();
		try {
			// DBに接続
			con = ItemDAO.getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info WHERE product_name = '" + product_name + "'";
			ResultSet rs = smt.executeQuery(sql);
			
			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

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
		return product;
	}
	public void delete(String product_id) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		// productクラスのオブジェクト化
		Product product = new Product();

		try {
			// DBに接続
			con = ItemDAO.getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "DELETE FROM product_info WHERE product_id = '" + product_id + "'";
			smt.executeUpdate(sql);
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
	}

	public void update(Product product) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		int count = 0;
		// SQL文の発行
		String sql = "UPDATE product_info SET product_id='" + 
		 product.getProduct_id() + "',product_name=" + product.getProduct_name()
		 + "',category_id='" + product.getCategory_id() + "',explanation=" 
		 + product.getExplanation() + "',situation='" + product.getSituation() + 
		 "',delivery_time=" + product.getDelivery_time() + "',quantity_stock=" 
		 + product.getQuantity_stock() + "',value='" + product.getValue() + 
		 "',remarks_column=" + product.getRemarks_column() + "',shipping_method=" 
		 + product.getShipping_method() + "',product_registration='" + product.getProduct_registration()
		 + "',product_update=" + product.getProduct_registration() + 
		 " WHERE user_id='" + product.getUser_id() + "'";

		try {
			// 変数宣言
			con = getConnection();
			smt = con.createStatement();

			// SQLをDBへ発行
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
	}

	public ArrayList<Product> search(String product_name) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		
		// オブジェクト化
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info " + "WHERE product_name LIKE '%" + product_name + "%'";
			// 取り出した情報をrsに代入
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				Product product = new Product();
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

				
				// productListにproductを追加する
				productList.add(product);
			}
			// 例外処理
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
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return productList;
	}
	//価格昇順表示メソッド(上から安い順)
	public ArrayList<Product> descendingOrder(){
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		
		// オブジェクト化
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info ORDER BY value";
			// 取り出した情報をrsに代入
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				Product product = new Product();
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

				
				// productListにproductを追加する
				productList.add(product);
			}
			// 例外処理
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
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return productList;
	}
	//価格降順表示メソッド(上から高い順)
	public ArrayList<Product> ascendingOrder(){
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		
		// オブジェクト化
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info ORDER BY value DESC";
			// 取り出した情報をrsに代入
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				Product product = new Product();
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

				
				// productListにproductを追加する
				productList.add(product);
			}
			// 例外処理
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
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return productList;
	}
	//更新日時昇順表示メソッド(上から古い順)
	public ArrayList<Product> oldUpdate(){
		// 変数宣言
		Connection con = null;
		Statement smt = null;
		
		// オブジェクト化
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();
			// SQL文の発行
			String sql = "SELECT * FROM product_info ORDER BY product_update";
			// 取り出した情報をrsに代入
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				// オブジェクト化(クラス・変数名は仮)
				Product product = new Product();
				
				// 以降、各要素のセッターメソッドを呼び出し代入
				// 商品ID
				product.setProduct_id(rs.getInt("product_id"));
				// ユーザーID
				product.setUser_id(rs.getInt("user_id"));
				// 商品名
				product.setProduct_name(rs.getString("product_name"));
				// カテゴリID
				product.setCategory_id(rs.getInt("category_id"));
				// 商品の説明
				product.setExplanation(rs.getString("explanation"));
				// 商品の状態
				product.setSituation(rs.getInt("situation"));
				// 発送までの日数
				product.setDelivery_time(rs.getInt("delivery_time"));
				// 個数
				product.setQuantity_stock(rs.getInt("quantity_stock"));
				// 価格
				product.setValue(rs.getInt("value"));
				// 備考欄
				product.setRemarks_column(rs.getString("remarks_column"));
				// 配送方法
				product.setShipping_method(rs.getString("shipping_method"));
				// 登録日時
				product.setProduct_registration(rs.getString("product_registration"));
				// 更新日時
				product.setProduct_update(rs.getString("product_update"));

				
				// productListにproductを追加する
				productList.add(product);
			}
			// 例外処理
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
					// 例外処理
				} catch (SQLException ignore) {
				}
			}
		}
		return productList;
	}
	//更新日時降順表示メソッド(上から新しい順)
		public ArrayList<Product> newUpdate(){
			// 変数宣言
			Connection con = null;
			Statement smt = null;
			
			// オブジェクト化
			ArrayList<Product> productList = new ArrayList<Product>();
			try {
				// DBに接続
				con = getConnection();
				smt = con.createStatement();
				// SQL文の発行
				String sql = "SELECT * FROM product_info ORDER BY product_update DESC";
				// 取り出した情報をrsに代入
				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					// オブジェクト化(クラス・変数名は仮)
					Product product = new Product();
					
					// 以降、各要素のセッターメソッドを呼び出し代入
					// 商品ID
					product.setProduct_id(rs.getInt("product_id"));
					// ユーザーID
					product.setUser_id(rs.getInt("user_id"));
					// 商品名
					product.setProduct_name(rs.getString("product_name"));
					// カテゴリID
					product.setCategory_id(rs.getInt("category_id"));
					// 商品の説明
					product.setExplanation(rs.getString("explanation"));
					// 商品の状態
					product.setSituation(rs.getInt("situation"));
					// 発送までの日数
					product.setDelivery_time(rs.getInt("delivery_time"));
					// 個数
					product.setQuantity_stock(rs.getInt("quantity_stock"));
					// 価格
					product.setValue(rs.getInt("value"));
					// 備考欄
					product.setRemarks_column(rs.getString("remarks_column"));
					// 配送方法
					product.setShipping_method(rs.getString("shipping_method"));
					// 登録日時
					product.setProduct_registration(rs.getString("product_registration"));
					// 更新日時
					product.setProduct_update(rs.getString("product_update"));

					
					// productListにproductを追加する
					productList.add(product);
				}
				// 例外処理
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
						// 例外処理
					} catch (SQLException ignore) {
					}
				}
			}
			return productList;
		}
}



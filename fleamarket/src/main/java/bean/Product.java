/**
 * 製品情報DTO
 */

package bean;

public class Product {

	private int product_id; // 商品ID
	private int user_id; // ユーザーID
	private String product_name; // 商品名
	private int category_id; // カテゴリーID
	private String explanation; // 商品の説明
	private int situation; // 商品の状態
	private int delivery_time; // 発送までの日数
	private int quantity_stock; // 個数
	private int value; // 価格
	private String remarks_column; // 備考欄
	private String shipping_method; // 配送方法
	private String product_registration; // 登録日数
	private String product_update; // 更新日時

	/**
	 * コンストラクタ
	 */
	public Product() {
		this.product_id = 0;
		this.user_id = 0;
		this.product_name = null;
		this.category_id = 0;
		this.explanation = null;
		this.situation = 0;
		this.delivery_time = 0;
		this.quantity_stock = 0;
		this.value = 0;
		this.remarks_column = null;
		this.shipping_method = null;
		this.product_registration = null;
		this.product_update = null;
	}

	// ゲッターとセッター

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}

	public int getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(int delivery_time) {
		this.delivery_time = delivery_time;
	}

	public int getQuantity_stock() {
		return quantity_stock;
	}

	public void setQuantity_stock(int quantity_stock) {
		this.quantity_stock = quantity_stock;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getRemarks_column() {
		return remarks_column;
	}

	public void setRemarks_column(String remarks_column) {
		this.remarks_column = remarks_column;
	}

	public String getShipping_method() {
		return shipping_method;
	}

	public void setShipping_method(String shipping_method) {
		this.shipping_method = shipping_method;
	}

	public String getProduct_registration() {
		return product_registration;
	}

	public void setProduct_registration(String product_registration) {
		this.product_registration = product_registration;
	}

	public String getProduct_update() {
		return product_update;
	}

	public void setProduct_update(String product_update) {
		this.product_update = product_update;
	}

}

/*
 * 購入の情報をもつDTO
 */
package bean;

public class Purchase {

	//購入IDを格納する変数
	private int purchase_id;
	//購入者ユーザーIDを格納する変数
	private int purchaser_id;
	//出品者ユーザーIDを格納する変数
	private int seller_id;
	//商品IDを格納する変数
	private int product_id;
	//小計を格納する変数
	private int subtotal;
	//購入数を格納する変数
	private int quantity;
	//購入日時を格納する変数
	private String registration_date;
	//更新日を格納する変数
	private String update_date;
	//取引状態フラグ(0.購入 1.発送準備中 2.発送済み 3.完了)を格納する変数
	private int payment_flag;
	//出品者入金フラグ(0.未入金 1.入金済み)
	private int seller_payment_flag;
	//支払い日を格納する変数
	private String pay_day;

	//コンストラクタ
	public Purchase() {
		this.purchase_id = 0;
		this.purchaser_id = 0;
		this.seller_id = 0;
		this.product_id = 0;
		this.subtotal = 0;
		this.quantity = 0;
		this.registration_date = null;
		this.update_date = null;
		this.payment_flag = 0;
		this.seller_payment_flag = 0;
		this.pay_day = null;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getPurchaser_id() {
		return purchaser_id;
	}
	public void setPurchaser_id(int purchaser_id) {
		this.purchaser_id = purchaser_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public int getPayment_flag() {
		return payment_flag;
	}
	public void setPayment_flag(int payment_flag) {
		this.payment_flag = payment_flag;
	}
	public int getSeller_payment_flag() {
		return seller_payment_flag;
	}
	public void setSeller_payment_flag(int seller_payment_flag) {
		this.seller_payment_flag = seller_payment_flag;
	}
	public String getPay_day() {
		return pay_day;
	}
	public void setPay_day(String pay_day) {
		this.pay_day = pay_day;
	}
}
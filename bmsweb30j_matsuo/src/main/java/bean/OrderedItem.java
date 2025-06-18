package bean;

public class OrderedItem {

	// ユーザーid
	private String userid;

	// タイトル
	private String title;

	private int quantity;

	// 日付
	private String date;

	// コンストラクタ
	public OrderedItem() {
		this.userid = null;
		this.title = null;
		this.date = null;
	}

	// アクセサメソッド
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
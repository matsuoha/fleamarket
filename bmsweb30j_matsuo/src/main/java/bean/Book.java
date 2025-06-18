package bean;

public class Book {

	// ISBN
	private String isbn;

	// タイトル
	private String title;

	// 価格
	private int price;

	// コンストラクタ
	public Book() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
	}

	// アクセサメソッド
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

package bean;

public class Sale {
	private String isbn;
	private String title;
	private int price;
	private int quantity;

	public Sale() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
		this.quantity = 0;
	}
	
	public Sale(Book book,int quantity) {
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.quantity = quantity;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
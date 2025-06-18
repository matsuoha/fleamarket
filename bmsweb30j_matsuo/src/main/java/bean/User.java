package bean;

public class User {

	// ユーザーid
	private String userid;

	// パスワード
	private String password;

	// メールアドレス
	private String email;

	// 権限
	private String authority;

	// コンストラクタ
	public User() {
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}

	// アクセサメソッド
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUserid() {
		return userid;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getAuthority() {
		return authority;
	}

}
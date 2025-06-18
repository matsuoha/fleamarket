/**
 * ログイン情報DTO
 */

package bean;

public class Login {

	private int loginId;
	private String password;
	private int authority;

	/**
	 * コンストラクタ
	 */
	public Login() {

		this.loginId = 0;
		this.password = null;
		this.authority = 0;

	}

	//ゲッターとセッター
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

}

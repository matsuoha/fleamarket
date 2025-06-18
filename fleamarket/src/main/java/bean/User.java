/**
 * ユーザー情報DTO
 */

package bean;

public class User {

	private int user_id; // ユーザーID
	private int login_id; // ログインID

	private String last_name; // 姓
	private String first_name; // 名
	private String last_name_kana; // 姓（かな）
	private String first_name_kana; // 名（かな）

	private String nickname; // ニックネーム

	// 住所
	private String prefecture; // 都道府県
	private String municipalities; // 市区町村
	private String address; // その他番地など
	private String post_code; // 郵便番号
	private String mail; // メールアドレス

	private int sex; // 性別
	private String birth; // 生年月日
	private int age; // 年齢

	private String bank_name; // 金融機関名
	private String bank_num; // 支店番号
	private String account_num; // 口座番号
	private String account_name; // 口座名義

	private String user_registration; // 登録日時
	private String user_update; // 更新日時

	private int display_flag; // 表示フラグ(0.表示1.非表示)
	private int frozen; // 凍結(0.しない1.する)

	/**
	 * コンストラクタ
	 */

	public User() {
		this.user_id = 0;
		this.login_id = 0;
		this.last_name = null;
		this.first_name = null;
		this.last_name_kana = null;
		this.first_name_kana = null;

		this.nickname = null;

		this.prefecture = null;
		this.municipalities = null;
		this.address = null;
		this.post_code = null;
		this.mail = null;

		this.sex = 0;
		this.birth = null;
		this.age = 0;

		this.bank_name = null;
		this.bank_num = null;
		this.account_num = null;
		this.account_name = null;

		this.user_registration = null;
		this.user_update = null;

		this.display_flag = 0;
		this.frozen = 0;
	}

	// ゲッターとセッター
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name_kana() {
		return last_name_kana;
	}

	public void setLast_name_kana(String last_name_kana) {
		this.last_name_kana = last_name_kana;
	}

	public String getFirst_name_kana() {
		return first_name_kana;
	}

	public void setFirst_name_kana(String first_name_kana) {
		this.first_name_kana = first_name_kana;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(String municipalities) {
		this.municipalities = municipalities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public String getAccount_num() {
		return account_num;
	}

	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getUser_registration() {
		return user_registration;
	}

	public void setUser_registration(String user_registration) {
		this.user_registration = user_registration;
	}

	public String getUser_update() {
		return user_update;
	}

	public void setUser_update(String user_update) {
		this.user_update = user_update;
	}

	public int getDisplay_flag() {
		return display_flag;
	}

	public void setDisplay_flag(int display_flag) {
		this.display_flag = display_flag;
	}

	public int getFrozen() {
		return frozen;
	}

	public void setFrozen(int frozen) {
		this.frozen = frozen;
	}

}

package jp.co.sss.shop.dto;

/**
 * DB登録・変更時の会員情報
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class User {
	/**
	 * 会員ID
	 */
	private Integer id;
	/**
	 * メールアドレス
	 */
	private String email;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * 会員名
	 */
	private String name;
	/**
	 * 郵便番号
	 */
	private String postalCode;
	/**
	 * 住所
	 */
	private String address;
	/**
	 * 電話番号
	 */
	private String phoneNumber;
	/**
	 * 権限(値)
	 */
	private Integer authority;

	/**
	 * 会員IDの取得
	 * 
	 * @return 会員ID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 会員IDのセット
	 * 
	 * @param id 会員ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * メールアドレスの取得
	 * 
	 * @return メールアドレス
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * メールアドレスのセット
	 * 
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * パスワードの取得
	 * 
	 * @return パスワードの
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * パスワードのセット
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 会員氏名の取得
	 * 
	 * @return 会員氏名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 会員氏名のセット
	 * 
	 * @param name 会員氏名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 郵便番号の取得
	 * 
	 * @return 郵便番号
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * 郵便番号のセット
	 * 
	 * @param postalCode 郵便番号
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * 住所の取得
	 * 
	 * @return 住所
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 住所のセット
	 * 
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号の取得
	 * 
	 * @return 電話番号
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 電話番号のセット
	 * 
	 * @param phoneNumber 電話番号
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 権限(値)の取得
	 * 
	 * @return 権限(値)
	 */
	public Integer getAuthority() {
		return this.authority;
	}

	/**
	 * 権限(値)のセット
	 * 
	 * @param authority 権限(値)
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

}

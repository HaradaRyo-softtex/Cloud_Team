package jp.co.sss.shop.form;

import java.io.Serializable;

/**
 * パラメタで取得した会員情報を格納するクラス
 *
 * @author System Shared.co.,Ltd.
 */
public class UserForm implements Serializable {
	/**
	 * 会員ID
	 */
	private String id;
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
	 * 権限(文字列)
	 */
	private String authority;
	/**
	 * 権限(表示文字列)
	 */
	private String authorityStr;
	
	/**
	 * パスワード(確認)
	 */
	private String newpassword;
	
	
	

	/**
	 * 会員ID(文字列)の取得
	 * 
	 * @return 会員ID(文字列)
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 会員ID(文字列)のセット
	 * 
	 * @param id 会員ID(文字列)
	 */
	public void setId(String id) {
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
	 * @return パスワード
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
	 * 権限の数値表現(文字列)の取得
	 * 
	 * @return 権限の数値表現(文字列)
	 */
	public String getAuthority() {
		return this.authority;
	}

	/**
	 * 権限の数値表現(文字列)のセット
	 * 
	 * @param authority 権限の数値表現(文字列)
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * 権限の文字列表現の取得
	 * 
	 * @return 権限の文字列表現
	 */
	public String getAuthorityStr() {
		return this.authorityStr;
	}

	/**
	 * 権限の文字列表現のセット
	 * 
	 * @param authorityStr 権限の文字列表現
	 */
	public void setAuthorityStr(String authorityStr) {
		this.authorityStr = authorityStr;
	}

	/** 権限の文字列表現のセット
	
	 * @param authorityStr 権限の文字列表現
	 * (newpassword)*/
	
	public void setNewPassword(String newpassword) {
		this.newpassword = newpassword;
		
	}
	
	public String getNewPassword() {
		return this.newpassword;
		
	    }
	}




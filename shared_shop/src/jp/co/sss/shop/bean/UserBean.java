package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 会員情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class UserBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	/**
	 * 会員ID
	 */
	private Integer id;
	/**
	 * 会員名
	 */
	private String name;
	/**
	 * メールアドレス
	 */
	private String email;
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

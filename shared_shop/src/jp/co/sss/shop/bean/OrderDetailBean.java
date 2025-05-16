package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 注文情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class OrderDetailBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 注文ID
	 */
	private Integer id;

	/**
	 * 会員氏名
	 */
	private String userName;

	/**
	 * 注文日付
	 */
	private String insertDate;

	/**
	 * 支払方法
	 */
	private Integer payMethod;
	/**
	 * 支払方法(文字列)
	 */
	private String payMethodStr;
	/**
	 * 合計金額
	 */
	private Integer total;

	/**
	 * お届け先郵便番号
	 */
	private String postalCode;

	/**
	 * お届け先住所
	 */
	private String address;

	/**
	 * お届け先名
	 */
	private String name;

	/**
	 * お届け先電話番号
	 */
	private String phoneNumber;

	/**
	 * 注文IDの取得
	 * 
	 * @return 注文ID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 注文IDのセット
	 * 
	 * @param id 注文ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 注文日付の取得
	 * 
	 * @return 注文日付
	 */
	public String getInsertDate() {
		return this.insertDate;
	}

	/**
	 * 注文日付のセット
	 * 
	 * @param insertDate 注文日付
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * 会員氏名の取得
	 * 
	 * @return 会員氏名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 会員氏名のセット
	 * 
	 * @param userName 会員氏名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 支払方法(値)の取得
	 * 
	 * @return 支払方法(値)
	 */
	public Integer getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 支払方法(値)のセット
	 * 
	 * @param payMethod 支払方法(値)
	 */
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 支払方法(文字列)の取得
	 * 
	 * @return 支払方法(文字列)
	 */
	public String getPayMethodStr() {
		return this.payMethodStr;
	}

	/**
	 * 支払方法(文字列)のセット
	 * 
	 * @param payMethodStr 支払方法(文字列)
	 */
	public void setPayMethodStr(String payMethodStr) {
		this.payMethodStr = payMethodStr;
	}

	/**
	 * 合計金額の取得
	 * 
	 * @return 合計金額
	 */
	public Integer getTotal() {
		return this.total;
	}

	/**
	 * 合計金額のセット
	 * 
	 * @param total 合計金額
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * お届け先郵便番号の取得
	 * 
	 * @return お届け先郵便番号
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * お届け先郵便番号のセット
	 * 
	 * @param postalCode お届け先郵便番号
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * お届け先住所の取得
	 * 
	 * @return お届け先住所
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * お届け先住所のセット
	 * 
	 * @param address お届け先住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * お届け先氏名の取得
	 * 
	 * @return お届け先氏名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * お届け先氏名のセット
	 * 
	 * @param ｎame お届け先氏名
	 */
	public void setName(String ｎame) {
		this.name = ｎame;
	}

	/**
	 * お届け先電話番号の取得
	 * 
	 * @return お届け先電話番号
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * お届け先電話番号のセット
	 * 
	 * @param phoneNumber お届け先電話番号
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

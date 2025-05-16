package jp.co.sss.shop.form;

import java.io.Serializable;

/**
 * パラメタで取得した注文情報を格納するクラス
 *
 * @author System Shared.co.,Ltd.
 */
public class OrderForm implements Serializable {

	/**
	 * 支払方法
	 */
	private String payMethod;
	/**
	 * 支払方法(文字列)
	 */
	private String payMethodStr;
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
	 * 支払方法-数値表現(文字列)の取得
	 * 
	 * @return 支払方法-数値表現(文字列)
	 */
	public String getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 支払方法-数値表現(文字列)のセット
	 * 
	 * @param payMethod 支払方法-数値表現(文字列)
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 支払方法-文字列表現の取得
	 * 
	 * @return 支払方法-文字列表現(文字列)
	 */
	public String getPayMethodStr() {
		return this.payMethodStr;
	}

	/**
	 * 支払方法-文字列表現のセット
	 * 
	 * @param payMethodStr 支払方法-文字列表現
	 */
	public void setPayMethodStr(String payMethodStr) {
		this.payMethodStr = payMethodStr;
	}

	/**
	 * お届け郵便番号の取得
	 * 
	 * @return お届け郵便番号
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * お届け郵便番号のセット
	 * 
	 * @param postalCode お届け郵便番号
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * お届け住所の取得
	 * 
	 * @return お届け住所
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * お届け住所のセット
	 * 
	 * @param address お届け住所
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
	 * お届け電話番号の取得
	 * 
	 * @return お届け電話番号
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * お届け電話番号のセット
	 * 
	 * @param phoneNumber お届け先電話番号
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

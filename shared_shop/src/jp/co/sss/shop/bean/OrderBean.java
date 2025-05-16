package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 注文情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class OrderBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	/**
	 * 注文ID
	 */
	private Integer id;

	/**
	 * 注文日付
	 */
	private String insertDate;

	/**
	 * 支払方法
	 */
	private String payMethod;

	/**
	 * 合計金額
	 */
	private Integer total;

	/**
	 * 会員名
	 */
	private String userName;

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
	 * 支払方法の取得
	 * 
	 * @return 支払方法(文字列)
	 */
	public String getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 支払方法のセット
	 * 
	 * @param payMethod 支払方法(文字列)
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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

}

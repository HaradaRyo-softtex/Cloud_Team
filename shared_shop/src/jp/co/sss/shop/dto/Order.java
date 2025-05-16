package jp.co.sss.shop.dto;

/**
 * DB登録時の注文情報
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class Order {

	/**
	 * 注文ID
	 */
	private int id;
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
	 * 支払方法
	 */
	private int payMethod;
	/**
	 * 会員ID
	 */
	private int userId;

	/**
	 * 注文IDの取得
	 * 
	 * @return 注文ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 注文IDのセット
	 * 
	 * @param id 注文ID
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @param name お届け先氏名
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * 支払方法(値)を取得
	 * 
	 * @return 支払方法(値)
	 */
	public int getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 支払方法(値)のセット
	 * 
	 * @param payMethod 支払方法(値)
	 */
	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 会員IDの取得
	 * 
	 * @return 会員ID
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * 会員IDのセット
	 * 
	 * @param userId 会員ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}

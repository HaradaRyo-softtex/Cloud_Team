package jp.co.sss.shop.form;

import java.io.Serializable;

/**
 * パラメタで取得した商品情報を格納するクラス
 *
 * @author System Shared.co.,Ltd.
 */
public class ItemForm implements Serializable {

	/**
	 * 商品ID
	 */
	private String id;

	/**
	 * 商品名
	 */
	private String name;

	/**
	 * 価格
	 */
	private String price;

	/**
	 * 商品説明
	 */
	private String description;

	/**
	 * 在庫数
	 */
	private String stock;

	/**
	 * 商品画像ファイル名
	 */
	private String image;

	/**
	 * カテゴリID
	 */
	private String categoryId;

	/**
	 * カテゴリ名
	 */
	private String categoryName;

	/**
	 * 商品ID(文字列)の取得
	 * 
	 * @return 商品ID(文字列)
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 商品ID(文字列)のセット
	 * 
	 * @param id 商品ID(文字列)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 商品名の取得
	 * 
	 * @return 商品名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 商品名のセット
	 * 
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品価格(文字列)の取得
	 * 
	 * @return 商品価格(文字列)
	 */
	public String getPrice() {
		return this.price;
	}

	/**
	 * 商品価格(文字列)のセット
	 * 
	 * @param price 商品価格(文字列)
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * 商品説明文の取得
	 * 
	 * @return 商品説明文
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 商品説明文のセット
	 * 
	 * @param description 商品説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 在庫数(文字列)の取得
	 * 
	 * @return 商品在庫数(文字列)
	 */
	public String getStock() {
		return this.stock;
	}

	/**
	 * 商品在庫数(文字列)のセット
	 * 
	 * @param stock 商品在庫数(文字列)
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}

	/**
	 * 商品画像ファイル名の取得
	 * 
	 * @return 商品画像ファイル名
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * 商品画像ファイル名のセット
	 * 
	 * @param image 商品画像ファイル名
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * カテゴリID(文字列)の取得
	 * 
	 * @return カテゴリID(文字列)
	 */
	public String getCategoryId() {
		return this.categoryId;
	}

	/**
	 * カテゴリID(文字列)のセット
	 * 
	 * @param categoryId カテゴリID(文字列)
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * カテゴリ名の取得
	 * 
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * カテゴリ名のセット
	 * 
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 商品情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class ItemBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品ID
	 */
	private Integer id;

	/**
	 * 商品名
	 */
	private String name;

	/**
	 * 価格
	 */
	private Integer price;

	/**
	 * 商品画像ファイル名
	 */
	private String image;

	/**
	 * カテゴリID
	 */
	private Integer categoryId;

	/**
	 * カテゴリ名
	 */
	private String categoryName;

	/**
	 * 商品IDの取得
	 * 
	 * @return カテゴリID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 商品IDのセット
	 * 
	 * @param id 商品ID
	 */
	public void setId(Integer id) {
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
	 * 商品単価の取得
	 * 
	 * @return 商品単価
	 */
	public Integer getPrice() {
		return this.price;
	}

	/**
	 * 商品単価のセット
	 * 
	 * @param price 商品単価
	 */
	public void setPrice(Integer price) {
		this.price = price;
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
	 * カテゴリIDの取得
	 * 
	 * @return カテゴリID
	 */
	public Integer getCategoryId() {
		return this.categoryId;
	}

	/**
	 * カテゴリIDのセット
	 * 
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(Integer categoryId) {
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

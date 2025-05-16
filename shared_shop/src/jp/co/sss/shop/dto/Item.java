package jp.co.sss.shop.dto;

/**
 * DB登録・変更時の商品情報
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class Item {

	/**
	 * 商品ID
	 */
	private int id;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 商品単価
	 */
	private int price;
	/**
	 * 商品説明文
	 */
	private String description;
	/**
	 * 在庫数
	 */
	private int stock;
	/**
	 * 商品画像ファイル名
	 */
	private String image;
	/**
	 * カテゴリID
	 */
	private int categoryId;

	/**
	 * 商品IDを取得
	 * 
	 * @return 商品ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 商品IDをセット
	 * 
	 * @param id 商品ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 商品名を取得
	 * 
	 * @return 商品名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 商品名をセット
	 * 
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 商品単価を取得
	 * 
	 * @return 商品単価
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * 商品単価をセット
	 * 
	 * @param price 商品単価
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 商品説明文を取得
	 * 
	 * @return 商品説明文
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 商品説明文をセット
	 * 
	 * @param description 商品説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 在庫数を取得
	 * 
	 * @return 在庫数
	 */
	public int getStock() {
		return this.stock;
	}

	/**
	 * 在庫数を取得
	 * 
	 * @param stock 在庫数
	 */
	public void setStock(int stock) {
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
	 * カテゴリIDの取得
	 * 
	 * @return カテゴリID
	 */
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	 * カテゴリIDのセット
	 * 
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}

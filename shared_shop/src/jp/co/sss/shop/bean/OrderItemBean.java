package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 注文商品情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class OrderItemBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	/**
	 * 注文商品ID
	 */
	private Integer id;
	/**
	 * 注文商品名
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
	 * 注文個数
	 */
	private Integer orderNum;
	/**
	 * 小計
	 */
	private Integer subtotal;

	/**
	 * 注文商品IDの取得
	 * 
	 * @return 注文商品ID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 注文商品IDのセット
	 * 
	 * @param id 注文商品ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 注文商品名の取得
	 * 
	 * @return 注文商品名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 注文商品名のセット
	 * 
	 * @param name 注文商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 注文時点の商品単価の取得
	 * 
	 * @return 注文時点の商品単価
	 */
	public Integer getPrice() {
		return this.price;
	}

	/**
	 * 注文時点の商品単価のセット
	 * 
	 * @param price 注文時点の商品単価
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * 小計(注文個数×商品単価)の取得
	 * 
	 * @return 小計
	 */
	public Integer getSubtotal() {
		return this.subtotal;
	}

	/**
	 * 小計(注文個数×商品単価)のセット
	 * 
	 * @param subtotal 小計(注文個数×商品単価)
	 */
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * 注文個数の取得
	 * 
	 * @return 注文個数
	 */
	public Integer getOrderNum() {
		return this.orderNum;
	}

	/**
	 * 注文個数のセット
	 * 
	 * @param orderNum 注文個数
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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

}

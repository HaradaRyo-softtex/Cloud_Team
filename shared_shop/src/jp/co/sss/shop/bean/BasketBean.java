package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * 買い物かご内の商品情報クラス
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class BasketBean implements Serializable {
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
	 * 商品在庫数
	 */
	private Integer stock;

	/**
	 * 商品注文個数 初期値 1
	 */
	private Integer orderNum = 1;

	/**
	 * コンストラクタ
	 */
	public BasketBean() {
	}

	/**
	 * コンストラクタ
	 *
	 * @param id    商品ID
	 * @param name  商品名
	 * @param stock 商品在庫数
	 */
	public BasketBean(Integer id, String name, Integer stock) {
		this(id, name, stock, 1);
	}

	/**
	 * コンストラクタ
	 *
	 * @param id       商品ID
	 * @param name     商品名
	 * @param stock    商品在庫数
	 * @param orderNum 注文個数
	 */
	public BasketBean(Integer id, String name, Integer stock, Integer orderNum) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.orderNum = orderNum;
	}

	/**
	 * 買い物かごにある商品のIdを取得
	 * 
	 * @return 商品ＩＤ
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 買い物かごに入れる商品のIdをセット
	 * 
	 * @param id 商品ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 買い物かごにある商品の名前を取得
	 * 
	 * @return 商品名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 買い物かごに入れる商品の名前をセット
	 * 
	 * @param name 商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 買い物かごにある商品の在庫数を取得
	 * 
	 * @return 在庫数
	 */
	public Integer getStock() {
		return this.stock;
	}

	/**
	 * 買い物かごに入れる商品の在庫をセット
	 * 
	 * @param stock 在庫数
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 買い物かごに入れられている商品の個数を取得
	 * 
	 * @return 商品個数
	 */
	public Integer getOrderNum() {
		return this.orderNum;
	}

	/**
	 * 買い物かご内の商品の個数をセット
	 * 
	 * @param orderNum 商品個数
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}

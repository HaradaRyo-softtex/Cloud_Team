package jp.co.sss.shop.dto;

/**
 * DB登録時の注文商品情報
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class OrderItem {

	/**
	 * 注文商品ID
	 */
	private Integer id;
	/**
	 * 注文商品ID
	 */
	private Integer itemId;
	
	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 価格
	 */
	private Integer price;
	/**
	 * 注文個数
	 */
	private Integer quantity;

	/**
	 * 注文ID
	 */
	private Integer orderId;
	
	/**
	 * 小計
	 */
	private Integer sum;
	
	/**
	 * 合計
	 */
	private Integer total;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	/**
	 * 注文商品IDの取得
	 * 
	 * @return 注文商品ID
	 * 
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
	 * 注文個数の取得
	 * 
	 * @return 注文個数
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * 注文個数のセット
	 * 
	 * @param quantity 注文個数
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 注文IDの取得
	 * 
	 * @return 注文ID
	 */
	public Integer getOrderId() {
		return this.orderId;
	}

	/**
	 * 注文IDのセット
	 * 
	 * @param orderId 注文ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 商品IDの取得
	 * 
	 * @return 商品ID
	 */
	public Integer getItemId() {
		return this.itemId;
	}

	/**
	 * 商品IDのセット
	 * 
	 * @param itemId 商品ID
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * 注文時商品単価の取得
	 * 
	 * @return 注文時商品単価
	 */
	public Integer getPrice() {
		return this.price;
	}

	/**
	 * 注文時商品単価のセット
	 * 
	 * @param price 注文時商品単価
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}

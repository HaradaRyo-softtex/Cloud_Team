package jp.co.sss.shop.dto;

/**
 * DB登録・変更時のカテゴリ情報
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class Category {

	/** カテゴリID */
	private int id;
	/** カテゴリ名 */
	private String name;
	/** カテゴリ説明文 */
	private String description;

	/**
	 * カテゴリIDの取得
	 * 
	 * @return カテゴリID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * カテゴリIDのセット
	 * 
	 * @param id カテゴリID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * カテゴリ名の取得
	 * 
	 * @return カテゴリ名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * カテゴリ名のセット
	 * 
	 * @param name カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カテゴリ説明文の取得
	 * 
	 * @return カテゴリ説明文
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * カテゴリ説明文のセット
	 * 
	 * @param description カテゴリ説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}

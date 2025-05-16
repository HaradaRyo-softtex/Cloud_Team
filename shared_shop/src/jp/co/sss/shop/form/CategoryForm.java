package jp.co.sss.shop.form;

import java.io.Serializable;

/**
 * パラメタで取得したカテゴリ情報を格納するクラス
 *
 * @author System Shared.co.,Ltd.
 */
public class CategoryForm implements Serializable {

	/**
	 * カテゴリID
	 */
	private String id;
	/**
	 * カテゴリ名
	 */
	private String name;
	/**
	 * カテゴリ説明
	 */
	private String description;

	/**
	 * カテゴリIDを取得
	 * 
	 * @return カテゴリID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * カテゴリIDをセット
	 * 
	 * @param id カテゴリID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 入力されたカテゴリ名を取得
	 * 
	 * @return カテゴリ名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 入力されたカテゴリ名をセット
	 * 
	 * @param name カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 入力されたカテゴリ詳細説明を取得
	 * 
	 * @return カテゴリ詳細説明
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 入力されたカテゴリ詳細説明をセット
	 * 
	 * @param description カテゴリ詳細説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}

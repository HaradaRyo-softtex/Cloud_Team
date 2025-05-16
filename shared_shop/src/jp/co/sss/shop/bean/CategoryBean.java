package jp.co.sss.shop.bean;

import java.io.Serializable;

/**
 * カテゴリ情報クラス
 *
 * @author System Shared.co.,Ltd.
 */
public class CategoryBean implements Serializable {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	/**
	 * カテゴリID
	 */
	private Integer id;
	/**
	 * カテゴリ名
	 */
	private String name;
	/**
	 * カテゴリ説明
	 */
	private String description;

	/**
	 * カテゴリIDの取得
	 * 
	 * @return カテゴリID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * カテゴリIDのセット
	 * 
	 * @param id カテゴリID
	 */
	public void setId(Integer id) {
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
	 * カテゴリ説明の取得
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

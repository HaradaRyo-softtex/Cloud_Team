/**
 * 
 */
package jp.co.sss.shop.validator;

import java.sql.SQLException;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.dao.CategoryDao;

/**
 * IDの入力チェックに関する処理をまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */

public class IdValid {

	/**
	 * 検索処理用カテゴリIDの値チェック
	 * 
	 * @param categoryIdStr パラメタから取得したカテゴリID
	 * @return True(カテゴリIDが正常)、False(カテゴリIDなしor異常）
	 * @throws SQLException           SQL関連の例外
	 * @throws ClassNotFoundException データベース接続時の例外
	 * 
	 */
	public static boolean checkCategoryIdForSearch(String categoryIdStr) throws ClassNotFoundException, SQLException {
		boolean retflg = true;

		if (CommonValid.isEmpty(categoryIdStr)) {
			// カテゴリIDが空
			retflg = false;
		} else if (CommonValid.isNumber(categoryIdStr)) {
			// カテゴリIDが数値以外
			retflg = false;
		} else if (categoryIdStr.equals(Constant.CATEGORY_SELECT_NONE_NO)) {
			retflg = false;
		} else if (Constant.CATEGORY_RECORD_MAX < Integer.parseInt(categoryIdStr)) {
			// カテゴリIDの最大値を超えている
			retflg = false;
		} else if (CategoryDao.findOneByCategoryId(categoryIdStr) == null) {
			retflg = false;
		}
		return retflg;
	}

	/**
	 * カテゴリIDの値が正しくないかをチェック
	 * 
	 * @param categoryIdStr パラメタから取得したカテゴリID
	 * @return True(カテゴリIDが正常)、False(カテゴリIDなしor異常）
	 * 
	 */
	public static boolean isNOTCorrectCategoryId(String categoryIdStr) {
		return !(isCorrectCategoryId(categoryIdStr));
	}

	/**
	 * カテゴリIDの値が正しいかをチェック
	 * 
	 * @param categoryIdStr パラメタから取得したカテゴリID
	 * @return True(カテゴリIDが正常)、False(カテゴリIDなしor異常）
	 * 
	 */
	public static boolean isCorrectCategoryId(String categoryIdStr) {
		boolean retflg = true;

		if (CommonValid.isEmpty(categoryIdStr)) {
			// カテゴリIDが空
			retflg = false;
		} else if (CommonValid.isNumber(categoryIdStr)) {
			// カテゴリIDが数値以外
			retflg = false;
		} else if (categoryIdStr.equals(Constant.CATEGORY_SELECT_NONE_NO)) {
			retflg = false;
		} else if (Constant.CATEGORY_RECORD_MAX < Integer.parseInt(categoryIdStr)) {
			// カテゴリIDの最大値を超えている
			retflg = false;
		}
		return retflg;
	}

	/**
	 * 会員IDの形式が正しくないかをチェック
	 * 
	 * @param userIdStr パラメタから取得した会員ID
	 * @return True(会員IDが異常)、False(会員IDが正常）
	 * 
	 */
	public static boolean isNOTCorrectUserId(String userIdStr) {
		return !(isCorrectUserId(userIdStr));
	}

	/**
	 * 会員IDの形式が正しいかをチェック
	 * 
	 * @param userIdStr パラメタから取得した会員ID
	 * @return True(会員IDが正常)、False(会員IDなしor異常）
	 */
	public static boolean isCorrectUserId(String userIdStr) {
		boolean retflg = true;

		if (CommonValid.isEmpty(userIdStr)) {
			// 会員IDが空
			retflg = false;
		} else if (CommonValid.isNumber(userIdStr)) {
			// 会員IDが数値以外
			retflg = false;
		} else if (Constant.USER_RECORD_MAX < Integer.parseInt(userIdStr)) {
			// 会員IDの最大値を超えている
			retflg = false;
		}
		return retflg;
	}

	/**
	 * 注文IDの形式が正しくないかのチェック
	 * 
	 * @param orderIdStr パラメタから取得した注文ID
	 * @return True(注文ID異常)、False(注文ID正常）
	 */
	public static boolean isNOTCorrectOrderId(String orderIdStr) {
		return !(isCorrectOrderId(orderIdStr));
	}

	/**
	 * 注文IDの形式が正しいかのチェック
	 * 
	 * @param orderIdStr パラメタから取得した注文ID
	 * @return True(注文IDが正常)、False(注文IDなしor異常）
	 */
	public static boolean isCorrectOrderId(String orderIdStr) {
		boolean retflg = true;

		if (CommonValid.isEmpty(orderIdStr)) {
			// 注文IDが空
			retflg = false;
		} else if (CommonValid.isNumber(orderIdStr)) {
			// 注文IDが数値以外
			retflg = false;
		}
		return retflg;
	}

	/**
	 * 商品IDの形式が正しくないかのチェック
	 * 
	 * @param itemIdStr パラメタから取得した商品ID
	 * @return True(商品ID異常)、False(商品ID正常）
	 */
	public static boolean isNOTCorrectItemId(String itemIdStr) {
		return !(isCorrectItemId(itemIdStr));
	}

	/**
	 * 商品IDの形式が正しいかのチェック
	 * 
	 * @param itemIdStr パラメタから取得した商品ID
	 * @return True(商品IDが正常)、False(商品IDなしor異常）
	 */
	public static boolean isCorrectItemId(String itemIdStr) {
		boolean retflg = true;

		if (CommonValid.isEmpty(itemIdStr)) {
			// 商品IDが空
			retflg = false;
		} else if (CommonValid.isNumber(itemIdStr)) {
			// 商品IDが数値以外
			retflg = false;
		} else if (Constant.ITEM_RECORD_MAX < Integer.parseInt(itemIdStr)) {
			// 会員IDの最大値を超えている
			retflg = false;
		}

		return retflg;
	}
}

/**
 *
 */
package jp.co.sss.shop.validator;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.form.CategoryForm;

/**
 * カテゴリ情報の入力チェック
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class CategoryInputValid {

	/**
	 * カテゴリ登録・カテゴリ変更画面に表示するエラーメッセージのリストを作成するメソッド
	 *
	 * @param categoryForm カテゴリ情報
	 * @return エラーメッセージのリスト
	 * @throws SQLException           SQL関連の例外
	 * @throws ClassNotFoundException データベース接続時の例外
	 */
	public static List<String> makeInputErrorMessageList(CategoryForm categoryForm)
			throws ClassNotFoundException, SQLException {
		List<String> errorMessageList = new ArrayList<>();
		String name = categoryForm.getName();
		String description = categoryForm.getDescription();
//		System.out.println("name:" + name);
		// ****** 名前のチェック ****** //
		if (CommonValid.isEmpty(name)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_CATEGORY_NAME));
		} else if (CommonValid.overRange(name, Constant.CATEGORY_NAME_LENGTH_MIN, Constant.CATEGORY_NAME_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_CATEGORY_NAME,
					Constant.CATEGORY_NAME_LENGTH_MIN, Constant.CATEGORY_NAME_LENGTH_MAX));
		} else if (checkNameDuplicate(name, categoryForm.getId())) {
			// カテゴリ名の重複
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_DUPLICATE, Constant.DATA_CATEGORY_NAME));
		}
		// ****** 説明のチェック ****** //
		if (CommonValid.overLength(description, Constant.CATEGORY_DESCRIPTION_LENGTH_MAX)) {
			// 桁数の範囲超えチェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_DESCRIPTION,
					Constant.CATEGORY_DESCRIPTION_LENGTH_MIN, Constant.CATEGORY_DESCRIPTION_LENGTH_MAX));
		}

		return errorMessageList;
	}

	/**
	 * カテゴリ名の重複チェック
	 * 
	 * @param name       入力されたカテゴリ名
	 * @param categoryId カテゴリID
	 * @return True(重複あり)、False(重複なし)
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 */
	private static boolean checkNameDuplicate(String name, String categoryId)
			throws ClassNotFoundException, SQLException {
		Integer id = CategoryDao.findOneByName(name);
		if (id != null) {
			if (categoryId == null) {
				// 新規登録で商品名が既存商品と重複
				return true;
			} else if (id != Integer.parseInt(categoryId)) {
				// 変更で商品名が他の商品と重複
				return true;
			}
		}
		return false;
	}

}

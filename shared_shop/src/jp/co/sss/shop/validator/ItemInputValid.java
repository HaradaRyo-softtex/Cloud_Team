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
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.form.ItemForm;

/**
 * 商品情報の入力チェック
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class ItemInputValid {

	/**
	 * 商品登録・商品変更画面に表示するエラーメッセージのリストを作成するメソッド
	 *
	 * @param itemForm 入力された商品情報
	 * @return エラーメッセージのリスト
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 */
	public static List<String> makeInputErrorMessageList(ItemForm itemForm)
			throws ClassNotFoundException, SQLException {
		List<String> errorMessageList = new ArrayList<>();
		String name = itemForm.getName();
		String price = itemForm.getPrice();
		String description = itemForm.getDescription();
		String stock = itemForm.getStock();

		// ****** 名前のチェック ****** //
		if (CommonValid.isEmpty(name)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_ITEM_NAME));
		} else if (CommonValid.overRange(name, Constant.ITEM_NAME_LENGTH_MIN, Constant.ITEM_NAME_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_ITEM_NAME,
					Constant.ITEM_NAME_LENGTH_MIN, Constant.ITEM_NAME_LENGTH_MAX));
		} else if (checkNameDuplicate(name, itemForm.getId())) {
			// 商品名の重複
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_DUPLICATE, Constant.DATA_ITEM_NAME));
		}
		// ****** 価格のチェック ****** //
		if (CommonValid.isEmpty(price)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_ITEM_PRICE));
		} else if (CommonValid.isNumber(price)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_ITEM_PRICE));
		} else if (CommonValid.overRange(Integer.parseInt(price), Constant.PRICE_MIN, Constant.PRICE_MAX)) {
			// 値の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_INT, Constant.DATA_ITEM_PRICE,
					Constant.PRICE_MIN, Constant.PRICE_MAX));
		}
		// ****** 説明のチェック ****** //
		if (CommonValid.overLength(description, Constant.ITEM_DESCRIPTION_LENGTH_MAX)) {
			// 桁数の範囲超えチェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_DESCRIPTION,
					Constant.ITEM_DESCRIPTION_LENGTH_MIN, Constant.ITEM_DESCRIPTION_LENGTH_MAX));
		}
		// ****** 在庫数のチェック ****** //
		if (CommonValid.isEmpty(stock)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_ITEM_STOCK));
		} else if (CommonValid.isNumber(stock)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_ITEM_STOCK));
		} else if (CommonValid.overRange(Integer.parseInt(stock), Constant.STOCK_MIN, Constant.STOCK_MAX)) {
			// 値の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_INT, Constant.DATA_ITEM_STOCK,
					Constant.STOCK_MIN, Constant.STOCK_MAX));
		}

		return errorMessageList;
	}

	/**
	 * 商品名の重複チェック
	 * 
	 * @param name   入力された商品名
	 * @param itemId 商品ID
	 * @return True(重複あり)、False(重複なし)
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 */
	private static boolean checkNameDuplicate(String name, String itemId) throws ClassNotFoundException, SQLException {

		Integer id = ItemDao.findOneByName(name);
		if (id != null) {
			if (itemId == null) {
				// 新規登録で商品名が既存商品と重複
				return true;
			} else if (id != Integer.parseInt(itemId)) {
				// 変更で商品名が他の商品と重複
				return true;
			}
		}
		return false;
	}
}

/**
 *
 */
package jp.co.sss.shop.validator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;

/**
 * ログイン情報の入力チェック
 * 
 * @author System Shared.co.,Ltd.
 */
public class LoginValid {

	/**
	 * ログイン画面に表示するエラーメッセージのリストを作成するメソッド
	 *
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return エラーメッセージのリスト
	 */
	public static List<String> makeInputErrorMessageList(String email, String password) {
		List<String> errorMessageList = new ArrayList<>();

		// ****** emailのチェック ****** //
		if (CommonValid.isEmpty(email)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_EMAIL));
		} else if (CommonValid.isPatternMisMatch(email, Constant.EMAIL_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_UNFORMAT, Constant.DATA_EMAIL));
		} else if (CommonValid.overRange(email, Constant.EMAIL_LENGTH_MIN, Constant.EMAIL_LENGTH_MAX)) {
			// 形式チェック長さ
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_UNFORMAT, Constant.DATA_EMAIL));
		}
		// ****** パスワードのチェック ****** //
		if (CommonValid.isEmpty(password)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_PASSWORD));
		} else if (CommonValid.isPatternMisMatch(password, Constant.PASSWORD_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_UNFORMAT, Constant.DATA_PASSWORD));
		} else if (CommonValid.overRange(password, Constant.PASSWORD_LENGTH_MIN, Constant.PASSWORD_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_PASSWORD,
					Constant.PASSWORD_LENGTH_MIN, Constant.PASSWORD_LENGTH_MAX));
		}

		return errorMessageList;
	}

}

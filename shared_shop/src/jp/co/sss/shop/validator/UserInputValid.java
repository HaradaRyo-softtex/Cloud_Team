/**
 *
 */
package jp.co.sss.shop.validator;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.form.OrderForm;
import jp.co.sss.shop.form.UserForm;

/**
 * 会員情報の入力チェック
 * 
 * @author System Shared.co.,Ltd.
 */
public class UserInputValid {

	/**
	 * 会員登録・会員変更画面に表示するエラーメッセージのリストを作成するメソッド
	 *
	 * @param userForm 会員情報
	 * @return エラーメッセージのリスト
	 * @throws SQLException           SQL関連の例外
	 * @throws ClassNotFoundException データベース接続時の例外
	 */
	public static List<String> makeInputErrorMessageList(UserForm userForm)
			throws ClassNotFoundException, SQLException {
		List<String> errorMessageList = new ArrayList<>();
		String email = userForm.getEmail();
		String password = userForm.getPassword();
		String name = userForm.getName();
		String postalCode = userForm.getPostalCode();
		String address = userForm.getAddress();
		String phoneNumber = userForm.getPhoneNumber();

		// ****** emailのチェック ****** //
		if (CommonValid.isEmpty(email)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_EMAIL));
		} else if (CommonValid.overRange(email, Constant.EMAIL_LENGTH_MIN, Constant.EMAIL_LENGTH_MAX)) {
			// 形式チェック長さ
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_UNFORMAT, Constant.DATA_EMAIL));
		} else if (CommonValid.isPatternMisMatch(email, Constant.EMAIL_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_UNFORMAT, Constant.DATA_EMAIL));
		} else if (checkEmailDuplicate(email, userForm.getId())) {
			// メールアドレスの重複
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_DUPLICATE, Constant.DATA_EMAIL));
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
		// ****** 名前のチェック ****** //
		if (CommonValid.isEmpty(name)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_USERNAME));
		} else if (CommonValid.overRange(name, Constant.USERNAME_LENGTH_MIN, Constant.USERNAME_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_USERNAME,
					Constant.USERNAME_LENGTH_MIN, Constant.USERNAME_LENGTH_MAX));
		}
		// ****** 郵便番号のチェック ****** //
		if (CommonValid.isEmpty(postalCode)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_POSTALCODE));
		} else if (CommonValid.isPatternMisMatch(postalCode, Constant.POSTALCODE_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_POSTALCODE));
		} else if (CommonValid.overRange(postalCode, Constant.POSTALCODE_LENGTH_MIN, Constant.POSTALCODE_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_POSTALCODE,
					Constant.POSTALCODE_LENGTH_MIN, Constant.POSTALCODE_LENGTH_MAX));
		}
		// ****** 住所のチェック ****** //
		if (CommonValid.isEmpty(address)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_ADDRESS));
		} else if (CommonValid.overRange(address, Constant.ADDRESS_LENGTH_MIN, Constant.ADDRESS_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_ADDRESS,
					Constant.ADDRESS_LENGTH_MIN, Constant.ADDRESS_LENGTH_MAX));
		}
		// ****** 電話番号のチェック ****** //
		if (CommonValid.isEmpty(phoneNumber)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_PHONENUMBER));
		} else if (CommonValid.isPatternMisMatch(phoneNumber, Constant.PHONENUMBER_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_PHONENUMBER));
		} else if (CommonValid.overRange(phoneNumber, Constant.PHONENUMBER_LENGTH_MIN,
				Constant.PHONENUMBER_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_PHONENUMBER,
					Constant.PHONENUMBER_LENGTH_MIN, Constant.PHONENUMBER_LENGTH_MAX));
		}

		return errorMessageList;
	}
	public static List<String> orderInputErrorMessageList(OrderForm orderform)
			throws ClassNotFoundException, SQLException {
		List<String> errorMessageList = new ArrayList<>();
		
		String name = orderform.getName();
		String postalCode = orderform.getPostalCode();
		String address = orderform.getAddress();
		String phoneNumber = orderform.getPhoneNumber();
		if (CommonValid.isEmpty(postalCode)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_POSTALCODE));
		} else if (CommonValid.isPatternMisMatch(postalCode, Constant.POSTALCODE_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_POSTALCODE));
		} else if (CommonValid.overRange(postalCode, Constant.POSTALCODE_LENGTH_MIN, Constant.POSTALCODE_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_POSTALCODE,
					Constant.POSTALCODE_LENGTH_MIN, Constant.POSTALCODE_LENGTH_MAX));
		}
		// ****** 住所のチェック ****** //
		if (CommonValid.isEmpty(address)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_ADDRESS));
		} else if (CommonValid.overRange(address, Constant.ADDRESS_LENGTH_MIN, Constant.ADDRESS_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_ADDRESS,
					Constant.ADDRESS_LENGTH_MIN, Constant.ADDRESS_LENGTH_MAX));
		}
		// ****** 名前のチェック ****** //
		if (CommonValid.isEmpty(name)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_USERNAME));
		} else if (CommonValid.overRange(name, Constant.USERNAME_LENGTH_MIN, Constant.USERNAME_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_USERNAME,
					Constant.USERNAME_LENGTH_MIN, Constant.USERNAME_LENGTH_MAX));
		}
		// ****** 電話番号のチェック ****** //
		if (CommonValid.isEmpty(phoneNumber)) {
			// 未入力チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_REQUIRED, Constant.DATA_PHONENUMBER));
		} else if (CommonValid.isPatternMisMatch(phoneNumber, Constant.PHONENUMBER_PATTERN)) {
			// 形式チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_NUM_ONLY, Constant.DATA_PHONENUMBER));
		} else if (CommonValid.overRange(phoneNumber, Constant.PHONENUMBER_LENGTH_MIN,
				Constant.PHONENUMBER_LENGTH_MAX)) {
			// 桁数の範囲チェック
			errorMessageList.add(MessageFormat.format(MSGConstant.MSG_OVERRANGE_STR, Constant.DATA_PHONENUMBER,
					Constant.PHONENUMBER_LENGTH_MIN, Constant.PHONENUMBER_LENGTH_MAX));
		}
		return errorMessageList;
	}

	/**
	 * Emailの重複チェックを行う処理
	 * 
	 * @param email 入力されたメールアドレス
	 * @param id    会員ID(変更時のみ)
	 * @return True:重複あり、False：重複なし
	 * @throws SQLException           SQL関連の例外
	 * @throws ClassNotFoundException データベース接続時の例外
	 */
	private static boolean checkEmailDuplicate(String email, String id) throws ClassNotFoundException, SQLException {

		UserBean userBean = UserDao.findOneByEmail(email);
		if (userBean != null) {
			if (id == null) {
				// 新規登録でメールアドレスが既存会員と重複
				return true;
			} else if (userBean.getId() != Integer.parseInt(id)) {
				// 変更でメールアドレスが他の会員と重複
				return true;
			}
		}
		return false;
	}

}

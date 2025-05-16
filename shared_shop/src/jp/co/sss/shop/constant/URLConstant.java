/**
 *
 */
package jp.co.sss.shop.constant;

/**
 * URLに関連する文字列をまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class URLConstant {

	/** インスタンス化を禁止 */
	private URLConstant() {
	}

	/** ウェルカムページのURL */
	public static final String URL_WELCOME = "/index.jsp"; //$NON-NLS-1$

	/** トップ画面のURL */
	public static final String URL_TOP = "/top"; //$NON-NLS-1$

	/** エラー画面のURL */
	public static final String URL_ERROR_TYPE = "/error?type="; //$NON-NLS-1$

	/** ログイン画面のURL */
	public static final String URL_LOGIN = "/login"; //$NON-NLS-1$
	/** ログアウト画面のURL */
	public static final String URL_LOGOUT = "/logout"; //$NON-NLS-1$

	/** 一般会員向け商品機能のURL */
	public static final String URL_CLIENT_ITEM = "/item/*"; //$NON-NLS-1$
	/** 一般会員向け買い物かご機能のURL */
	public static final String URL_CLIENT_BASKET = "/basket/*"; //$NON-NLS-1$
	/** 一般会員向け注文機能のURL */
	public static final String URL_CLIENT_ORDER = "/order/*"; //$NON-NLS-1$
	/** 一般会員向け会員機能のURL */
	public static final String URL_CLIENT_USER = "/user/*"; //$NON-NLS-1$
	/** 一般会員向け会員変更機能のURL */
	public static final String URL_CLIENT_USER_UPDATE = "/user/update/*"; //$NON-NLS-1$
	/** 一般会員向け会員詳細機能のURL */
	public static final String URL_CLIENT_USER_DETAIL = "/user/detail"; //$NON-NLS-1$
	/** 一般会員向け会員管理登録確認のURL */
	public static final String URL_CLIENT_USER_REGIST_CONFIRM = "/user/regist/confirm"; //$NON-NLS-1$
	/** 一般会員向け会員管理変更確認のURL */
	public static final String URL_CLIENT_USER_UPDATE_CONFIRM = "/user/update/confirm"; //$NON-NLS-1$
	/** 一般会員向け会員削除機能のURL */
	public static final String URL_CLIENT_USER_DELETE_CONFIRM = "/user/delete/confirm"; //$NON-NLS-1$
	/** 注文登録 お届け先入力画面のURL */
	public static final String URL_ORDER_ADDRESS_INPUT = "/order/address/input"; //$NON-NLS-1$
	/** 注文登録 支払方法入力画面のURL */
	public static final String URL_ORDER_PAYMENT_INPUT = "/order/payment/input"; //$NON-NLS-1$
	/** 注文登録 確認画面のURL */
	public static final String URL_ORDER_CONFIRM = "/order/regist/confirm"; //$NON-NLS-1$
	/** 注文登録 確認画面のURL */
	public static final String URL_ORDER_COMPLETE = "/order/regist/complete"; //$NON-NLS-1$
	/** 管理者機能の先頭URL */
	public static final String URL_ADMIN_ALL = "/admin/*"; //$NON-NLS-1$
	/** 運用管理者向け注文管理機能のURL */
	public static final String URL_ADMIN_ORDER = "/admin/order/*"; //$NON-NLS-1$
	/** 運用管理者向け商品管理機能のURL */
	public static final String URL_ADMIN_ITEM = "/admin/item/*"; //$NON-NLS-1$
	/** 運用管理者向けカテゴリ管理機能のURL */
	public static final String URL_ADMIN_CATEGORY = "/admin/category/*"; //$NON-NLS-1$

	/** 運用管理者向けカテゴリ管理登録確認のURL */
	public static final String URL_ADMIN_CATEGORY_REGIST_CONFIRM = "/admin/category/regist/confirm"; //$NON-NLS-1$
	/** 運用管理者向けカテゴリ管理変更確認のURL */
	public static final String URL_ADMIN_CATEGORY_UPDATE_CONFIRM = "/admin/category/update/confirm"; //$NON-NLS-1$
	/** 運用管理者向けカテゴリ管理削除確認のURL */
	public static final String URL_ADMIN_CATEGORY_DELETE_CONFIRM = "/admin/category/delete/confirm"; //$NON-NLS-1$

	/** 運用管理者向け商品管理登録確認のURL */
	public static final String URL_ADMIN_ITEM_REGIST_CONFIRM = "/admin/item/regist/confirm"; //$NON-NLS-1$
	/** 運用管理者向け商品管理変更確認のURL */
	public static final String URL_ADMIN_ITEM_UPDATE_CONFIRM = "/admin/item/update/confirm"; //$NON-NLS-1$
	/** 運用管理者向け商品管理削除確認のURL */
	public static final String URL_ADMIN_ITEM_DELETE_CONFIRM = "/admin/item/delete/confirm"; //$NON-NLS-1$

	/** 管理者向け会員管理登録確認のURL */
	public static final String URL_ADMIN_USER_REGIST_CONFIRM = "/admin/user/regist/confirm"; //$NON-NLS-1$
	/** 管理者向け会員管理変更確認のURL */
	public static final String URL_ADMIN_USER_UPDATE_CONFIRM = "/admin/user/update/confirm"; //$NON-NLS-1$
	/** 管理者向け会員管理削除確認のURL */
	public static final String URL_ADMIN_USER_DELETE_CONFIRM = "/admin/user/delete/confirm"; //$NON-NLS-1$

	/** 一覧画面のURL */
	public static final String URL_LIST = "/list"; //$NON-NLS-1$
	/** 詳細画面のURL */
	public static final String URL_DETAIL = "/detail"; //$NON-NLS-1$
	/** 入力画面のURL */
	public static final String URL_INPUT = "/input"; //$NON-NLS-1$
	/** 確認画面のURL */
	public static final String URL_CONFIRM = "/confirm"; //$NON-NLS-1$

	/** CSS保存用フォルダの名前 */
	public static final String CSS_FOLDER = "/css/"; //$NON-NLS-1$

	/** 画像ファイル保存用フォルダの名前 */
	public static final String IMAGE_PATH = "/img/"; //$NON-NLS-1$

}

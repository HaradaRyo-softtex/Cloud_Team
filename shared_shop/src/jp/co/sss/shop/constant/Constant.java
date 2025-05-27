package jp.co.sss.shop.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 設定値をまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class Constant {

	/** インスタンス化を禁止 */
	private Constant() {
	}

	/** 画像ファイルが登録されていない場合に表示する画像データのパス名 */
	public static final String FILENAME_NOIMAGE = "common/no_image.jpg"; //$NON-NLS-1$

	/** 画像ファイル保存用実フォルダのパス名 */
	// TODO ※環境によって変更する必要あり※
	public static final String IMAGE_REAL_PATH = "C:\\pleiades-202112\\pleiades\\workspace\\shared_shop\\WebContent\\img\\"; //$NON-NLS-1$

	/** 一般会員の商品詳細画面で表示する在庫状態(なし) */
	public static final String STOCK_ZERO = "在庫なし"; //$NON-NLS-1$
	/** 一般会員の商品詳細画面で表示する在庫状態(あり) */
	public static final String STOCK_RICH = "在庫あり"; //$NON-NLS-1$
	/** サイドバーのカテゴリ選択のリストボックスで初期値として表示する文字列 */
	public static final String CATEGORY_SELECT_NONE = "--指定なし--"; //$NON-NLS-1$
	/** サイドバーのカテゴリ選択のリストボックスで初期値としてセットする値 */
	public static final String CATEGORY_SELECT_NONE_NO = "0"; //$NON-NLS-1$

	/** 登録、変更処理で、画面遷移の制御に使う値 通常の遷移 */
	public static final String BACKFLG_OFF = "0"; //$NON-NLS-1$
	/** 登録、変更処理で、画面遷移の制御に使う値 戻るボタンでの遷移 */
	public static final String BACKFLG_ON = "1"; //$NON-NLS-1$

	/** 権限の値(システム管理者) */
	public static final int AUTH_SYSTEM = 0;
	/** 権限の表示名称(システム管理者) */
	public static final String AUTH_SYSTEM_STR = "システム管理者"; //$NON-NLS-1$
	/** 権限の値(運用管理者) */
	public static final int AUTH_ADMIN = 1;
	/** 権限の表示名称(運用管理者) */
	public static final String AUTH_ADMIN_STR = "運用管理者"; //$NON-NLS-1$
	/** 権限の値(一般会員) */
	public static final int AUTH_CLIENT = 2;
	/** 権限の表示名称(一般会員) */
	public static final String AUTH_CLIENT_STR = "一般会員"; //$NON-NLS-1$

	/** 権限の数値と表示名のマップ */
	public static final Map<Integer, String> AUTH_MAP = new HashMap<Integer, String>() {
		{
			put(AUTH_SYSTEM, AUTH_SYSTEM_STR);
			put(AUTH_ADMIN, AUTH_ADMIN_STR);
			put(AUTH_CLIENT, AUTH_CLIENT_STR);
		}
	};

	/** 並び順の値(新着順) */
	public static final String SORT_LATEST = "1"; //$NON-NLS-1$
	/** 並び順の表示名称(新着順) */
	public static final String SORT_LATEST_STR = "新着順"; //$NON-NLS-1$
	/** 並び順の表示名称(トップ画面用) */
	public static final String SORT_LATEST_TITLE = "新着商品"; //$NON-NLS-1$
	/** 並び順の値(売れ筋順) */
	public static final String SORT_HOTSELL = "2"; //$NON-NLS-1$
	/** 並び順の表示名称(売れ筋順) */
	public static final String SORT_HOTSELL_STR = "売れ筋順"; //$NON-NLS-1$
	/** 並び順の表示名称(トップ画面用) */
	public static final String SORT_HOTSELL_TITLE = "売れ筋商品"; //$NON-NLS-1$
	
	//追加機能　価格順
	/** 並び順の値(価格が安い順) */
	public static final String SORT_PRICE_ASC = "3"; //$NON-NLS-1$
	/** 並び順の表示名称(価格が安い順) */
	public static final String SORT_PRICE_ASC_STR = "価格：安い順"; //$NON-NLS-1$
	/** 並び順の値(価格が高い順) */
	public static final String SORT_PRICE_DESC = "4"; //$NON-NLS-1$
	/** 並び順の表示名称(価格が高い順) */
	public static final String SORT_PRICE_DESC_STR = "価格：高い順"; //$NON-NLS-1$

	/** 支払方法の選択肢(数値) クレジットカード */
	public static final int PAYMETHOD_CREDIT = 1;
	/** 支払方法の選択肢(数値) 銀行振り込み */
	public static final int PAYMETHOD_BANK = 2;
	/** 支払方法の選択肢(数値) 着払い */
	public static final int PAYMETHOD_ONARRIVAL = 3;
	/** 支払方法の選択肢(数値) 電子マネー */
	public static final int PAYMETHOD_EMONEY = 4;
	/** 支払方法の選択肢(数値) コンビニ決済 */
	public static final int PAYMETHOD_CONVENIENCE = 5;

	/** 支払方法の選択肢(表示文字列) クレジットカード */
	public static final String PAYMETHOD_CREDIT_STR = "クレジットカード"; //$NON-NLS-1$
	/** 支払方法の選択肢(表示文字列) 銀行振り込み */
	public static final String PAYMETHOD_BANK_STR = "銀行振込"; //$NON-NLS-1$
	/** 支払方法の選択肢(表示文字列) 着払い */
	public static final String PAYMETHOD_ONARRIVAL_STR = "着払い"; //$NON-NLS-1$
	/** 支払方法の選択肢(表示文字列) 電子マネー */
	public static final String PAYMETHOD_EMONEY_STR = "電子マネー"; //$NON-NLS-1$
	/** 支払方法の選択肢(表示文字列) コンビニ決済 */
	public static final String PAYMETHOD_CONVENIENCE_STR = "コンビニ決済"; //$NON-NLS-1$

	/** 支払方法の数値と表示名のマップ */
	public static final Map<Integer, String> PAYMETHOD_MAP = new HashMap<Integer, String>() {
		{
			put(PAYMETHOD_CREDIT, PAYMETHOD_CREDIT_STR);
			put(PAYMETHOD_BANK, PAYMETHOD_BANK_STR);
			put(PAYMETHOD_ONARRIVAL, PAYMETHOD_ONARRIVAL_STR);
			put(PAYMETHOD_EMONEY, PAYMETHOD_EMONEY_STR);
			put(PAYMETHOD_CONVENIENCE, PAYMETHOD_CONVENIENCE_STR);
		}
	};
	// **** エラー番号 ****

	/** SQLException,ClassNotFoundExceptionが発生した場合 */
	public static final String ERROR_CODE_DB = "900"; //$NON-NLS-1$
	/** 例外発生なしで、id指定でデータ取得した結果がない場合 */
	public static final String ERROR_CODE_DB_NONE = "901"; //$NON-NLS-1$
	/** executeUpdateの戻り値が0だったら、エラー扱いとする */
	public static final String ERROR_CODE_DB_UPDATE = "902"; //$NON-NLS-1$
	/** スコープにあるはずの情報がない、SQL関連以外の例外発生等の状態 */
	public static final String ERROR_CODE_SYS = "800"; //$NON-NLS-1$
	/** 想定外の画面遷移 */
	public static final String ERROR_CODE_UNEXPECTED_TRANSITION = "801"; //$NON-NLS-1$
	/** 不正なIDの指定 */
	public static final String ERROR_CODE_ILLEGAL_ID = "802"; //$NON-NLS-1$
	/** HttpStatusCode InternalServerError 内部エラー発生 Web.xmlと連動 */
	public static final String ERROR_CODE_INTERNAL = "500"; //$NON-NLS-1$
	/** HttpStatusCode NotFound 存在しないURL Web.xmlと連動 */
	public static final String ERROR_CODE_NOT_FOUND = "404"; //$NON-NLS-1$
	/** HttpStatusCode Method Not Allowed 許可されていないメソッド要求 Web.xmlと連動 */
	public static final String ERROR_CODE_METHOD_NOT_ARROWED = "405"; //$NON-NLS-1$

	/** カテゴリ情報の最大レコード数 */
	public static final int CATEGORY_RECORD_MAX = 99;
	/** 商品情報の最大レコード数 */
	public static final int ITEM_RECORD_MAX = 999999;
	/** 会員情報の最大レコード数 */
	public static final int USER_RECORD_MAX = 999999;
	// ***** 入力チェックの規定値 *****
	/** メールアドレスの正規表現 [英数字+.-で構成された文字列]@[英数字_.で構成された文字列] */
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"; //$NON-NLS-1$
	/** メールアドレスの長さの最小値 */
	public static final int EMAIL_LENGTH_MIN = 5;
	/** メールアドレスの長さの最大値 */
	public static final int EMAIL_LENGTH_MAX = 129;

	/** パスワードの正規表現 英数字のみ */
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]+$"; //$NON-NLS-1$
	/** パスワードの長さの最小値 */
	public static final int PASSWORD_LENGTH_MIN = 8;
	/** パスワードの長さの最大値 */
	public static final int PASSWORD_LENGTH_MAX = 16;

	/** 価格の最小値 */
	public static final int PRICE_MIN = 0;
	/** 価格の最大値 */
	public static final int PRICE_MAX = 9999999;

	/** 在庫の最小値 */
	public static final int STOCK_MIN = 0;
	/** 在庫の最大値 */
	public static final int STOCK_MAX = 9999;

	/** 電話番号の正規表現 */
	public static final String PHONENUMBER_PATTERN = "^[0-9]+$"; //$NON-NLS-1$
	/** 電話番号の長さの最小値 */
	public static final int PHONENUMBER_LENGTH_MIN = 10;
	/** 電話番号の長さの最大値 */
	public static final int PHONENUMBER_LENGTH_MAX = 11;

	/** 郵便番号の正規表現 */
	public static final String POSTALCODE_PATTERN = "^[0-9]+$"; //$NON-NLS-1$
	/** 郵便番号の長さの最小値 */
	public static final int POSTALCODE_LENGTH_MIN = 7;
	/** 郵便番号の長さの最大値 */
	public static final int POSTALCODE_LENGTH_MAX = 8;

	/** 商品名の長さの最小値 */
	public static final int ITEM_NAME_LENGTH_MIN = 1;
	/** 商品名の長さの最大値 */
	public static final int ITEM_NAME_LENGTH_MAX = 100;
	/** 商品説明文の長さの最小値 */
	public static final int ITEM_DESCRIPTION_LENGTH_MIN = 0;
	/** 商品説明文の長さの最大値 */
	public static final int ITEM_DESCRIPTION_LENGTH_MAX = 400;

	/** カテゴリ名の長さの最小値 */
	public static final int CATEGORY_NAME_LENGTH_MIN = 1;
	/** カテゴリ名の長さの最大値 */
	public static final int CATEGORY_NAME_LENGTH_MAX = 15;
	/** カテゴリ説明文の長さの最小値 */
	public static final int CATEGORY_DESCRIPTION_LENGTH_MIN = 0;
	/** カテゴリ説明文の長さの最大値 */
	public static final int CATEGORY_DESCRIPTION_LENGTH_MAX = 30;

	/** 住所の長さの最小値 */
	public static final int ADDRESS_LENGTH_MIN = 1;
	/** 住所の長さの最大値 */
	public static final int ADDRESS_LENGTH_MAX = 150;

	/** 名前の長さの最小値 */
	public static final int USERNAME_LENGTH_MIN = 1;
	/** 名前の長さの最大値 */
	public static final int USERNAME_LENGTH_MAX = 30;

	/** 日付のフォーマット */
	public static final String DATE_FORMAT = "yyyy/MM/dd"; //$NON-NLS-1$
	/** 画像ファイルに付与する日付のフォーマット */
	public static final String IMG_FILE_DATE_FORMAT = "yyyyMMddhhmmss"; //$NON-NLS-1$

	// ***** 入力項目名 *****
	// ログイン画面、会員登録、会員変更
	/** メールアドレス */
	public static final String DATA_EMAIL = "メールアドレス"; //$NON-NLS-1$
	/** パスワード */
	public static final String DATA_PASSWORD = "パスワード"; //$NON-NLS-1$
	/** パスワード確認 */
	public static final String DATA_RECHECKPASSWORD = "パスワード再確認"; //$NON-NLS-1$
	/** 氏名 */
	public static final String DATA_USERNAME = "氏名"; //$NON-NLS-1$
	/** 郵便番号 */
	public static final String DATA_POSTALCODE = "郵便番号"; //$NON-NLS-1$
	/** 住所 */
	public static final String DATA_ADDRESS = "住所"; //$NON-NLS-1$
	/** 電話番号 */
	public static final String DATA_PHONENUMBER = "電話番号"; //$NON-NLS-1$
	/** 権限 */
	public static final String DATA_AUTHORITY = "権限"; //$NON-NLS-1$

	// 注文 お届け先入力

	/** お届け先名 */
	public static final String DATA_ADDRESSEE_NAME = "お届け先氏名"; //$NON-NLS-1$
	/** お届け先住所 */
	public static final String DATA_ADDRESSEE_ADDRESS = "お届け先住所"; //$NON-NLS-1$
	/** お届け先郵便番号 */
	public static final String DATA_ADDRESSEE_POSTALCODE = "お届け先郵便番号"; //$NON-NLS-1$
	/** お届け先郵便番号 */
	public static final String DATA_ADDRESSEE_PHONENUMBER = "お届け先電話番号"; //$NON-NLS-1$
	/** 支払方法 */
	public static final String DATA_PAYMETHOD = "支払い方法"; //$NON-NLS-1$

	// 注文一覧、詳細
	/** 注文日 */
	public static final String DATA_ORDER_DAY = "注文日"; //$NON-NLS-1$
	/** 単価 */
	public static final String DATA_UNIT_PRICE = "単価"; //$NON-NLS-1$
	/** 数量 */
	public static final String DATA_ORDER_NUM = "数量"; //$NON-NLS-1$
	/** 小計 */
	public static final String DATA_SUBTOTAL = "小計"; //$NON-NLS-1$
	/** 合計金額 */
	public static final String DATA_TOTAL = "合計金額"; //$NON-NLS-1$

	// 商品、カテゴリ入力
	/** 商品名 */
	public static final String DATA_ITEM_NAME = "商品名"; //$NON-NLS-1$
	/** カテゴリ名 */
	public static final String DATA_CATEGORY_NAME = "カテゴリ名"; //$NON-NLS-1$
	/** 商品在庫 */
	public static final String DATA_ITEM_STOCK = "在庫数"; //$NON-NLS-1$
	/** 商品価格 */
	public static final String DATA_ITEM_PRICE = "価格"; //$NON-NLS-1$
	/** 商品価格 */
	public static final String DATA_ITEM_IMAGE = "商品画像"; //$NON-NLS-1$
	/** 商品説明文、カテゴリ説明文 */
	public static final String DATA_DESCRIPTION = "説明"; //$NON-NLS-1$

	// 買い物かご
	/** 在庫状況 */
	public static final String DATA_ITEM_STOCK_STATUS = "在庫状況"; //$NON-NLS-1$

	// 情報名称
	/** 商品 */
	public static final String ITEM = "商品"; //$NON-NLS-1$
	/** カテゴリ */
	public static final String CATEGORY = "カテゴリ"; //$NON-NLS-1$
	/** 会員 */
	public static final String USER = "会員"; //$NON-NLS-1$
	/** 注文 */
	public static final String ORDER = "注文"; //$NON-NLS-1$
	/** 買い物かご */
	public static final String BASKET = "買い物かご"; //$NON-NLS-1$
	/** お届け先 */
	public static final String ADDRESSEE = "お届け先"; //$NON-NLS-1$
	/** トップ */
	public static final String TOP = "トップ"; //$NON-NLS-1$
	/** ログイン */
	public static final String LOGIN = "ログイン"; //$NON-NLS-1$
	/** ログイン */
	public static final String LOGOUT = "ログアウト"; //$NON-NLS-1$
	/** 新規会員登録 */
	public static final String CLIENT_REGIST = "新規会員登録"; //$NON-NLS-1$
	/** 管理者用メニュー */
	public static final String ADMIN_MENU = "管理者用メニュー"; //$NON-NLS-1$
	/** ページタイトル */
	public static final String SHOP_TITLE = "シェアードショップ"; //$NON-NLS-1$
	/** エラー画面のタイトル */
	public static final String ERROR = "メンテナンス中"; //$NON-NLS-1$

	// 一覧表示共通項目
	/** ID */
	public static final String DATA_ID = "ID"; //$NON-NLS-1$
	/** 操作 */
	public static final String LIST_TITLE_ACTION = "操作"; //$NON-NLS-1$

}

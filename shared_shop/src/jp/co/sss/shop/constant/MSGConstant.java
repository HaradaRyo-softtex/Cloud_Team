package jp.co.sss.shop.constant;

/**
 * 表示メッセージをまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class MSGConstant {

	/** インスタンス化を禁止 */
	private MSGConstant() {
	}

	// リンク用文字列
	/** トップ画面遷移リンクの文字列 "トップへ戻る" */
	public static final String MSG_BACK_TO_TOP = "トップへ戻る"; //$NON-NLS-1$
	/** リンク用文字列 情報名(商品、カタログ、注文、会員)と組み合わせて利用 "一覧へ戻る" */
	public static final String MSG_BACK_TO_LIST = "一覧へ戻る"; //$NON-NLS-1$
	/** リンク用文字列 情報名(商品、カタログ、注文、会員)と組み合わせて利用 "詳細へ戻る" */
	public static final String MSG_BACK_TO_DETAIL = "詳細へ戻る"; //$NON-NLS-1$

	// 登録変更削除画面のメッセージ
	/** 削除確認画面表示メッセージ "以下の内容を削除します。" */
	public static final String MSG_DELETE_CONFIRM = "以下の内容を削除します。"; //$NON-NLS-1$
	/** 削除完了画面表示メッセージ "情報の削除が完了しました。" */
	public static final String MSG_DELETE_COMPLETE = "情報の削除が完了しました。"; //$NON-NLS-1$
	/** 一般会員 削除確認画面表示メッセージ "以下の会員の退会手続きを行います。" */
	public static final String MSG_CLIENT_DELETE_COMFIRM = "以下の会員の退会手続きを行います。"; //$NON-NLS-1$
	/** 一般会員 削除完了画面表示メッセージ "退会手続きが完了しました。" */
	public static final String MSG_CLIENT_DELETE_COMPLETE = "退会手続きが完了しました。"; //$NON-NLS-1$
	/** 変更入力画面表示メッセージ "変更する情報を入力してください。" */
	public static final String MSG_UPDATE_INPUT = "変更する情報を入力してください。"; //$NON-NLS-1$
	/** 変更確認画面表示メッセージ "以下の内容に変更します。" */
	public static final String MSG_UPDATE_CONFIRM = "以下の内容に変更します。"; //$NON-NLS-1$
	/** 変更完了画面表示メッセージ "情報の変更が完了しました。" */
	public static final String MSG_UPDATE_COMPLETE = "情報の変更が完了しました。"; //$NON-NLS-1$
	/** 登録入力画面表示メッセージ "登録する情報を入力してください。" */
	public static final String MSG_REGIST_INPUT = "登録する情報を入力してください。"; //$NON-NLS-1$
	/** 登録確認画面表示メッセージ "以下の内容を登録します。" */
	public static final String MSG_REGIST_CONFIRM = "以下の内容を登録します。"; //$NON-NLS-1$
	/** 登録完了画面表示メッセージ "情報の登録が完了しました。" */
	public static final String MSG_REGIST_COMPLETE = "情報の登録が完了しました。"; //$NON-NLS-1$
	/** 管理者側各種一覧画面表示メッセージ "情報は最大件数に到達しているため新規登録できません。" */
	public static final String MSG_CANNOT_REGIST = "情報は最大件数に到達しているため新規登録できません。"; //$NON-NLS-1$
	/** エラー画面表示メッセージ"メンテナンス中です。しばらくお待ちください。" */
	public static final String MSG_ERROR_SYS = "メンテナンス中です。しばらくお待ちください。"; //$NON-NLS-1$

	// ログイン画面メッセージ
	/** ログイン画面表示メッセージ */
	public static final String MSG_LOGIN_INPUT = "メールアドレスとパスワードを入力してください。"; //$NON-NLS-1$
	/** ログインID、またはパスワードが間違っていた場合のエラーメッセージ */
	public static final String MSG_LOGIN_EMAIL_OR_PASSWORD_MISMATCH = "メールアドレスもしくはパスワードが間違っています。"; //$NON-NLS-1$

	// 商品一覧画面メッセージ
	/** 検索結果が0件の場合のメッセージ */
	public static final String MSG_ITEM_CLIENT_LIST_NONE = "閲覧できる商品情報がありません。"; //$NON-NLS-1$
	/** 運用管理者側の一覧で0件の場合のメッセージ */
	public static final String MSG_ADMIN_LIST_NONE = "情報は登録されていません。"; //$NON-NLS-1$

	// カテゴリ一覧画面メッセージ
	/** 検索結果が0件の場合のメッセージ */
	public static final String MSG_CATEGORY_LIST_NONE = "閲覧できるカテゴリ情報がありません。"; //$NON-NLS-1$

	// 注文一覧画面メッセージ
	/** お客様側の注文一覧で注文情報が0件の場合のメッセージ */
	public static final String MSG_ORDER_CLIENT_LIST_NONE = "お客様の注文履歴はありません。"; //$NON-NLS-1$
	/** 運用管理者側の注文一覧で注文情報が0件の場合のメッセージ */
	public static final String MSG_ORDER_ADMIN_LIST_NONE = "注文情報は登録されていません。"; //$NON-NLS-1$

	// 注文登録画面メッセージ
	/** お届け先入力画面の表示メッセージ */
	public static final String MSG_ORDER_ADDRESS_INPUT = "お届け先情報を入力してください。"; //$NON-NLS-1$
	/** 注文確認画面を表示する時点で在庫0の場合のメッセージ */
	public static final String MSG_ORDER_ITEM_STOCK_NONE = "は、在庫切れのため注文できません。"; //$NON-NLS-1$
	/** 注文確認画面を表示する時点で在庫不足の場合のメッセージ */
	public static final String MSG_ORDER_ITEM_STOCK_SHORT = "は、在庫不足のため、在庫数分のみ注文できます。"; //$NON-NLS-1$
	/** 注文完了画面の表示メッセージ */
	public static final String MSG_ORDER_COMPLETE = "ご注文ありがとうございます。<br />注文手続きが完了しました。"; //$NON-NLS-1$

	// 買い物かご画面メッセージ
	/** 買い物かごが空の場合のメッセージ */
	public static final String MSG_BASKET_LIST_NONE = "現在、お客様の買い物かごには商品がありません。商品をお選びください。"; //$NON-NLS-1$
	/** 買い物かごに入れていた商品が在庫切れになった場合に表示するメッセージ */
	public static final String MSG_BASKET_STOCK_NONE = "は、在庫切れです。他の商品を選択してください。"; //$NON-NLS-1$
	/** 買い物かごに入れていた商品の在庫が買い物かごに入れている数を下回った場合に表示するメッセージ */
	public static final String MSG_BASKET_STOCK_SHORT = "は、在庫不足のため、数を増やすことができません"; //$NON-NLS-1$

	// 入力チェック 共通メッセージ java.text.MessageFormat#formatを利用することを想定
	/** 必須項目が空欄もしくは半角空白のみ場合のメッセージ */
	public static final String MSG_REQUIRED = "{0}は必須項目です。"; //$NON-NLS-1$
	/** 形式が一致していない場合のメッセージ */
	public static final String MSG_UNFORMAT = "{0}は正しい形式で入力してください。"; //$NON-NLS-1$
	/** 文字数が条件に一致していない場合のメッセージ */
	public static final String MSG_OVERRANGE_STR = "{0}は{1}文字以上{2}文字以内で入力してください。"; //$NON-NLS-1$
	/** 値の範囲が条件に一致していない場合のメッセージ */
	public static final String MSG_OVERRANGE_INT = "{0}は{1}以上{2}以内の値で入力してください。"; //$NON-NLS-1$
	/** 数値のみの項目に数値以外の文字が含まれていた場合のメッセージ */
	public static final String MSG_NUM_ONLY = "{0}は半角数字のみで入力してください。"; //$NON-NLS-1$
	/** 既存情報と重複していた場合のメッセージ メールアドレス、商品名、カテゴリ名で利用 */
	public static final String MSG_DUPLICATE = "入力された{0}は既に利用されています。"; //$NON-NLS-1$

	/** エラー画面のメッセージ */
	public static final String MSG_ERROR = "メンテナンス中"; //$NON-NLS-1$
}

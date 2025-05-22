package jp.co.sss.shop.constant;

/**
 * DB関連の設定値をまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class DBConstant {

	/** インスタンス化を禁止 */
	private DBConstant() {
	}

	// ***** DB接続のための設定値 *****

	/** DBドライバー */
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver"; //$NON-NLS-1$

	/** DB接続用文字列 */
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1"; //$NON-NLS-1$

	/** DB接続用ユーザー */
	public static final String USER_NAME = "shared_shop_user"; //$NON-NLS-1$

	/** DB接続用パスワード */
	public static final String PASSWORD = "systemsss"; //$NON-NLS-1$

	// ***** フラグ値 *****
	/** 削除フラグの値(未削除状態) */
	public static final int NOT_DELETED = 0;

	/** 削除フラグの値(削除状態) */
	public static final int DELETED = 1;

	// ***** 会員関連(usersテーブル) SQL文 *****
	/** 会員表示(全件検索) */
	public static final String SQL_SELECT_ALL_USER = "SELECT * FROM users WHERE delete_flag=" + NOT_DELETED //$NON-NLS-1$
			+ " ORDER BY insert_date DESC,id DESC"; //$NON-NLS-1$

	/** 会員表示(システム管理者以外を抽出) */
	public static final String SQL_SELECT_USER_NOT_AUTH_SYSTEM = "SELECT * FROM users WHERE delete_flag=" + NOT_DELETED //$NON-NLS-1$
			+ " AND authority!=" + Constant.AUTH_SYSTEM + " ORDER BY insert_date DESC,id DESC"; //$NON-NLS-1$ //$NON-NLS-2$
	/** 会員検索(ログイン処理) */
	public static final String SQL_SELECT_BY_EMAIL_AND_PASSWD = "SELECT * FROM users WHERE email=? AND password=? AND delete_flag=" //$NON-NLS-1$
			+ NOT_DELETED;

	/** 会員検索(id検索) */
	public static final String SQL_SELECT_BY_USERID = "SELECT * FROM users WHERE id=? AND delete_flag=" + NOT_DELETED; //$NON-NLS-1$

	/** 会員検索(email検索) */
	public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email=?"; //$NON-NLS-1$

	/** 会員登録 */
	public static final String SQL_INSERT_USER = "INSERT INTO users VALUES(seq_users.NEXTVAL,?,?,?,?,?,?,?,DEFAULT, DEFAULT)"; //$NON-NLS-1$

	/** 会員変更 */
	public static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, password = ?,name = ? ,postal_code = ?, address = ?, phone_number=?,authority=? WHERE id = ?"; //$NON-NLS-1$

	/** 会員削除(削除フラグを立てる) */
	public static final String SQL_DELETE_USER = "UPDATE users SET delete_flag=" + DELETED + " WHERE id = ?"; //$NON-NLS-1$ //$NON-NLS-2$

	/** カテゴリ情報の総件数を取得 */
	public static final String SQL_SELECT_COUNT_USERS = "SELECT count(id) AS count FROM users"; //$NON-NLS-1$

	// 注文商品関連(orderitemsテーブル) SQL */
	/** 注文Idに該当する注文商品情報を検索 注文一覧、注文詳細で利用 */
	public static final String SQL_SELECT_ORDERITEMS_JOIN_ITEMS_BY_ORDERID = "SELECT oi.id,i.name,oi.quantity,oi.price,(oi.quantity*oi.price) AS subtotal " //$NON-NLS-1$
			+ " FROM order_items oi INNER JOIN items i ON oi.item_id=i.id WHERE oi.order_id=?  ORDER BY oi.id ASC"; //$NON-NLS-1$

	/** 注文商品登録 */
	public static final String SQL_INSERT_ORDERITEM = "INSERT INTO order_items VALUES(seq_order_items.NEXTVAL,?,?,?,?)"; //$NON-NLS-1$

	//喜田が追加
	/**注文商品が存在するか確認する　トップ画面で使用*/
	public static final String SQL_SELECT_COUNT_ORDER_ITEMS = "SELECT count(id) AS count FROM order_items";
	
	/** 注文関連(ordersテーブル) SQL */

	/** 会員Idに該当する注文情報を注文日付順で検索 一般会員の注文一覧表示で利用 */
	public static final String SQL_SELECT_ORDERS_BY_USERID_ORDER_BY_INSERT_DATE = "SELECT * FROM orders WHERE user_id=?  ORDER BY insert_date DESC,id ASC"; //$NON-NLS-1$

	/** 会員Idに該当する注文情報を注文日付順,ID降順で検索 注文登録で最新の注文情報を取るために利用 */
	public static final String SQL_SELECT_ORDERS_BY_USERID_ORDER_BY_INSERT_DATE_ID_DESC = "SELECT id FROM orders WHERE user_id=?  ORDER BY insert_date DESC,id DESC"; //$NON-NLS-1$

	/** 注文情報を全件検索(新着順) 管理者の注文一覧表示で利用 */
	public static final String SQL_SELECT_ORDERS_JOIN_USERS_ORDER_BY_INSERT_DATE = "SELECT o.id,o.insert_date,o.pay_method,u.name AS username FROM orders o" //$NON-NLS-1$
			+ " INNER JOIN users u ON o.user_id=u.id ORDER BY insert_date DESC,id ASC"; //$NON-NLS-1$

	/** 注文Idに該当する注文情報を1件取得 管理者の注文詳細表示で利用 */
	public static final String SQL_SELECT_ORDERS_JOIN_USERS_BY_ORDERID = "SELECT o.id,u.name AS username,o.postal_code,o.address,o.name,o.phone_number,o.insert_date,o.pay_method" //$NON-NLS-1$
			+ " FROM orders o INNER JOIN users u ON o.user_id=u.id  WHERE o.id=?"; //$NON-NLS-1$

	// 商品関連(itemsテーブル) SQL */
	/** 商品情報を全件検索(ID順) 管理者用 */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_ID = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.id ASC"; //$NON-NLS-1$

	/** 商品情報を全件検索(新着順) */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_INSERT_DATE = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.insert_date DESC,i.id ASC"; //$NON-NLS-1$
	//追加機能
	/** 商品情報を全件検索(価格が安い順) */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_PRICE_ASC = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.price ASC,i.id ASC"; //$NON-NLS-1$
	//追加機能
	/** 商品情報を全件検索(価格が高い順) */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_PRICE_DESC = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.price DESC,i.id ASC"; //$NON-NLS-1$
	
	/** 商品情報を全件検索（売れ筋順） */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDERITEMS_ORDER_BY_ORDER_COUNT = "SELECT i.id, i.name, i.price, i.image,  c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" +" INNER JOIN order_items oi ON i.id = oi.item_id"+ " WHERE i.delete_flag =" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " GROUP BY i.id, i.name, i.price, i.image, c.name " + " ORDER BY SUM(oi.quantity) DESC,i.id ASC"; //$NON-NLS-1$
	
	/** カテゴリIdに該当する商品情報を新着順で検索 */
	public static final String SQL_SELECT_ITEMS_BY_CATEGORIES_ORDER_BY_INSERT_DATE = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED + " AND i.category_id = ?"//$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.insert_date DESC,i.id ASC"; //$NON-NLS-1$;
	/** カテゴリIdに該当する商品情報を売れ筋順で検索 */
	public static final String SQL_SELECT_ITEMS_BY_CATEGORIES_ORDER_BY_ORDER_COUNT = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " INNER JOIN order_items oi ON i.id = oi.item_id"+ " WHERE i.delete_flag =" + NOT_DELETED + " AND i.category_id = ?"//$NON-NLS-1$ //$NON-NLS-2$
			+ " GROUP BY i.id, i.name, i.price, i.image, c.name " + " ORDER BY SUM(oi.quantity) DESC,i.id ASC"; //$NON-NLS-1$;
	//追加機能
	/** カテゴリIdに該当する商品情報を価格が安い順で検索 */
	public static final String SQL_SELECT_ITEMS_BY_CATEGORIES_ORDER_BY_PRICE_ASC = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED + " AND i.category_id = ?"//$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.price ASC,i.id ASC"; //$NON-NLS-1$;
	//追加機能
	/** カテゴリIdに該当する商品情報を価格が高い順で検索 */
	public static final String SQL_SELECT_ITEMS_BY_CATEGORIES_ORDER_BY_PRICE_DESC = "SELECT i.id, i.name, i.price, i.image, c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id" + " WHERE i.delete_flag =" + NOT_DELETED + " AND i.category_id = ?"//$NON-NLS-1$ //$NON-NLS-2$
			+ " ORDER BY i.price DESC,i.id ASC"; //$NON-NLS-1$;
	
	/** 商品情報を商品IDで検索(商品詳細用) */
	public static final String SQL_SELECT_ITEMS_JOIN_CATEGORIES_BY_ITEMID = "SELECT i.id, i.name, i.price, i.description,i.stock, i.image, c.id AS categoryId ,c.name AS categoryName FROM items i" //$NON-NLS-1$
			+ " INNER JOIN categories c ON i.category_id=c.id " + " WHERE i.delete_flag=" + NOT_DELETED //$NON-NLS-1$ //$NON-NLS-2$
			+ " AND i.id = ?"; //$NON-NLS-1$

	/** 商品情報を商品名で検索 入力チェック用 */
	public static final String SQL_SELECT_ITEMS_BY_NAME = "SELECT id FROM items WHERE name = ?"; //$NON-NLS-1$
	/** 商品登録 */
	public static final String SQL_INSERT_ITEM = "INSERT INTO items VALUES(seq_items.NEXTVAL,?,?,?,?,?,?,DEFAULT,DEFAULT)"; //$NON-NLS-1$

	/** 商品変更 */
	public static final String SQL_UPDATE_ITEM = "UPDATE items SET name = ?, price = ?,description = ? ,stock = ?, image = ?, category_id = ? WHERE id = ?"; //$NON-NLS-1$

	/** 商品削除(削除フラグを立てる) */
	public static final String SQL_DELETE_ITEM = "UPDATE items SET delete_flag=" + DELETED + " WHERE id = ?"; //$NON-NLS-1$ //$NON-NLS-2$

	/** 商品情報の総件数を取得 */
	public static final String SQL_SELECT_COUNT_ITEMS = "SELECT count(id) AS count FROM items"; //$NON-NLS-1$
	
	/**カテゴリIDに該当する商品情報の総件数を取得（追加）*/
	public static final String SQL_SELECT_COUNT_ITEMS_BY_CATEGORY = "SELECT count(id) AS count FROM items " + " WHERE delete_flag =" + NOT_DELETED + " AND category_id = ?"; //$NON-NLS-1$

	// カテゴリ関連(categoriesテーブル) SQL */
	/** カテゴリ情報を全件検索(新着順) */
	public static final String SQL_SELECT_CATEGORIES_ORDER_BY_INSERT_DATE = "SELECT id,name,description FROM categories WHERE delete_flag =" //$NON-NLS-1$
			+ NOT_DELETED + " ORDER BY insert_date DESC,id ASC"; //$NON-NLS-1$

	/** カテゴリIdでカテゴリ情報を取得 */
	public static final String SQL_SELECT_CATEGORIES_BY_ID = "SELECT id,name,description FROM categories WHERE delete_flag =" //$NON-NLS-1$
			+ NOT_DELETED + " AND id=?"; //$NON-NLS-1$

	/** カテゴリ情報をカテゴリ名で検索 入力チェック用 */
	public static final String SQL_SELECT_CATEGORIES_BY_NAME = "SELECT id FROM categories WHERE name = ?"; //$NON-NLS-1$

	/** カテゴリ情報の総件数を取得 */
	public static final String SQL_SELECT_COUNT_CATEGORIES = "SELECT count(id) AS count FROM categories"; //$NON-NLS-1$

	/** カテゴリ登録 */
	public static final String SQL_INSERT_CATEGORY = "INSERT INTO categories VALUES(seq_categories.NEXTVAL,?,?,DEFAULT,DEFAULT)"; //$NON-NLS-1$

	/** カテゴリ変更 */
	public static final String SQL_UPDATE_CATEGORY = "UPDATE categories SET name = ?, description = ? WHERE id = ?"; //$NON-NLS-1$

	/** カテゴリ削除(削除フラグを立てる) */
	public static final String SQL_DELETE_CATEGORY = "UPDATE categories SET delete_flag=" + DELETED + " WHERE id = ?"; //$NON-NLS-1$ //$NON-NLS-2$

}

package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.DBConstant;
import jp.co.sss.shop.dto.Item;

/**
 * 商品テーブルを操作するクラス
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class ItemDao {

	/**
	 * 商品情報一覧(管理者用)を取得する
	 *
	 * @return 商品情報(ItemBean)一覧を取得
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<ItemBean> findAllForAdmin() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<ItemBean> itemBeanList = new ArrayList<>();

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_INSERT_DATE);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			itemBeanList.add(getItemDataNoStock(rs));
		}
		DBManager.close(con, ps);
		return itemBeanList;
	}

	/**
	 * 商品情報一覧を取得する
	 *
	 * @param sortType 並び替え順
	 * @return 商品情報(ItemBean)一覧を取得
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<ItemBean> findAll(String sortType) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<ItemBean> itemBeanList = new ArrayList<>();

		con = DBManager.getConnection();
//		if (sortType.equals(Constant.SORT_LATEST)) {
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDER_BY_INSERT_DATE);
//		} else {
//			ps = con.prepareStatement(DBConstant.SQL_SELECT_ITEMS_JOIN_CATEGORIES_ORDERITEMS_ORDER_BY_ORDER_COUNT);
//		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			itemBeanList.add(getItemDataNoStock(rs));
		}
		DBManager.close(con, ps);
		return itemBeanList;
	}

	/**
	 * 商品Idに該当する情報を1件だけ取得する
	 *
	 * @param itemId 商品ID(文字列)
	 * @return 商品情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static ItemDetailBean findOneByItemId(String itemId) throws SQLException, ClassNotFoundException {
		return findOneByItemId(Integer.parseInt(itemId));
	}

	/**
	 * 商品Idに該当する情報を1件だけ取得する
	 *
	 * @param itemId 商品ID
	 * @return 商品情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static ItemDetailBean findOneByItemId(Integer itemId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ItemDetailBean itemDetailBean = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ITEMS_JOIN_CATEGORIES_BY_ITEMID);
		ps.setInt(1, itemId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			itemDetailBean = getItemData(rs);
		}
		DBManager.close(con, ps);
		return itemDetailBean;
	}

	/**
	 * 商品名の重複をチェックするために名前で検索しIDを取得する
	 *
	 * @param name 商品名
	 * @return 商品ID
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static Integer findOneByName(String name) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Integer id = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ITEMS_BY_NAME);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			id = rs.getInt("id");
		}
		DBManager.close(con, ps);
		return id;
	}

	/**
	 * 商品情報の件数を取得する
	 *
	 * @return 商品情報の件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static Integer getItemsCount() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Integer count = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_COUNT_ITEMS);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		DBManager.close(con, ps);
		return count;
	}

	/**
	 * 商品情報の追加
	 *
	 * @param item 追加する商品情報
	 * @return 追加件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int insert(Item item) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_INSERT_ITEM);
		ps.setString(1, item.getName());
		ps.setInt(2, item.getPrice());
		ps.setString(3, item.getDescription());
		ps.setInt(4, item.getStock());
		ps.setString(5, item.getImage());
		ps.setInt(6, item.getCategoryId());
		ret = ps.executeUpdate();

		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * 商品情報の変更
	 *
	 * @param item 変更する商品情報
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int update(Item item) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_UPDATE_ITEM);

		ps.setString(1, item.getName());
		ps.setInt(2, item.getPrice());
		ps.setString(3, item.getDescription());
		ps.setInt(4, item.getStock());
		ps.setString(5, item.getImage());
		ps.setInt(6, item.getCategoryId());
		ps.setInt(7, item.getId());

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * 商品在庫情報の変更
	 *
	 * @param itemId 変更する商品ID
	 * @param stock  変更する在庫数
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
//	public static int updateStock(Integer itemId, Integer stock) throws SQLException, ClassNotFoundException {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		int ret = 0;
//
//		con = DBManager.getConnection();
//		ps = con.prepareStatement(DBConstant.SQL_UPDATE_ITEM_STOCK);
//
//		ps.setInt(1, stock);
//		ps.setInt(2, itemId);
//
//		ret = ps.executeUpdate();
//		DBManager.close(con, ps);
//		return ret;
//	}

	/**
	 * 商品の削除(削除フラグを立てる)
	 *
	 * @param itemId 商品ID
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int delete(String itemId) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_DELETE_ITEM);

		ps.setString(1, itemId);

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * ResultSetから商品情報を取得し、 ItemBeanオブジェクトにセットする
	 *
	 * @param rs DBからの取得情報
	 * @return 商品情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static ItemBean getItemDataNoStock(ResultSet rs) throws SQLException {

		ItemBean itemBean = new ItemBean();
		itemBean.setId(rs.getInt("id"));
		itemBean.setName(rs.getString("name"));
		itemBean.setPrice(rs.getInt("price"));
		itemBean.setImage(getImageFileName(rs.getString("image")));
		itemBean.setCategoryName(rs.getString("categoryName"));

		return itemBean;
	}

	/**
	 * ResultSetから商品情報を取得し、 ItemDetailBeanオブジェクトにセットする
	 *
	 * @param rs DBからの取得情報
	 * @return 商品詳細情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static ItemDetailBean getItemData(ResultSet rs) throws SQLException {

		ItemDetailBean itemDetailBean = new ItemDetailBean();
		itemDetailBean.setId(rs.getInt("id"));
		itemDetailBean.setName(rs.getString("name"));
		itemDetailBean.setPrice(rs.getInt("price"));
		itemDetailBean.setDescription(rs.getString("description"));
		itemDetailBean.setStock(rs.getInt("stock"));
		itemDetailBean.setImage(getImageFileName(rs.getString("image")));
		itemDetailBean.setCategoryId(rs.getInt("categoryId"));
		itemDetailBean.setCategoryName(rs.getString("categoryName"));

		return itemDetailBean;
	}

	/**
	 * 画像ファイル名がnullもしくは空の場合、画像ファイルがない場合のファイル名を指定
	 * 
	 * @param fileName 画像ファイル名
	 * @return 画像ファイル名
	 */
	private static String getImageFileName(String fileName) {
		String retFileName = fileName;
		if (fileName == null) {
			retFileName = Constant.FILENAME_NOIMAGE;
		} else if (fileName.length() == 0) {
			retFileName = Constant.FILENAME_NOIMAGE;
		}
		return retFileName;
	}
}
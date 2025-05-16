package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.constant.DBConstant;
import jp.co.sss.shop.dto.Category;

/**
 * カテゴリ情報テーブルを操作する
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class CategoryDao {

	/**
	 * カテゴリ情報一覧を取得する
	 *
	 * @return カテゴリ情報一覧
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<CategoryBean> findAll() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<CategoryBean> categoryList = new ArrayList<>();

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_CATEGORIES_ORDER_BY_INSERT_DATE);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			categoryList.add(getCategoryData(rs));
		}
		DBManager.close(con, ps);
		return categoryList;
	}

	/**
	 * カテゴリIdに該当する情報を1件だけ取得する
	 *
	 * @param categoryId カテゴリID
	 * @return カテゴリ情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static CategoryBean findOneByCategoryId(String categoryId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		CategoryBean category = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_CATEGORIES_BY_ID);
		ps.setString(1, categoryId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			category = getCategoryData(rs);
		}
		DBManager.close(con, ps);
		return category;
	}

	/**
	 * カテゴリIdに該当するカテゴリ名を1件だけ取得する 商品変更確認画面で利用
	 *
	 * @param categoryId カテゴリID
	 * @return カテゴリ名
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static String findOneNameByCategoryId(String categoryId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String categoryName = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_CATEGORIES_BY_ID);
		ps.setString(1, categoryId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			categoryName = rs.getString("name");
		}
		DBManager.close(con, ps);
		return categoryName;
	}

	/**
	 * カテゴリ名の重複をチェックするために名前で検索しIDを取得する
	 *
	 * @param name 入力されたカテゴリ名
	 * @return カテゴリID
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static Integer findOneByName(String name) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Integer id = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_CATEGORIES_BY_NAME);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			id = rs.getInt("id");
		}
		DBManager.close(con, ps);
		return id;
	}

	/**
	 * カテゴリ情報の件数を取得する
	 *
	 * @return カテゴリ情報の件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static Integer getCategoriesCount() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Integer count = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_COUNT_CATEGORIES);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		DBManager.close(con, ps);
		return count;
	}

	/**
	 * カテゴリ情報の追加
	 *
	 * @param category 追加するカテゴリ情報
	 * @return 処理されたレコード件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int insert(Category category) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_INSERT_CATEGORY);
		ps.setString(1, category.getName());
		ps.setString(2, category.getDescription());
		ret = ps.executeUpdate();

		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * カテゴリ情報の変更
	 *
	 * @param category 変更するカテゴリ情報
	 * @return 処理されたレコード件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int update(Category category) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_UPDATE_CATEGORY);

		ps.setString(1, category.getName());
		ps.setString(2, category.getDescription());
		ps.setInt(3, category.getId());

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;

	}

	/**
	 * カテゴリの削除(削除フラグを立てる)
	 *
	 * @param categoryId カテゴリID
	 * @return 処理されたレコード件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー *
	 */
	public static int delete(String categoryId) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_DELETE_CATEGORY);

		ps.setString(1, categoryId);

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * ResultSetからカテゴリ情報を取得し、Categoryオブジェクトにセットする
	 *
	 * @param rs データベースからの取得情報
	 * @return カテゴリ情報
	 * @throws SQLException SQL発行時のエラー
	 */
	private static CategoryBean getCategoryData(ResultSet rs) throws SQLException {

		CategoryBean category = new CategoryBean();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setDescription(rs.getString("description"));

		return category;
	}

}
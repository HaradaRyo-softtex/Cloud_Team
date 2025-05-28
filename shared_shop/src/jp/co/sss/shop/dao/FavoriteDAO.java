package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.ItemBean;

/**
 * FavoriteDAOクラス
 * 
 * ユーザーのお気に入り情報を操作するためのDAOクラス。
 * DB上のfavoritesテーブルに対し、お気に入りの登録、
 * 解除、登録状況の確認を行う。
 */
public class FavoriteDAO {

	/**
	 * 指定ユーザーが指定商品の「お気に入り」に登録しているか判定する。
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 * @return お気に入り登録されていればtrue、そうでなければfalse
	 */
	public boolean isFavorite(int userId, int itemId) {
		try (Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT COUNT(*) FROM favorites WHERE user_id = ? AND item_id = ?")) {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 指定ユーザーのお気に入りに指定商品を登録する。
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 */
	public void addFavorite(int userId, int itemId) {
		try (Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO favorites (id, user_id, item_id) VALUES (seq_favorites.NEXTVAL, ?, ?)")) {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定ユーザーのお気に入りから指定商品を解除する。
	 * 
	 * @param userId ユーザーID
	 * @param itemId 商品ID
	 */
	public void removeFavorite(int userId, int itemId) {
		try (Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"DELETE FROM favorites WHERE user_id = ? AND item_id = ?")) {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定ユーザーのお気に入り商品一覧を取得する。
	 * 
	 * @param userId ユーザーID
	 * @return お気に入り商品リスト
	 */
	public List<ItemBean> findByUserId(int userId) {
	    List<ItemBean> favoriteItems = new ArrayList<>();
	    try (Connection con = DBManager.getConnection();
	         PreparedStatement ps = con.prepareStatement(
	             "SELECT i.id, i.name, i.price, i.image, c.name AS category_name " +
	             "FROM favorites f " +
	             "JOIN items i ON f.item_id = i.id " +
	             "JOIN categories c ON i.category_id = c.id " +
	             "WHERE f.user_id = ?")) {
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            ItemBean item = new ItemBean();
	            item.setId(rs.getInt("id"));
	            item.setName(rs.getString("name"));
	            item.setPrice(rs.getInt("price"));
	            item.setImage(rs.getString("image"));
	            item.setCategoryName(rs.getString("category_name"));
	            favoriteItems.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return favoriteItems;
	}

}
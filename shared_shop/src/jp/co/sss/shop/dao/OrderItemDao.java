package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.OrderItemBean;
import jp.co.sss.shop.constant.DBConstant;
import jp.co.sss.shop.dto.OrderItem;

/**
 * 注文商品テーブルの操作処理
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class OrderItemDao {

	/**
	 * 注文Idに該当する注文商品情報リストを取得する
	 * 
	 * @param orderId 注文ID
	 * @return 注文商品情報リスト
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<OrderItemBean> findByOrderId(int orderId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<OrderItemBean> orderItemBeanList = new ArrayList<>();
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ORDERITEMS_JOIN_ITEMS_BY_ORDERID);
		ps.setInt(1, orderId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			OrderItemBean orderItemBean = new OrderItemBean();
			orderItemBean.setId(rs.getInt("id"));
			orderItemBean.setName(rs.getString("name"));
			orderItemBean.setOrderNum(rs.getInt("quantity"));
			orderItemBean.setPrice(rs.getInt("price"));
			orderItemBean.setSubtotal(rs.getInt("subtotal"));
			orderItemBeanList.add(orderItemBean);
		}
		DBManager.close(con, ps);
		return orderItemBeanList;
	}

}
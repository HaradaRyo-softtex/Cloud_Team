package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.OrderBean;
import jp.co.sss.shop.bean.OrderDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.DBConstant;

/**
 * 注文情報テーブルを操作するクラス
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class OrderDao {

	/**
	 * すべての注文情報を取得する
	 *
	 * @return 注文情報リスト
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<OrderBean> findAll() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<OrderBean> orderList = new ArrayList<>();

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ORDERS_JOIN_USERS_ORDER_BY_INSERT_DATE);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			orderList.add(getOrderData(rs));
		}
		DBManager.close(con, ps);
		return orderList;
	}
	
	// 小島和也 追記 2025/5/19
	public static List<OrderBean> findAllByOrderIdIncludeUserName() 
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<OrderBean> orderList = new ArrayList<>();
		
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ORDERS_BY_USERID_ORDER_BY_INSERT_DATE);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			orderList.add(getOrderData(rs));
		}
		DBManager.close(con, ps);
		return orderList;
	}

	/**
	 * 注文Idに該当する注文情報を1件だけ取得する(管理者向け,会員氏名を含む)
	 *
	 * @param orderId 注文ID
	 * @return 注文詳細情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static OrderDetailBean findOneByOrderIdIncludeUserName(String orderId)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		OrderDetailBean orderDetailBean = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ORDERS_JOIN_USERS_BY_ORDERID);
		ps.setString(1, orderId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			orderDetailBean = getOrderDetailDataForAdmin(rs);
		}
		DBManager.close(con, ps);
		return orderDetailBean;
	}

	/**
	 * ResultSetから注文情報を取得し、 OrderBeanオブジェクトにセットする(一般会員用)
	 *
	 * @param rs DBの検索結果
	 * @return 注文情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static OrderBean getUserOrderData(ResultSet rs) throws SQLException {

		OrderBean orderBean = new OrderBean();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

		orderBean.setId(rs.getInt("id"));
		orderBean.setPayMethod(Constant.PAYMETHOD_MAP.get(rs.getInt("pay_method")));
		orderBean.setInsertDate(dateFormat.format(rs.getDate("insert_date")).toString());

		return orderBean;
	}

	/**
	 * ResultSetから注文情報を取得し、 OrderBeanオブジェクトにセットする(管理者権限用)
	 *
	 * @param rs DBの検索結果
	 * @return 注文情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static OrderBean getOrderData(ResultSet rs) throws SQLException {

		OrderBean orderBean = new OrderBean();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

		orderBean.setId(rs.getInt("id"));
		orderBean.setUserName(rs.getString("username"));
		orderBean.setPayMethod(Constant.PAYMETHOD_MAP.get(rs.getInt("pay_method")));
		orderBean.setInsertDate(dateFormat.format(rs.getDate("insert_date")).toString());

		return orderBean;
	}

	/**
	 * ResultSetから注文詳細情報を取得し、 OrderDetailBeanオブジェクトにセットする
	 *
	 * @param rs DBの検索結果
	 * @return 注文詳細情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static OrderDetailBean getOrderDetailData(ResultSet rs) throws SQLException {

		OrderDetailBean orderDetailBean = new OrderDetailBean();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

		orderDetailBean.setId(rs.getInt("id"));
		orderDetailBean.setPostalCode(rs.getString("postal_code"));
		orderDetailBean.setAddress(rs.getString("address"));
		orderDetailBean.setName(rs.getString("name"));
		orderDetailBean.setPhoneNumber(rs.getString("phone_number"));
		orderDetailBean.setPayMethod(rs.getInt("pay_method"));
		orderDetailBean.setPayMethodStr(Constant.PAYMETHOD_MAP.get(orderDetailBean.getPayMethod()));
		orderDetailBean.setInsertDate(dateFormat.format(rs.getDate("insert_date")).toString());

		return orderDetailBean;
	}

	/**
	 * ResultSetから注文情報を取得し、 OrderDetailBeanオブジェクトにセットする
	 *
	 * @param rs DBの検索結果
	 * @return 注文詳細情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static OrderDetailBean getOrderDetailDataForAdmin(ResultSet rs) throws SQLException {

		OrderDetailBean orderDetailBean = new OrderDetailBean();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

		orderDetailBean.setId(rs.getInt("id"));
		orderDetailBean.setUserName(rs.getString("username"));
		orderDetailBean.setPostalCode(rs.getString("postal_code"));
		orderDetailBean.setAddress(rs.getString("address"));
		orderDetailBean.setName(rs.getString("name"));
		orderDetailBean.setPhoneNumber(rs.getString("phone_number"));
		orderDetailBean.setPayMethod(rs.getInt("pay_method"));
		orderDetailBean.setPayMethodStr(Constant.PAYMETHOD_MAP.get(orderDetailBean.getPayMethod()));
		orderDetailBean.setInsertDate(dateFormat.format(rs.getDate("insert_date")).toString());

		return orderDetailBean;
	}

}
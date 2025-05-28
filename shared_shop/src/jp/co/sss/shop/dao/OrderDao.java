package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.sss.shop.bean.OrderBean;
import jp.co.sss.shop.bean.OrderDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.DBConstant;
import jp.co.sss.shop.dto.OrderDetail;
import jp.co.sss.shop.dto.OrderItem;
import jp.co.sss.shop.dto.OrdersSum;
import jp.co.sss.shop.form.OrderForm;

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
	// 原田 追記 0527
	public static List<OrdersSum> findAllByOrderIdIncludeUserName(int userId) 
	        throws SQLException, ClassNotFoundException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    List<OrdersSum> ordersumList = new ArrayList<>();

	    con = DBManager.getConnection();
	    String sql = "SELECT o.insert_date, o.pay_method, o.id AS order_id, SUM(oi.price * oi.quantity) AS sum "
	               + "FROM orders o "
	               + "INNER JOIN order_items oi ON o.id = oi.order_id "
	               + "WHERE o.user_id = ? "
	               + "GROUP BY o.insert_date, o.pay_method, o.id "
	               + "ORDER BY o.insert_date ASC";
	    ps = con.prepareStatement(sql);
	    ps.setInt(1, userId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        OrdersSum orderssum = new OrdersSum();
	        orderssum.setDate(rs.getDate("insert_date"));
	        orderssum.setPay_method(rs.getInt("pay_method"));
	        orderssum.setOrder_id(rs.getInt("order_id"));
	        orderssum.setSum(rs.getInt("sum"));
	        ordersumList.add(orderssum);
	    }
	    DBManager.close(con, ps);
	    return ordersumList;
	}
	
	// 小島和也　追記 5/21
	// findOrderDetails メソッドを注文IDで検索するように修正
	public static List<OrderDetail> findOrderDetails(int orderId) 
	        throws SQLException, ClassNotFoundException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    List<OrderDetail> orderDetailList = new ArrayList<>();

	    con = DBManager.getConnection();
	    // 注文IDで絞り込み
	    ps = con.prepareStatement(
	        "select insert_date, pay_method, address, postal_code, name, phone_number from orders where id = ?");
	    ps.setInt(1, orderId);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        OrderDetail orderdetail = new OrderDetail();
	        orderdetail.setInsert_date(rs.getDate("insert_date"));
	        orderdetail.setPay_method(rs.getInt("pay_method"));
	        orderdetail.setPostal_code(rs.getString("postal_code"));
	        orderdetail.setAddress(rs.getString("address"));
	        orderdetail.setName(rs.getString("name"));
	        orderdetail.setPhone_number(rs.getString("phone_number"));
	        orderDetailList.add(orderdetail);
	    }

	    DBManager.close(con, ps);
	    return orderDetailList;
	}

	public static List<OrderItem> findOrderItem(int orderid) 
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps2 = null;
		List<OrderItem> orderItemList = new ArrayList<>();
		
		con = DBManager.getConnection();
		ps2 = con.prepareStatement("select i.name, o.item_id, o.price, o.quantity, SUM(o.price*quantity) as sum from order_items o inner join items i on i.id = o.item_id where order_id = ? group by i.name, o.item_id, o.price, o.quantity");
		ps2.setInt(1, orderid);
		ResultSet rs2 = ps2.executeQuery();
		
		while (rs2.next()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(rs2.getInt("item_id"));
			orderItem.setName(rs2.getString("name"));
		orderItem.setPrice(rs2.getInt("price"));
			orderItem.setQuantity(rs2.getInt("quantity"));
			orderItem.setSum(rs2.getInt("sum"));
			
		orderItemList.add(orderItem);	
		}
		DBManager.close(con, ps2);
		return orderItemList;
	}
	
	
	public static int getOrderId(int user_id) throws SQLException,ClassNotFoundException{
		Connection con = null;
		PreparedStatement ps = null;
		int inw = 0;
		
		con = DBManager.getConnection();
		ps = con.prepareStatement("SELECT Max(id) AS orderid FROM orders WHERE user_id=?");
		
		ps.setInt(1,user_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			inw = rs.getInt("orderid");
			
		}
		DBManager.close(con, ps);
		return inw;
	}
	
	public static int insert(OrderForm orders,int user_id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int ine = 0;
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_INSART_ORDERS);
		
		ps.setString(1,orders.getPostalCode());
		ps.setString(2,orders.getAddress());
		ps.setString(3,orders.getName());
		ps.setString(4,orders.getPhoneNumber());
		ps.setInt(5,Integer.parseInt(orders.getPayMethod()));
		ps.setInt(6,user_id);
		Date date =new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		ps.setDate(7,date2);
		ine = ps.executeUpdate();
		System.out.println(ine);
		DBManager.close(con,ps);
		return ine;
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
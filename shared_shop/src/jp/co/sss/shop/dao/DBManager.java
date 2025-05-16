package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * データベースに接続するクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class DBManager {

	/** インスタンス化を禁止 */
	private DBManager() {
	}


	public static Connection getConnection() {
		Connection con = null;
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/xe_shared_shop_user");
			con = ds.getConnection();
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection con,PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

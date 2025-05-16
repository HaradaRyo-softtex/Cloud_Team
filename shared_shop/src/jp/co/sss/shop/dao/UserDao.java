package jp.co.sss.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.DBConstant;
import jp.co.sss.shop.dto.User;

/**
 * 会員情報テーブルを操作する処理
 * 
 * @author System Shared.co.,Ltd.
 *
 */
public class UserDao {
	/**
	 * 会員情報をDBから取得する(delete_flagがOnではない会員のみ)
	 *
	 * @return 会員リスト
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<UserBean> findAll() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<UserBean> userBeanList = new ArrayList<>();
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_ALL_USER);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			userBeanList.add(getUserData(rs));
		}
		DBManager.close(con, ps);

		return userBeanList;
	}

	/**
	 * 会員情報をDBから取得する(delete_flagがOnではなくシステム管理者ではない会員のみ)
	 *
	 * @return 会員リスト
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static List<UserBean> findAllNotSystemUser() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		List<UserBean> userBeanList = new ArrayList<>();

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_USER_NOT_AUTH_SYSTEM);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			userBeanList.add(getUserData(rs));
		}
		DBManager.close(con, ps);
		return userBeanList;
	}

	/**
	 * 会員情報をemailとパスワードから検索
	 *
	 * @param email  メールアドレス
	 * @param passwd パスワード
	 * @return 会員情報 見つからない場合はNull
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static UserBean findByEmailAndPasswd(String email, String passwd)
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		UserBean userBean = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_BY_EMAIL_AND_PASSWD);
		ps.setString(1, email);
		ps.setString(2, passwd);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			userBean = getUserData(rs);
		}
		DBManager.close(con, ps);
		return userBean;
	}

	/**
	 * Idによる会員情報の検索
	 *
	 * @param userId 会員ID(文字列)
	 * @return 会員情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static UserDetailBean findOneByUserId(String userId) throws SQLException, ClassNotFoundException {
		return findOneByUserId(Integer.parseInt(userId));
	}

	/**
	 * Idによる会員情報の検索
	 *
	 * @param userId 会員ID(数値)
	 * @return 会員情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static UserDetailBean findOneByUserId(Integer userId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		UserDetailBean userDetailBean = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_BY_USERID);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			userDetailBean = getUserDetailData(rs);
		}
		DBManager.close(con, ps);
		return userDetailBean;
	}

	/**
	 * emailによる会員情報の検索
	 *
	 * @param email メールアドレス
	 * @return 会員情報
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static UserBean findOneByEmail(String email) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		UserBean userBean = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_BY_EMAIL);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			userBean = getUserData(rs);
		}
		DBManager.close(con, ps);
		return userBean;
	}

	/**
	 * 会員情報の件数を取得する
	 *
	 * @return 会員情報の件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static Integer getUsersCount() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Integer count = null;

		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_SELECT_COUNT_USERS);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		DBManager.close(con, ps);
		return count;
	}

	/**
	 * 会員情報の追加
	 *
	 * @param user 追加する会員情報
	 * @return 登録件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int insert(User user) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_INSERT_USER);
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());
		ps.setString(4, user.getPostalCode());
		ps.setString(5, user.getAddress());
		ps.setString(6, user.getPhoneNumber());
		ps.setInt(7, user.getAuthority());
		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * 会員情報の変更
	 *
	 * @param user 変更する会員情報
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int update(User user) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_UPDATE_USER);

		ps.setString(1, user.getEmail());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());
		ps.setString(4, user.getPostalCode());
		ps.setString(5, user.getAddress());
		ps.setString(6, user.getPhoneNumber());
		ps.setInt(7, user.getAuthority());
		ps.setInt(8, user.getId());

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;

	}

	/**
	 * 会員の削除(削除フラグを立てる)
	 *
	 * @param userId 会員ID(文字列)
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int delete(String userId) throws ClassNotFoundException, SQLException {
		return delete(Integer.parseInt(userId));
	}

	/**
	 * 会員の削除(削除フラグを立てる)
	 *
	 * @param userId 会員ID(数値)
	 * @return 変更件数
	 * @throws SQLException           DB接続、SQL発行時のエラー
	 * @throws ClassNotFoundException DB接続準備時のエラー
	 */
	public static int delete(Integer userId) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int ret = 0;
		con = DBManager.getConnection();
		ps = con.prepareStatement(DBConstant.SQL_DELETE_USER);

		ps.setInt(1, userId);

		ret = ps.executeUpdate();
		DBManager.close(con, ps);
		return ret;
	}

	/**
	 * ResultSetから会員情報を取得し、Userオブジェクトにセットする
	 *
	 * @param rs DBからの取得情報
	 * @return 会員情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static UserBean getUserData(ResultSet rs) throws SQLException {

		UserBean userBean = new UserBean();

		userBean.setId(rs.getInt("id"));
		userBean.setName(rs.getString("name"));
		userBean.setEmail(rs.getString("email"));
		userBean.setAuthority(rs.getInt("authority"));

		return userBean;
	}

	/**
	 * ResultSetから会員情報を取得し、Userオブジェクトにセットする
	 *
	 * @param rs DBからの取得情報
	 * @return 会員情報
	 * @throws SQLException DB接続、SQL発行時のエラー
	 */
	private static UserDetailBean getUserDetailData(ResultSet rs) throws SQLException {

		UserDetailBean userDetailBean = new UserDetailBean();

		userDetailBean.setId(rs.getInt("id"));
		userDetailBean.setEmail(rs.getString("email"));
		userDetailBean.setName(rs.getString("name"));
		userDetailBean.setPostalCode(rs.getString("postal_code"));
		userDetailBean.setAddress(rs.getString("address"));
		userDetailBean.setPhoneNumber(rs.getString("phone_number"));
		userDetailBean.setAuthority(rs.getInt("authority"));
		userDetailBean.setAuthorityStr(Constant.AUTH_MAP.get(userDetailBean.getAuthority()));

		return userDetailBean;
	}
}
package jp.co.sss.shop.controller.admin.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;

/**
 * 会員情報一覧表示画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/user/list")
public class UserShowListAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * DBから会員情報一覧を取得し表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userForm");
		UserBean userBean = (UserBean) session.getAttribute("user");
		if (userBean == null) {
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}
		List<UserBean> userBeanList = new ArrayList<>();
		Integer count = 0;

		try {
			if (userBean.getAuthority() == Constant.AUTH_SYSTEM) {
				// ログインユーザがシステム管理者の場合、全件検索
				userBeanList = UserDao.findAll();
			} else if (userBean.getAuthority() == Constant.AUTH_ADMIN) {
				// ログインユーザが運用管理者の場合、システム管理者以外を検索
				userBeanList = UserDao.findAllNotSystemUser();
			} else {
				// それ以外の場合、エラー処理
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		try {
			count = UserDao.getUsersCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}

		// 会員情報(削除済み含む)が最大件数の場合、登録ボタンを非表示にするために件数をＶｉｅｗに渡す
		request.setAttribute("count", count);
		request.setAttribute("userBeanList", userBeanList);
		request.getRequestDispatcher("/jsp/admin/user/list.jsp").forward(request, response);
	}

}

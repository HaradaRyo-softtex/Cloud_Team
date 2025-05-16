package jp.co.sss.shop.controller.admin.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.validator.IdValid;

/**
 * 会員情報詳細画面用コントローラ(管理者用)
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/user/detail")
public class UserShowDetailAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * DBから表示対象の情報を取得し、詳細画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userForm");
		String id = request.getParameter("id");

		// idの検査
		if (IdValid.isNOTCorrectUserId(id)) {
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}
		UserDetailBean userDetailBean = null;
		try {
			userDetailBean = UserDao.findOneByUserId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (userDetailBean != null) {
			request.setAttribute("userDetailBean", userDetailBean);
			request.getRequestDispatcher("/jsp/admin/user/detail.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		}
	}
}

package jp.co.sss.shop.controller.admin.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.form.UserForm;
import jp.co.sss.shop.util.BeanCopy;

/**
 * 会員情報削除確認画面表示処理用コントローラ（管理者用）
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/user/delete/confirm")
public class UserDeleteConfirmAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 削除対象の情報をDBから取得しViewに渡す
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDetailBean userDetailBean = null;
		UserForm userForm = null;

		try {
			// DBから会員情報を取得する
			userDetailBean = UserDao.findOneByUserId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (userDetailBean == null) {
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			// 正常な場合
			userForm = BeanCopy.copyDetailBeanToForm(userDetailBean);
			request.setAttribute("userForm", userForm);
			request.getRequestDispatcher("/jsp/admin/user/delete_confirm.jsp").forward(request, response);
		}
	}

}

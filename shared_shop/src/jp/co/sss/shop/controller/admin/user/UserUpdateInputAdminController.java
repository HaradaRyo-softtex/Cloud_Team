package jp.co.sss.shop.controller.admin.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import jp.co.sss.shop.form.UserForm;
import jp.co.sss.shop.util.BeanCopy;

import jp.co.sss.shop.validator.UserInputValid;

/**
 * 会員情報変更入力画面用コントローラ(管理者用)
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/user/update/input")
public class UserUpdateInputAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 会員情報変更入力画面の表示、入力値の受付とチェック、戻るボタン後の入力画面表示を行う
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String backflg = request.getParameter("backflg");
		HttpSession session = request.getSession();
		UserForm userForm = null;

		if (backflg == null) {
			// 会員詳細、会員一覧からの遷移
			String id = request.getParameter("id");
			UserDetailBean userDetailBean = null;
			try {
				// DBから会員情報を取得する
				userDetailBean = UserDao.findOneByUserId(id);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (userDetailBean == null) {
				response.sendRedirect(
						request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
				return;
			}
			userForm = BeanCopy.copyDetailBeanToForm(userDetailBean);
			request.setAttribute("userForm", userForm);
			// セッションスコープから削除
			session.removeAttribute("userForm");
			request.getRequestDispatcher("/jsp/admin/user/update_input.jsp").forward(request, response);
		} else if (backflg.equals(Constant.BACKFLG_ON)) {
			// 会員変更確認画面で「戻る」ボタンが押された時
			userForm = (UserForm) session.getAttribute("userForm");
			if (userForm == null) {
				// セッションスコープに情報がない場合システムエラーとする
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
			}
			// 会員変更入力画面を表示
			request.getRequestDispatcher("/jsp/admin/user/update_input.jsp").forward(request, response);

		} else if (backflg.equals(Constant.BACKFLG_OFF)) {

			// 会員変更入力画面で「確認」ボタンが押された時
			// 入力された値を取得
			userForm = new UserForm();
			userForm.setId(request.getParameter("id"));
			userForm.setEmail(request.getParameter("email"));
			userForm.setPassword(request.getParameter("password"));
			userForm.setName(request.getParameter("name"));
			userForm.setPostalCode(request.getParameter("postalCode"));
			userForm.setAddress(request.getParameter("address"));
			userForm.setPhoneNumber(request.getParameter("phoneNumber"));
			String authority = request.getParameter("authority");
			userForm.setAuthority(authority);
			// 表示するための文字列を生成
			userForm.setAuthorityStr(Constant.AUTH_MAP.get(Integer.parseInt(authority)));

			// エラーチェック
			List<String> errorMessageList = null;
			try {
				errorMessageList = UserInputValid.makeInputErrorMessageList(userForm);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (errorMessageList.size() > 0) {
				// 入力値にエラーがある場合
				// リクエストスコープにセット
				request.setAttribute("userForm", userForm);
				request.setAttribute("errorMessageList", errorMessageList);
				// 会員登録入力画面を表示
				request.getRequestDispatcher("/jsp/admin/user/update_input.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合
				// セッションスコープにセット
				session.setAttribute("userForm", userForm);
				response.sendRedirect(request.getContextPath() + "/admin/user/update/confirm");
			}

		} else {
			// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}
	}

}

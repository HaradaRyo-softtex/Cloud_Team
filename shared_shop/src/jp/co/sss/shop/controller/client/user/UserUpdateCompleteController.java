
package jp.co.sss.shop.controller.client.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.dto.User;
import jp.co.sss.shop.form.UserForm;
import jp.co.sss.shop.validator.IdValid;
import jp.co.sss.shop.validator.URLValid;

@WebServlet("/user/update/complete")
public class UserUpdateCompleteController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/* 会員情報変更完了画面*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isNOTCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_CLIENT_USER_UPDATE_CONFIRM)) {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}

		String id = request.getParameter("id");
		
		// idの検査
		if (IdValid.isNOTCorrectUserId(id)) {
			
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}

		UserDetailBean user = null;
		// 遷移元が確認画面の場合
		try {
			// 変更完了したIDが有効なIDかを確認するためにDBに問い合わせ
			user = UserDao.findOneByUserId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (user == null) {
			// 情報が取得できなかった場合エラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			request.setAttribute("id", id);
			request.getRequestDispatcher("/jsp/client/user/update_complete.jsp").forward(request, response);
		}
	}

	/*会員情報の変更内容をDBに反映し、完了画面表示処理にリダイレクトする*/
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserForm userForm = (UserForm) session.getAttribute("userForm");
		if (userForm == null) {
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}

		User user = new User();
		user.setId(Integer.parseInt(userForm.getId()));
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setName(userForm.getName());
		user.setPostalCode(userForm.getPostalCode());
		user.setAddress(userForm.getAddress());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setAuthority(Integer.parseInt(userForm.getAuthority()));
		int ret = 0;
		try {
			ret = UserDao.update(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
			return;
		}

		UserBean userBean = (UserBean) session.getAttribute("user");
		if (userBean == null) {
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}

		if (userBean.getId().equals(user.getId())) {
			// 変更したユーザが現在ログインしているユーザと同じ場合セッション情報を更新
			userBean.setName(user.getName());
			userBean.setAuthority(user.getAuthority());
		}

		// 変更入力情報をセッション情報から削除
		session.removeAttribute("userForm");
		response.sendRedirect(request.getContextPath() + "/user/update/complete?id="+ user.getId());
	}

}

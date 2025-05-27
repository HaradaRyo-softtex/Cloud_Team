//会員登録完了画面
package jp.co.sss.shop.controller.client.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.dto.User;
import jp.co.sss.shop.form.UserForm;
import jp.co.sss.shop.validator.URLValid;

/**
 * Servlet implementation class UserRegistCompleteController
 */
@WebServlet("/user/regist/complete")
public class UserRegistCompleteController extends HttpServlet {
	
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/*登録処理完了後のリダイレクト先、完了画面を表示する(2重送信防止用)*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_CLIENT_USER_REGIST_CONFIRM)) {
			// 遷移元が確認画面の場合、完了画面を表示
			request.getRequestDispatcher("/jsp/client/user/regist_complete.jsp").forward(request, response);
		} else {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}

	//DBに会員情報を登録し、完了画面にリダイレクトする
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
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setName(userForm.getName());
		user.setPostalCode(userForm.getPostalCode());
		user.setAddress(userForm.getAddress());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setAuthority(2);

		int ret = 0;
		try {
			ret = UserDao.insert(user);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
		} else {
			// 正常に登録できた場合
			session.removeAttribute("userForm");
			response.sendRedirect(request.getContextPath() + "/user/regist/complete");
		}
	}

}

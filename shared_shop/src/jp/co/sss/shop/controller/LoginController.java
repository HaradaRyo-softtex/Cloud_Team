package jp.co.sss.shop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.validator.LoginValid;

/**
 * ログイン処理用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate();
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	/**
	 * ログイン画面の入力値を受け取り、ログイン可否判断後、権限に応じてリダイレクト
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		UserBean userBean = null;
		String redirectURL = "";
		boolean hasError = false;

		List<String> errorMessageList = LoginValid.makeInputErrorMessageList(email, password);
		if (errorMessageList.size() > 0) {
			// 入力値にエラーあり
			hasError = true;
		} else {
			// 入力値にエラーがなかった場合
			try {
				// DBへ問合せ
				userBean = UserDao.findByEmailAndPasswd(email, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (userBean == null) {
				// ユーザが取得できない場合ログインエラーとする
				errorMessageList.add(MSGConstant.MSG_LOGIN_EMAIL_OR_PASSWORD_MISMATCH);
				hasError = true;
			} else if (userBean.getAuthority() == Constant.AUTH_CLIENT) {
				// 一般ユーザ権限でのログイン
				// TODO 一般ユーザでログインした後のパスを作る
			} else {
				// システム管理者、運用管理者権限でのログイン
				redirectURL = request.getContextPath() + "/admin";
			}
		}

		if (hasError) {
			// 入力値にエラーあり
			session.invalidate();
			request.setAttribute("errorMessageList", errorMessageList);
			request.setAttribute("email", email);
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		} else {
			session.setAttribute("user", userBean);
			response.sendRedirect(redirectURL);
		}
	}
}

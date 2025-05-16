package jp.co.sss.shop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.URLConstant;

/**
 * ログインチェック用フィルタ
 *
 * @author System Shared.co.,Ltd.
 */
@WebFilter(urlPatterns = { URLConstant.URL_ADMIN_ALL, URLConstant.URL_CLIENT_BASKET, URLConstant.URL_CLIENT_ORDER,
		URLConstant.URL_CLIENT_USER_UPDATE, URLConstant.URL_CLIENT_USER_DETAIL,
		URLConstant.URL_CLIENT_USER_DELETE_CONFIRM, URLConstant.URL_LOGOUT })
public class LoginCheckFilter extends HttpFilter{
	/**
	 * @see HttpFilter#doFilter(ServletRequest request, ServletResponse
	 *      response,FilterChain chain)
	 */
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// セッション情報を取得
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {

			// 不正アクセスの場合、ログイン画面にリダイレクト
			// ログイン画面へリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
		} else {

			chain.doFilter(request, response);
		}
	}

}

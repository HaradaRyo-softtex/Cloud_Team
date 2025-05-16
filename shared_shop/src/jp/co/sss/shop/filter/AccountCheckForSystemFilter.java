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
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;

/**
 * システム管理者向けアクセス制限用フィルタ
 *
 * @author System Shared.co.,Ltd.
 */
@WebFilter(urlPatterns = { URLConstant.URL_ADMIN_ORDER, URLConstant.URL_ADMIN_ITEM, URLConstant.URL_ADMIN_CATEGORY })
public class AccountCheckForSystemFilter extends HttpFilter{
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

		if (user != null) {
			// ログイン情報あり

			if (user.getAuthority() != Constant.AUTH_ADMIN) {
				// セッション情報を削除
				session.invalidate();

				// ログイン画面にリダイレクト
				response.sendRedirect(request.getContextPath() + "/login");

			} else {
				chain.doFilter(request, response);
			}
		} else {
			// ログイン情報なし
			// セッション情報を削除
			session.invalidate();

			// ログイン画面にリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}

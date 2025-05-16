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
 * 一般会員向けアクセス制限用フィルタ
 *
 * @author System Shared.co.,Ltd.
 * 
 */
@WebFilter(urlPatterns = { URLConstant.URL_WELCOME, URLConstant.URL_TOP, URLConstant.URL_CLIENT_ITEM,
		URLConstant.URL_CLIENT_BASKET, URLConstant.URL_CLIENT_ORDER, URLConstant.URL_CLIENT_USER_UPDATE,
		URLConstant.URL_CLIENT_USER_DELETE_CONFIRM })
public class AccountCheckForClientFilter extends HttpFilter{
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
			if (user.getAuthority() != Constant.AUTH_CLIENT) {
				// 一般ユーザではない場合
				// レスポンス情報を取得
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				session.invalidate();
				// ログイン画面にリダイレクト
				httpResponse.sendRedirect(request.getContextPath() + "/login");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			// ログイン情報なし
			chain.doFilter(request, response);

		}
	}

}

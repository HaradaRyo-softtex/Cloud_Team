package jp.co.sss.shop.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;

/**
 * カテゴリ一覧取得用フィルター
 *
 * @author System Shared.co.,Ltd.
 */
@WebFilter(urlPatterns = { URLConstant.URL_ADMIN_ITEM, URLConstant.URL_WELCOME, URLConstant.URL_TOP,
		URLConstant.URL_CLIENT_ITEM, URLConstant.URL_CLIENT_BASKET, URLConstant.URL_CLIENT_ORDER,
		URLConstant.URL_CLIENT_USER })
public class CategoryListMakeFilter extends HttpFilter{
	/**
	 * @see HttpFilter#doFilter(ServletRequest request, ServletResponse
	 *      response,FilterChain chain)
	 */
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<CategoryBean> categoryList = null;

		// カテゴリ情報を全件検索
		try {
			categoryList = CategoryDao.findAll();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}

		// リクエストスコープに検索結果を保存
		request.setAttribute("categoryList", categoryList);
		chain.doFilter(request, response);
	}

}

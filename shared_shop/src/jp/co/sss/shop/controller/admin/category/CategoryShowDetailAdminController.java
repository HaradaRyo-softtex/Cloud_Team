package jp.co.sss.shop.controller.admin.category;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.validator.IdValid;

/**
 * カテゴリ情報詳細画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/detail")
public class CategoryShowDetailAdminController extends HttpServlet {
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
		session.removeAttribute("categoryForm");
		String id = request.getParameter("id");

		// idの検査 問題ありのときTrue
		if (IdValid.isNOTCorrectCategoryId(id)) {
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}
		CategoryBean category = null;

		try {
			category = CategoryDao.findOneByCategoryId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (category == null) {
			// カテゴリ情報が取得できなかった場合エラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			return;
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/jsp/admin/category/detail.jsp").forward(request, response);
	}

}

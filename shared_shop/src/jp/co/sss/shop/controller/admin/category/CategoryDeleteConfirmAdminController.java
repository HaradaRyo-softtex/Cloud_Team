package jp.co.sss.shop.controller.admin.category;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;

/**
 * カテゴリ情報削除確認画面表示用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/delete/confirm")
public class CategoryDeleteConfirmAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * DBから削除対象のカテゴリ情報を取得しViewに受け渡す
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		CategoryBean category = null;

		try {
			// DBからカテゴリ情報を取得する
			category = CategoryDao.findOneByCategoryId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (category == null) {
			// カテゴリ情報がDBから取得できなかった場合エラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			return;
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/jsp/admin/category/delete_confirm.jsp").forward(request, response);
	}

}

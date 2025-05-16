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
import jp.co.sss.shop.dto.Category;
import jp.co.sss.shop.form.CategoryForm;
import jp.co.sss.shop.validator.IdValid;
import jp.co.sss.shop.validator.URLValid;

/**
 * カテゴリ情報変更完了画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/update/complete")
public class CategoryUpdateCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * カテゴリ情報変更完了画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1つ前のURL情報を取得しチェック
		if (URLValid.isNOTCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_CATEGORY_UPDATE_CONFIRM)) {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}
		// 遷移元が確認画面の場合
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
			// 変更完了したカテゴリIDが有効なIDかを確認するためにDBに問い合わせ
			category = CategoryDao.findOneByCategoryId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (category == null) {
			// カテゴリ情報が取得できなかった場合エラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			request.setAttribute("id", id);
			request.getRequestDispatcher("/jsp/admin/category/update_complete.jsp").forward(request, response);
		}
	}

	/**
	 * カテゴリ情報の変更内容をDBに反映し、完了画面表示処理にリダイレクトする
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CategoryForm categoryForm = (CategoryForm) session.getAttribute("categoryForm");
		if (categoryForm == null) {
			// セッションスコープに情報がない
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}
		Category category = new Category();
		category.setId(Integer.parseInt(categoryForm.getId()));
		category.setName(categoryForm.getName());
		category.setDescription(categoryForm.getDescription());
		int ret = 0;
		try {
			ret = CategoryDao.update(category);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			// 更新件数が0件の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
			return;
		}

		session.removeAttribute("categoryForm");
		response.sendRedirect(request.getContextPath() + "/admin/category/update/complete?id=" + category.getId());
	}

}

package jp.co.sss.shop.controller.admin.category;

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
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.dto.Category;
import jp.co.sss.shop.form.CategoryForm;
import jp.co.sss.shop.validator.URLValid;

/**
 * カテゴリ情報登録完了用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/regist/complete")
public class CategoryRegistCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 登録処理完了後のリダイレクト先、完了画面を表示する(2重送信防止用)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_CATEGORY_REGIST_CONFIRM)) {
			// 遷移元が確認画面の場合、完了画面を表示
			request.getRequestDispatcher("/jsp/admin/category/regist_complete.jsp").forward(request, response);
		} else {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}
	}

	/**
	 * DBにカテゴリ情報を登録し、完了画面にリダイレクトする
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
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}
		int ret = 0;

		Category category = new Category();
		category.setName(categoryForm.getName());
		category.setDescription(categoryForm.getDescription());

		try {
			ret = CategoryDao.insert(category);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			// 登録件数が0の場合エラー扱いにする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			// 正常に登録できた場合
			session.removeAttribute("categoryForm");
			response.sendRedirect(request.getContextPath() + "/admin/category/regist/complete");
		}
	}

}

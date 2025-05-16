package jp.co.sss.shop.controller.admin.category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import jp.co.sss.shop.form.CategoryForm;
import jp.co.sss.shop.util.BeanCopy;

import jp.co.sss.shop.validator.CategoryInputValid;

/**
 * カテゴリ情報変更入力画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/update/input")
public class CategoryUpdateInputAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * カテゴリ情報変更入力画面の表示、入力値の受付とチェック、戻るボタン後の入力画面表示を行う
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String backflg = request.getParameter("backflg");
		HttpSession session = request.getSession();
		CategoryForm categoryForm = null;

		if (backflg == null) {
			// カテゴリ詳細、カテゴリ一覧からの遷移
			String id = request.getParameter("id");
			CategoryBean categoryBean = null;
			try {
				// DBからカテゴリ情報を取得する
				categoryBean = CategoryDao.findOneByCategoryId(id);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (categoryBean == null) {
				// カテゴリ情報が取得できなかった場合エラーとする
				response.sendRedirect(
						request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
				return;
			}
			categoryForm = BeanCopy.copyDetailBeanToForm(categoryBean);
			session.setAttribute("categoryForm", categoryForm);

			request.getRequestDispatcher("/jsp/admin/category/update_input.jsp").forward(request, response);
		} else if (backflg.equals(Constant.BACKFLG_ON)) {
			// 変更確認画面で「戻る」ボタンが押された時
			// セッション情報から入力値を取り出す
			categoryForm = (CategoryForm) session.getAttribute("categoryForm");
			if (categoryForm == null) {
				// セッションスコープに情報がない場合システムエラーとする
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
			}
			// 変更入力画面を表示
			request.getRequestDispatcher("/jsp/admin/category/update_input.jsp").forward(request, response);

		} else if (backflg.equals(Constant.BACKFLG_OFF)) {

			// 変更入力画面で「確認」ボタンが押された時
			// 入力された値を取得
			categoryForm = new CategoryForm();
			categoryForm.setId(request.getParameter("id"));
			categoryForm.setName(request.getParameter("name"));
			categoryForm.setDescription(request.getParameter("description"));

			// エラーチェック
			List<String> errorMessageList = null;
			try {
				errorMessageList = CategoryInputValid.makeInputErrorMessageList(categoryForm);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (errorMessageList.size() > 0) {
				// 入力値にエラーがある場合
				// リクエストスコープにセット
				request.setAttribute("categoryForm", categoryForm);
				request.setAttribute("errorMessageList", errorMessageList);
				// 登録入力画面を表示
				request.getRequestDispatcher("/jsp/admin/category/update_input.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合

				// セッションスコープにセット
				session.setAttribute("categoryForm", categoryForm);
				response.sendRedirect(request.getContextPath() + "/admin/category/update/confirm");
			}

		} else {
			// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}

	}

}

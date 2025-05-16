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

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.form.CategoryForm;
import jp.co.sss.shop.validator.CategoryInputValid;

/**
 * カテゴリ情報登録入力画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/regist/input")
public class CategoryRegistInputAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * カテゴリ情報登録入力画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// セッション情報の初期化
		session.removeAttribute("categoryForm");

		request.getRequestDispatcher("/jsp/admin/category/regist_input.jsp").forward(request, response);

	}

	/**
	 * カテゴリ情報登録入力画面で入力された値を取得し入力チェックを行う カテゴリ情報確認画面から戻るボタンで遷移した場合にカテゴリ情報登録入力画面を表示する
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 戻るボタンの遷移かどうかを判断するフラグ情報を取得
		String backflg = request.getParameter("backflg");
		HttpSession session = request.getSession();
		CategoryForm categoryForm = null;

		if (backflg != null && backflg.equals(Constant.BACKFLG_OFF)) {
			// 登録入力画面で「確認」ボタンが押された時
			// 入力された値を取得
			categoryForm = new CategoryForm();
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
				request.getRequestDispatcher("/jsp/admin/category/regist_input.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合
				// セッションスコープにセット
				session.setAttribute("categoryForm", categoryForm);
				response.sendRedirect(request.getContextPath() + "/admin/category/regist/confirm");
			}

		} else if (backflg != null && backflg.equals(Constant.BACKFLG_ON)) {
			// 登録確認画面で「戻る」ボタンが押された時
			categoryForm = (CategoryForm) session.getAttribute("categoryForm");
			if (categoryForm == null) {
				// セッションスコープに情報がない場合システムエラーとする
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
			}
			// 登録入力画面を表示
			request.getRequestDispatcher("/jsp/admin/category/regist_input.jsp").forward(request, response);

		} else {
			// 想定外の状態 backflgがnullもしくは、値が想定外
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;

		}

	}

}

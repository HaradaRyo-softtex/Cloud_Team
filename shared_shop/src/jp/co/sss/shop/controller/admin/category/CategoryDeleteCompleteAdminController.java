package jp.co.sss.shop.controller.admin.category;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.validator.URLValid;

/**
 * カテゴリ情報削除完了処理用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/delete/complete")
public class CategoryDeleteCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 削除処理完了後のリダイレクト先、完了画面を表示する(2重送信防止用)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1つ前のURL情報を取得しチェック
		if (URLValid.isCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_CATEGORY_DELETE_CONFIRM)) {
			// 遷移元が削除確認画面の場合、完了画面を表示
			request.getRequestDispatcher("/jsp/admin/category/delete_complete.jsp").forward(request, response);
		} else {
			// 遷移元が削除完了画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}
	}

	/**
	 * カテゴリ情報の削除処理を呼び、完了画面にリダイレクトする
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int ret = 0;

		// 1つ前のURL情報を取得しチェック
		if (URLValid.isNOTCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_CATEGORY_DELETE_CONFIRM)) {
			// 遷移元が削除確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}
		// 遷移元が削除確認画面の場合
		try {
			ret = CategoryDao.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			// DB操作で更新できた件数が0件の場合エラーと判断する
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/category/delete/complete");
		}
	}

}

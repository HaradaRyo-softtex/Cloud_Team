package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.validator.URLValid;

/**
 * 商品情報削除完了処理用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/delete/complete")
public class ItemDeleteCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品情報削除完了画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_ITEM_DELETE_CONFIRM)) {
			// 遷移元が確認画面の場合、完了画面を表示する
			request.getRequestDispatcher("/jsp/admin/item/delete_complete.jsp").forward(request, response);
		} else {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}

	/**
	 * 商品削除の削除処理を呼び完了画面にリダイレクトする
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isNOTCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_ITEM_DELETE_CONFIRM)) {
			// 遷移元が削除確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}
		int ret = 0;
		try {
			ret = ItemDao.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
		} else {
			// 更新失敗
			response.sendRedirect(request.getContextPath() + "/admin/item/delete/complete");
		}

	}

}

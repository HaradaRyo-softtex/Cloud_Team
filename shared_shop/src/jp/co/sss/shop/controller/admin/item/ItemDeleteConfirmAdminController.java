package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;

/**
 * 商品情報削除確認画面表示処理用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/delete/confirm")
public class ItemDeleteConfirmAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 削除対象の情報をDBから取得しViewに渡す
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ItemDetailBean itemDetailBean = null;

		try {
			// DBから商品情報を取得する
			itemDetailBean = ItemDao.findOneByItemId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (itemDetailBean == null) {
			// 商品情報がない場合エラー
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			// 商品情報がある場合
			request.setAttribute("itemDetailBean", itemDetailBean);
			request.getRequestDispatcher("/jsp/admin/item/delete_confirm.jsp").forward(request, response);
		}
	}

}

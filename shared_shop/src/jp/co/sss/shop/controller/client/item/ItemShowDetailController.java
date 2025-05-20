package jp.co.sss.shop.controller.client.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.validator.IdValid;

/**
 * Servlet implementation class ItemShowDetailController
 */
@WebServlet("/item/detail")
public class ItemShowDetailController extends HttpServlet {
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
		session.removeAttribute("itemForm");
		String id = request.getParameter("id");

		// idの検査 問題ありのときTrue
		if (IdValid.isNOTCorrectItemId(id)) {
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}

		ItemDetailBean itemDetailBean = null;

		try {
			itemDetailBean = ItemDao.findOneByItemId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (itemDetailBean == null) {
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
		} else {
			request.setAttribute("itemDetailBean", itemDetailBean);
			request.getRequestDispatcher("/jsp/client/item/detail.jsp").forward(request, response);
		}
	}

}

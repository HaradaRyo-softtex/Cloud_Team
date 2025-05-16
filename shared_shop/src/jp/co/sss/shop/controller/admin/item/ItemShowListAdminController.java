package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;

/**
 * 商品情報一覧表示画面用コントローラ(運用管理者用)
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/list")
public class ItemShowListAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * DBから商品情報一覧を取得し表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("itemForm");
		List<ItemBean> itemBeanList = new ArrayList<>();
		Integer count = 0;

		try {
			itemBeanList = ItemDao.findAllForAdmin();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		// 商品情報のレコード数を取得
		try {
			count = ItemDao.getItemsCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		// 商品情報が最大件数の場合、登録ボタンを非表示にするために件数をＶｉｅｗに渡す
		request.setAttribute("count", count);
		request.setAttribute("itemBeanList", itemBeanList);
		request.getRequestDispatcher("/jsp/admin/item/list.jsp").forward(request, response);
	}

}

package jp.co.sss.shop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.dao.OrderDao;

/**
 * トップ画面表示用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/top")
public class IndexController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 注文件数の有無でsortTypeをセットし、表示内容をDBから取得する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ItemBean> itemBeanList = new ArrayList<>();
		String sortType = Constant.SORT_LATEST;

		try {
			// TODO 売れ筋情報があるかを判別し、sortTypeを変更する
			// 過去の注文件数が1件以上あれば、並び替え順に売れ筋順をセット
			itemBeanList = ItemDao.findAll(sortType);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		request.setAttribute("sortType", sortType);
		request.setAttribute("itemBeanList", itemBeanList);
		request.getRequestDispatcher("/jsp/top.jsp").forward(request, response);

	}

}

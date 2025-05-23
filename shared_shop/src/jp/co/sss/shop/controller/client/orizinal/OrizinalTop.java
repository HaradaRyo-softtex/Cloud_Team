package jp.co.sss.shop.controller.client.orizinal;

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

/**
 * 商品情報一覧表示画面用コントローラ(運用管理者用)
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/orizinal")
public class OrizinalTop extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * カテゴリID=1の商品情報のみを表示
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ItemBean> itemBeanList = new ArrayList<>();
		String sortType = Constant.SORT_LATEST;
		int categoryId = 1; // カテゴリID固定

		try {
			itemBeanList = ItemDao.findByCategoryId(categoryId, sortType);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}

		request.setAttribute("sortType", sortType);
		request.setAttribute("itemBeanList", itemBeanList);
		request.setAttribute("categoryId", categoryId); // 画面で使う場合に備え追加
		request.getRequestDispatcher("/jsp/client/orizinal/vegetable.jsp").forward(request, response);
	}
}
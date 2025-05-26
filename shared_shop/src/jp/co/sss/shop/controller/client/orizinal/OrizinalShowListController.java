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
@WebServlet("/orizinal/list")
public class OrizinalShowListController extends HttpServlet {
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
		//ソート
		String sortType = request.getParameter("sortType");
		if (sortType == null || sortType.isEmpty()) {
	        sortType = Constant.SORT_LATEST; // デフォルトで新着順
	    }
		//カテゴリ検索
		String categoryIdStr = request.getParameter("categoryId");
		if (categoryIdStr == null) {
	        categoryIdStr = Constant.CATEGORY_SELECT_NONE_NO; // デフォルトで新着順
	    }
		 int categoryId = Integer.parseInt(categoryIdStr);
		 String seasonType = request.getParameter("seasonType");


		//商品表示
		try {
			if("1".equals(seasonType)) {
				if(categoryId == 0) {
					itemBeanList = ItemDao.findBySeasonType(sortType, seasonType);
				}else {
					itemBeanList = ItemDao.findByCategoryId(categoryId, sortType);
				}
			}else if("2".equals(seasonType)){
				if(categoryId == 0) {
					itemBeanList = ItemDao.findBySeasonType(sortType, seasonType);
				}else {
					itemBeanList = ItemDao.findByCategoryId(categoryId, sortType);
				}	
			}else {
				if(categoryId == 0) {
					itemBeanList = ItemDao.findAll(sortType);
				}else {
					itemBeanList = ItemDao.findByCategoryId(categoryId, sortType);
			}
		}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		
		// 商品情報のレコード数を取得
		try {
			if(categoryId == 0) {
			count = ItemDao.getItemsCount();
			}else {
				count = ItemDao.getItemsCountByCategoryId(categoryId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		// 商品情報が最大件数の場合、登録ボタンを非表示にするために件数をＶｉｅｗに渡す
		request.setAttribute("count", count);
		request.setAttribute("itemBeanList", itemBeanList);
		//sortTypeを切り替えるための値
		request.setAttribute("sortType", sortType);
		//categoryIdの値を受け渡す
		request.setAttribute("categoryId", categoryId);
		//seasonTypeの値を受け渡す
		request.setAttribute("seasonType", seasonType);
		request.getRequestDispatcher("/jsp/client/orizinal/list.jsp").forward(request, response);
	}

}
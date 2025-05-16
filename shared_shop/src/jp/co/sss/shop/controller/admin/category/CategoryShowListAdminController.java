package jp.co.sss.shop.controller.admin.category;

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

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;

/**
 * カテゴリ情報一覧表示画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/category/list")
public class CategoryShowListAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * DBからカテゴリ情報一覧を取得し表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CategoryBean> categoryList = new ArrayList<>();
		Integer count = 0;
		HttpSession session = request.getSession();
		session.removeAttribute("categoryForm");
		try {
			categoryList = CategoryDao.findAll();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (categoryList != null && categoryList.size() > 0) {
			// カテゴリ情報のレコード数を取得
			try {
				count = CategoryDao.getCategoriesCount();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
		}
		request.setAttribute("categoryList", categoryList);
		// カテゴリ情報が99件の場合、登録ボタンを非表示にするために件数をＶｉｅｗに渡す
		request.setAttribute("count", count);
		request.getRequestDispatcher("/jsp/admin/category/list.jsp").forward(request, response);

	}

}

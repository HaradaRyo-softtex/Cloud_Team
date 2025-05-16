package jp.co.sss.shop.controller.admin.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.OrderDetailBean;
import jp.co.sss.shop.bean.OrderItemBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dao.OrderItemDao;
import jp.co.sss.shop.util.PriceCalc;
import jp.co.sss.shop.validator.IdValid;

/**
 * 注文情報詳細画面用コントローラ(運用管理者用)
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/order/detail")
public class OrderShowDetailAdminController extends HttpServlet {
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
		String id = request.getParameter("id");

		// IDのチェック 不正な場合True
		if (IdValid.isNOTCorrectOrderId(id)) {
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}

		// 選択された注文情報に該当する情報を取得
		OrderDetailBean orderDetailBean = null;
		List<OrderItemBean> orderItemBeanList = null;
		try {
			orderDetailBean = OrderDao.findOneByOrderIdIncludeUserName(id);
			if (orderDetailBean == null) {
				response.sendRedirect(
						request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
				return;
			}
			// 注文商品情報を取得
			orderItemBeanList = OrderItemDao.findByOrderId(orderDetailBean.getId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (orderItemBeanList == null || orderItemBeanList.size() == 0) {
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			return;
		}

		// 合計金額を算出
		orderDetailBean.setTotal(PriceCalc.orderItemPriceTotal(orderItemBeanList));

		// 注文情報をViewへ渡す
		request.setAttribute("orderDetailBean", orderDetailBean);
		request.setAttribute("orderItemBeanList", orderItemBeanList);
		request.getRequestDispatcher("/jsp/admin/order/detail.jsp").forward(request, response);

	}

}

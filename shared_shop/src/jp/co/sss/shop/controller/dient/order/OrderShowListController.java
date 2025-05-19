package jp.co.sss.shop.controller.dient.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.bean.OrderBean;
import jp.co.sss.shop.bean.OrderItemBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dao.OrderItemDao;

/**
 * Servlet implementation class OrderShowListController
 */
@WebServlet("/order/list")
public class OrderShowListController extends HttpServlet {
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
		List<OrderBean> orderBeanList = new ArrayList<>();
		
		try {
			List<OrderBean> orderList = OrderDao.findAllByOrderIdIncludeUserName();

			for (OrderBean orderBean : orderList) {

				// 注文時点の単価を合計するための一時変数
				int total = 0;

				// orderレコードから紐づくOrderItemのListを取り出す
				List<OrderItemBean> orderItemBeanList = OrderItemDao.findByOrderId(orderBean.getId());
				if (orderItemBeanList == null || orderItemBeanList.size() == 0) {
					response.sendRedirect(
							request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
					return;
				}
				for (OrderItemBean orderItemBean : orderItemBeanList) {

					// 小計(購入時単価 * 買った個数)を、合計に加算
					total += orderItemBean.getSubtotal();
				}
				// Orderに改めて注文時点の単価をセット
				orderBean.setTotal(total);
				orderBeanList.add(orderBean);
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

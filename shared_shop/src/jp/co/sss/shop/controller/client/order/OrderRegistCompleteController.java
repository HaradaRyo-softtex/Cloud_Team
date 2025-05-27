package jp.co.sss.shop.controller.client.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.BasketBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.form.OrderForm;

/**
 * Servlet implementation class OrderRegistCompleteController
 */
@WebServlet("/order/regist/complete")
public class OrderRegistCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/client/order/complete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			
			OrderForm orderForm = (OrderForm) session.getAttribute("orderform");
			UserBean user_bean = (UserBean) session.getAttribute("user");
			
				int user_id = user_bean.getId();
				OrderDao.insert(orderForm, user_id);

			int orderid = OrderDao.getOrderId(user_id);
			List<ItemDetailBean> itemDetailBeanList = (List<ItemDetailBean>)session.getAttribute("itemDetailBeanList");
			List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");
			
			//OrderItemDao.insert();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;

		}

		response.sendRedirect(request.getContextPath() + "/order/regist/complete");

	}

}

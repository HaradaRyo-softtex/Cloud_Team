package jp.co.sss.shop.controller.client.order;

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

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dto.OrderDetail;
import jp.co.sss.shop.dto.OrderItem;
/**
 * Servlet implementation class OrderShowDetailController
 */
@WebServlet("/order/detail")
public class OrderShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<OrderDetail> orderDetailsList = new ArrayList<>();
		List<OrderItem> orderItemList = new ArrayList<>();
		
		try {
			HttpSession session = request.getSession();
			UserBean user_bean =  (UserBean) session.getAttribute("user");
			int user_id = user_bean.getId();
			orderDetailsList = OrderDao.findOrderDetails(user_id);
			orderItemList = OrderDao.findOrderItem(user_id);
			System.out.println(orderItemList);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		request.setAttribute("orderDetailsList", orderDetailsList);
		request.setAttribute("orderItemList", orderItemList);
		request.getRequestDispatcher("/jsp/client/order/detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

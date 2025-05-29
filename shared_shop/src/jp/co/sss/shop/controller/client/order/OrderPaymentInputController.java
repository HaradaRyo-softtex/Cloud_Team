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

import jp.co.sss.shop.bean.BasketBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.form.OrderForm;

/**
 * Servlet implementation class OrderPaymentInputController
 */
@WebServlet("/order/payment/input")
public class OrderPaymentInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/client/order/input_payment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String backflg = request.getParameter("backflg");
		try {
			if (backflg.equals("off")) {

				HttpSession session = request.getSession();
				OrderForm orderform = (OrderForm) session.getAttribute("orderform");
				orderform.setPayMethod(request.getParameter("payMethod"));
				List<ItemDetailBean> itemDetailBeanList = (List<ItemDetailBean>) session
						.getAttribute("itemDetailBeanList");
				List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");
				List<String> messageList = new ArrayList<>();
				int zeroCheck = 0;
				for (BasketBean bask : basket) {
					ItemDetailBean itemdetailbean = ItemDao.findOneByItemId(bask.getId());
					itemdetailbean.getStock();
					if (itemdetailbean.getStock() == 0) {
						messageList.add(itemdetailbean.getName() + MSGConstant.MSG_ORDER_ITEM_STOCK_NONE);
						zeroCheck++;
						
						
					}else if (bask.getOrderNum() > itemdetailbean.getStock()) {
						bask.setOrderNum(itemdetailbean.getStock());
						messageList.add(itemdetailbean.getName() + MSGConstant.MSG_ORDER_ITEM_STOCK_SHORT);
						//response.sendRedirect(request.getContextPath() + "/order/regist/confirm");
						//request.getRequestDispatcher("/jsp/client/order/comfirm.jsp").forward(request, response);

						
					} 
					
				}
				if(messageList.size()>0) {
					session.setAttribute("messageList", messageList);
					//response.sendRedirect(request.getContextPath() + "/order/regist/confirm");
				}
				session.setAttribute("orderform", orderform);
				response.sendRedirect(request.getContextPath() + "/order/regist/confirm");

			} else if (backflg.equals("on")) {
				request.getRequestDispatcher("/jsp/client/order/input_payment.jsp").forward(request, response);

			} else {
				// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE
						+ Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;

		}

	}
}
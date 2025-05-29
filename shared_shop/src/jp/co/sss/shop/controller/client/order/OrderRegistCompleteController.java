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
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dao.OrderItemDao;
import jp.co.sss.shop.dto.OrderItem;
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
		boolean zeroAll = true;
		try {

			OrderForm orderForm = (OrderForm) session.getAttribute("orderform");
			UserBean user_bean = (UserBean) session.getAttribute("user");

			int user_id = user_bean.getId();
			OrderDao.insert(orderForm, user_id);
			

			int orderid = OrderDao.getOrderId(user_id);
			List<ItemDetailBean> itemDetailBeanList = (List<ItemDetailBean>) session.getAttribute("itemDetailBeanList");
			List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");
			List<String> messageList = new ArrayList<>();
			for (BasketBean bask : basket) {
				ItemDetailBean itemdetailbean = ItemDao.findOneByItemId(bask.getId());
				itemdetailbean.getStock();
				
				 if (itemdetailbean.getStock() == 0) {
						messageList.add(itemdetailbean.getName() + MSGConstant.MSG_ORDER_ITEM_STOCK_NONE);
						//response.sendRedirect(request.getContextPath() + "/order/regist/confirm");
						
					}else if (bask.getOrderNum() > itemdetailbean.getStock()) {
					bask.setOrderNum(itemdetailbean.getStock());
					messageList.add(itemdetailbean.getName() + MSGConstant.MSG_ORDER_ITEM_STOCK_SHORT);
					//response.sendRedirect(request.getContextPath() + "/order/regist/confirm");
					//request.getRequestDispatcher("/jsp/client/order/comfirm.jsp").forward(request, response);

					
				}  else {
					int price = itemdetailbean.getPrice();
					OrderItem orderItem = new OrderItem();
					orderItem.setQuantity(bask.getOrderNum());
					orderItem.setItemId(bask.getId());
					orderItem.setPrice(price);
					orderItem.setOrderId(orderid);

					OrderItemDao.insert(orderItem);

					ItemDao.update(bask.getOrderNum(), bask.getId());
					session.removeAttribute("basket");
				}
				if(itemdetailbean.getStock()>0) {
					zeroAll = false;
				}
			}
			if(messageList.size()>0) {
				session.setAttribute("messageList", messageList);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;

		}
		session.setAttribute("zeroAll",zeroAll);
		response.sendRedirect(request.getContextPath() + "/order/regist/complete");

	}

}

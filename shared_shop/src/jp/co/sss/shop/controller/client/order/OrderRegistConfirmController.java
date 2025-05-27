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
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;

/**
 * Servlet implementation class OrderRegistConfirmController
 */
@WebServlet("/order/regist/confirm")
public class OrderRegistConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<ItemDetailBean> itemDetailBeanList = new ArrayList<>();
		try {	
			@SuppressWarnings("unchecked")
			List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

			for (BasketBean value : basket) {
				value.getId();
				ItemDetailBean item = (ItemDetailBean) ItemDao.findOneByItemId(value.getId());
				itemDetailBeanList.add(item);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		session.setAttribute("itemDetailBeanList", itemDetailBeanList);
		request.getRequestDispatcher("/jsp/client/order/confirm.jsp").forward(request, response);
	}
}

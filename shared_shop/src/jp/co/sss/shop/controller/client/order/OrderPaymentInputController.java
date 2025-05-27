package jp.co.sss.shop.controller.client.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
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
		if (backflg.equals("off")) {
			
			HttpSession session = request.getSession();
			OrderForm orderform = (OrderForm) session.getAttribute("orderform");
			orderform.setPayMethod(request.getParameter("payMethod"));

			session.setAttribute("orderform", orderform);
			response.sendRedirect(request.getContextPath() + "/order/regist/confirm");
		} else if(backflg.equals("on")){
			request.getRequestDispatcher("/jsp/client/order/input_payment.jsp").forward(request, response);

		}else {
			// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}
}

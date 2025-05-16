package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/basket/list")
public class BasketShowListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Basket basket = (Basket) session.getAttribute("basket");

		if (basket == null) {
			basket = new Basket();
			session.setAttribute("basket", basket);
		}

		request.setAttribute("basketItems", basket.getItems());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/client/basket/list.jsp");
		dispatcher.forward(request, response);
	}
}

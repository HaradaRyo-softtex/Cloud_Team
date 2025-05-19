package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.BasketBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.MSGConstant;
import jp.co.sss.shop.dao.ItemDao;

@WebServlet("/basket/list")
public class BasketShowListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		List<String> messages = new ArrayList<>();

		if (basket == null || basket.isEmpty()) {
			request.setAttribute("message", MSGConstant.MSG_BASKET_LIST_NONE);
		} else {
			Iterator<BasketBean> it = basket.iterator();

			while (it.hasNext()) {
				BasketBean item = it.next();
				int stock = getStockByItemId(item.getId());

				if (stock <= 0) {
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_NONE);
					it.remove();
				} else if (item.getOrderNum() > stock) {
					item.setOrderNum(stock);
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_SHORT);
				}
			}

			if (basket.isEmpty()) {
				request.setAttribute("message", MSGConstant.MSG_BASKET_LIST_NONE);
			} else {
				Collections.reverse(basket);
				request.setAttribute("basket", basket);
			}
		}

		if (!messages.isEmpty()) {
			request.setAttribute("messageList", messages);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp//client/basket/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 商品IDから在庫数を取得する
	 *
	 * @param itemId 商品ID
	 * @return 在庫数
	 */
	private int getStockByItemId(int itemId) {
		try {
			ItemDetailBean itemDetail = ItemDao.findOneByItemId(itemId);
			if (itemDetail != null) {
				return itemDetail.getStock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

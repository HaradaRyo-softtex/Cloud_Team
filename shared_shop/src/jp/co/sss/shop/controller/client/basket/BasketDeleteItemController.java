package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.BasketBean;

@WebServlet("/basket/delete")
public class BasketDeleteItemController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションスコープから買い物かご情報を取得
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		if (basket != null) {
			// 商品IDを取得
			Integer id = Integer.parseInt(request.getParameter("id"));

			// 商品がカートに存在するか確認し、存在すれば数を減らすか削除
			for (BasketBean item : basket) {
				if (item.getId().equals(id)) {
					if (item.getOrderNum() > 1) {
						item.setOrderNum(item.getOrderNum() - 1); // 数を減らす
					} else {
						basket.remove(item); // 数が1の場合、商品を削除
					}
					break;
				}
			}
		}

		// 買い物かご画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

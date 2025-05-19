package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.BasketBean;

@WebServlet("/basket/add")
public class BasketAddItemController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションスコープから買い物かご情報を安全に取得（ジェネリクスを使用）
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		// セッションスコープに買い物かご情報がない場合、新規作成
		if (basket == null) {
			basket = new LinkedList<>();
			session.setAttribute("basket", basket); // セッションに設定
		}

		// 商品のパラメータ取得
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer stock = Integer.parseInt(request.getParameter("stock"));

		// 商品が既に買い物かごに存在するか確認
		boolean exists = false;
		for (BasketBean item : basket) {
			if (item.getId().equals(id)) {
				// 商品がある場合、注文数を在庫数に応じて増加
				if (item.getOrderNum() < item.getStock()) {
					item.setOrderNum(item.getOrderNum() + 1);
				}
				exists = true;
				break;
			}
		}

		// 商品が存在しない場合、新しい商品を買い物かごに追加
		if (!exists) {
			BasketBean newItem = new BasketBean(id, name, stock);
			basket.add(0, newItem); // 先頭に追加
		}

		// 買い物かご画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

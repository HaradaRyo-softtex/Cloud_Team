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
import jp.co.sss.shop.bean.ItemBean;
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
				int stock = getStockByItemId(item.getId()); // 商品IDから在庫を取得

				if (stock <= 0) {
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_NONE);
					it.remove(); // 在庫ゼロ ⇒ 削除
				} else if (item.getOrderNum() > stock) {
					item.setOrderNum(stock); // 数量を在庫に合わせる
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_SHORT);
				}
			}

			if (basket.isEmpty()) {
				request.setAttribute("message", MSGConstant.MSG_BASKET_LIST_NONE);
			} else {
				Collections.reverse(basket); // 買い物かごのリストを逆順にして表示
				request.setAttribute("basket", basket);
			}
		}

		if (!messages.isEmpty()) {
			request.setAttribute("messageList", messages);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/client/basket/list.jsp");
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
			List<ItemBean> itemList = ItemDao.findAll(""); // ItemDaoから商品情報を取得（引数は並び順の指定）
			for (ItemBean item : itemList) {
				if (item.getId().equals(itemId)) {
					return getStockFromItem(item); // ここで在庫数を取得
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // エラー処理
		}
		return 0; // 何か問題があれば0を返す
	}

	/**
	 * 商品の在庫数を取得
	 *
	 * @param item 商品情報
	 * @return 在庫数（仮のデータとして現在の在庫数を返す）
	 */
	private int getStockFromItem(ItemBean item) {
		// 実際の在庫数は、`item.getStock()` のようにDBから取得する
		// 今回は仮の値を返します。
		return 5; // 仮の在庫数
	}
}

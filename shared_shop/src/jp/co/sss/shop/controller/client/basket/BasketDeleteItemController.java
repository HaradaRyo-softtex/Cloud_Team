package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.BasketBean;

/**
 * BasketDeleteItemControllerクラス
 *
 * クライアントが買い物かご内の商品を1点削除するサーブレット。
 * 商品の数量が1点より多い場合は数量を1減らし、1点のみの場合はかごから削除する。
 * 処理後は買い物かご画面へリダイレクトする。
 *
 * @author 
 * @version 1.0
 */
@WebServlet("/basket/delete")
public class BasketDeleteItemController extends HttpServlet {
	
	/**
	 * POSTリクエストを処理するメソッド。
	 * 
	 * @param request  HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを取得
		HttpSession session = request.getSession();

		// セッションから買い物かご（basket）を取得
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		if (basket != null) {
			// リクエストパラメータから削除対象の商品IDを取得
			Integer itemId = Integer.parseInt(request.getParameter("itemId"));

			// 買い物かご内をループして該当商品を検索
			Iterator<BasketBean> iterator = basket.iterator();
			while (iterator.hasNext()) {
				BasketBean item = iterator.next();

				if (item.getId().equals(itemId)) {
					if (item.getOrderNum() > 1) {
						// 商品の注文数が2以上なら1つ減らす
						item.setOrderNum(item.getOrderNum() - 1);
					} else {
						// 商品の注文数が1なら、かごから削除
						iterator.remove();
					}
					break;
				}
			}
		}

		// 処理後、買い物かご一覧画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

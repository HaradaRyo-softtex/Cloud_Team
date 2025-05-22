package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * 買い物かご一覧表示用コントローラクラス
 *
 * クライアントの買い物かご画面を表示する。
 * セッションに保存された買い物かご情報を元に、在庫チェックを行い、
 * 在庫切れや在庫不足の場合には数量調整や削除を実施し、メッセージを表示する。
 *
 * URL: /basket/list
 */
@WebServlet("/basket/list")
public class BasketShowListController extends HttpServlet {

	/**
	 * GETリクエスト処理：買い物かご画面の表示
	 *
	 * @param request  クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット処理中の例外
	 * @throws IOException      入出力エラー時の例外
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを取得（存在しない場合は null）
		HttpSession session = request.getSession(false);

		// セッションから買い物かご情報を取得
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		// メッセージ格納用リスト
		List<String> messages = new ArrayList<>();

		// 買い物かごが空または存在しない場合
		if (basket == null || basket.isEmpty()) {
			request.setAttribute("message", MSGConstant.MSG_BASKET_LIST_NONE);
		} else {
			// 買い物かご内の各アイテムに対して在庫チェック
			Iterator<BasketBean> iterator = basket.iterator();

			while (iterator.hasNext()) {
				BasketBean item = iterator.next();

				// DBから最新の在庫数を取得
				int stock = getStockByItemId(item.getId());

				if (stock <= 0) {
					// 在庫なし：メッセージ追加＆アイテム削除
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_NONE);
					iterator.remove();
				} else if (item.getOrderNum() > stock) {
					// 注文数 > 在庫数：注文数を在庫数に修正＆メッセージ追加
					item.setOrderNum(stock);
					messages.add(item.getName() + MSGConstant.MSG_BASKET_STOCK_SHORT);
				}
			}

			// 在庫チェック後、買い物かごが空になった場合
			if (basket.isEmpty()) {
				request.setAttribute("message", MSGConstant.MSG_BASKET_LIST_NONE);
			} else {
				// 表示用に買い物かごデータをリクエストスコープに保存
				request.setAttribute("basket", basket);
			}
		}

		// メッセージリストがある場合はリクエストスコープに保存
		if (!messages.isEmpty()) {
			request.setAttribute("messageList", messages);
		}

		// 買い物かご画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/client/basket/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 商品IDをもとに在庫数を取得する
	 *
	 * @param itemId 商品ID
	 * @return 該当商品の在庫数（取得失敗時は0）
	 */
	private int getStockByItemId(int itemId) {
		try {
			ItemDetailBean itemDetail = ItemDao.findOneByItemId(itemId);
			if (itemDetail != null) {
				return itemDetail.getStock();
			}
		} catch (Exception e) {
			// 本番環境ではログ出力に置き換えることを推奨
			e.printStackTrace();
		}
		return 0;
	}
}

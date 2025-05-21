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

/**
 * BasketAddItemControllerクラス
 * 買い物かごに商品を追加するサーブレット。
 * セッションから買い物かごを取得し、商品在庫数をチェックして追加処理を行う。
 * 処理後、買い物かご一覧画面にリダイレクトする。
 * 
 * @WebServlet("/basket/add")
 */

@WebServlet("/basket/add")
public class BasketAddItemController extends HttpServlet {

	/**
	 * POSTリクエストを処理し、買い物かごに商品を追加する。
	 * セッションスコープに買い物かご情報がなければ新規作成し、
	 * 追加対象商品の在庫数を確認して、条件を満たす場合は追加する。
	 * 
	 * 処理後は買い物かご一覧画面へリダイレクトを行う。
	 * 
	 * @param request HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException サーブレット例外
	 * @throws IOException 入出力例外
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションから買い物かご情報を取得
		HttpSession session = request.getSession();

		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");
		if (basket == null) {
			basket = new LinkedList<>();
			session.setAttribute("basket", basket);
		}

		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer stock = Integer.parseInt(request.getParameter("stock"));

		boolean exists = false;
		// 既に買い物かごにある商品かどうかチェック
		for (BasketBean item : basket) {
			if (item.getId().equals(id)) {
				// 在庫数より注文数が少なければ数量を増やす
				if (item.getOrderNum() < item.getStock()) {
					item.setOrderNum(item.getOrderNum() + 1);
				} else {
					// 在庫切れの場合はエラー情報をセッションにセット
					session.setAttribute("addErrorItemName", item.getName());
				}
				exists = true;
				break;
			}
		}

		// 買い物かごに存在しない商品なら新規追加
		if (!exists) {
			BasketBean newItem = new BasketBean();
			newItem.setId(id);
			newItem.setName(name);
			newItem.setStock(stock);
			newItem.setOrderNum(1);
			// 末尾に追加して順序維持
			basket.add(newItem);
		}

		// 買い物かご一覧画面へリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

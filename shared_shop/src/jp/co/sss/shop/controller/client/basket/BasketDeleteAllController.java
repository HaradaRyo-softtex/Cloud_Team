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

/**
 * 買い物かごを全て削除するコントローラクラス
 * 
 * URL：/basket/deleteAll
 * メソッド：POST
 * 処理内容：
 * ・セッションスコープから買い物かご情報を取得
 * ・存在する場合は中身をクリア（全削除）
 * ・買い物かご一覧画面にリダイレクト
 */
@WebServlet("/basket/deleteAll")
public class BasketDeleteAllController extends HttpServlet {

	/**
	 * POSTリクエスト処理：買い物かごを全削除する
	 *
	 * @param request  クライアントからのリクエスト情報
	 * @param response クライアントへのレスポンス情報
	 * @throws ServletException サーブレット処理中の例外
	 * @throws IOException      入出力エラー時の例外
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// セッションからカート情報を取得
		List<BasketBean> basket = (List<BasketBean>) session.getAttribute("basket");

		if (basket != null) {
			// カート内の全アイテムを削除（リストをクリア）
			basket.clear();
		}

		// カート一覧ページにリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

package jp.co.sss.shop.controller.client.orizinal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.dao.FavoriteDAO;

/**
 * Servlet implementation class FavoriteToggleServlet
 * 
 * ユーザーが指定した商品の「お気に入り」登録状態を
 * トグル（切り替え）するためのサーブレット。
 * 
 * POSTリクエストで商品IDを受け取り、ログインユーザーの
 * お気に入りに登録されていれば解除、未登録なら登録する。
 * 結果はJSON形式で返却する。
 * 
 * URLパターン: /favorite/toggle
 */
@WebServlet("/favorite/toggle")
public class FavoriteToggleServlet extends HttpServlet {

	/**
	 * POSTリクエストを処理し、指定された商品の
	 * お気に入り登録・解除を切り替えるメソッド
	 * 
	 * @param request HttpServletRequest オブジェクト（リクエスト情報）
	 * @param response HttpServletResponse オブジェクト（レスポンス情報）
	 * @throws ServletException サーブレット例外
	 * @throws IOException 入出力例外
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータから商品IDを取得
		int itemId = Integer.parseInt(request.getParameter("item_id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		// レスポンスのコンテンツタイプをJSONに設定（UTF-8）
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// ユーザーが未ログインの場合は401 Unauthorizedを返す
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("{\"error\":\"login_required\"}");
			return;
		}

		// DAOを使ってお気に入り登録状況を確認
		FavoriteDAO dao = new FavoriteDAO();
		boolean isFavorite = dao.isFavorite(user.getId(), itemId);

		// 既にお気に入り登録されていれば解除し、
		// 登録されていなければ追加する処理
		if (isFavorite) {
			dao.removeFavorite(user.getId(), itemId);
			// 処理結果をJSONで返す
			out.print("{\"status\":\"removed\"}");
		} else {
			dao.addFavorite(user.getId(), itemId);
			out.print("{\"status\":\"added\"}");
		}
	}
}
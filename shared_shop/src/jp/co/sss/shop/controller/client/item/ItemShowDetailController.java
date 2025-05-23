package jp.co.sss.shop.controller.client.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.FavoriteDAO;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.validator.IdValid;

/**
 * 商品詳細画面表示コントローラー
 * 
 * 指定された商品IDの詳細情報をDBから取得し、
 * ログインユーザーがいればお気に入り登録状態も判定して詳細画面を表示する。
 */
@WebServlet("/item/detail")
public class ItemShowDetailController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品詳細画面の表示処理
	 * 
	 * GETリクエストを受け取り、商品IDの検証・DBからの商品詳細取得・
	 * お気に入り登録状態の判定を行い、JSPへフォワードする。
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException サーブレット例外
	 * @throws IOException      入出力例外
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションを取得し、古いitemForm属性を削除（フォームの初期化）
		HttpSession session = request.getSession();
		session.removeAttribute("itemForm");

		// リクエストパラメータから商品IDを取得
		String id = request.getParameter("id");

		// 商品IDの検証（不正なIDの場合はエラー画面にリダイレクト）
		if (IdValid.isNOTCorrectItemId(id)) {
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}

		ItemDetailBean itemDetailBean = null;

		try {
			// DBから商品詳細情報を取得
			itemDetailBean = ItemDao.findOneByItemId(id);
		} catch (ClassNotFoundException | SQLException e) {
			// DBアクセス時の例外発生時はエラー画面にリダイレクト
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}

		// 取得できなかった場合（該当商品なし）はエラー画面にリダイレクト
		if (itemDetailBean == null) {
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			return;
		}

		// ログインユーザーが存在すればお気に入り登録状態を判定
		UserBean user = (UserBean) session.getAttribute("user");
		if (user != null) {
			FavoriteDAO favoriteDAO = new FavoriteDAO();
			boolean isFavorite = false;
			try {
				// ユーザーのお気に入り登録を判定
				isFavorite = favoriteDAO.isFavorite(user.getId(), itemDetailBean.getId());
			} catch (Exception e) {
				// DBエラーが発生しても処理は続行し、お気に入り状態はfalseのままにする
				e.printStackTrace();
			}
			// 商品詳細Beanにお気に入り状態をセット
			itemDetailBean.setFavorite(isFavorite);
		} else {
			// 未ログインの場合はお気に入り未登録(false)とする
			itemDetailBean.setFavorite(false);
		}

		// リクエスト属性に商品詳細情報をセットし、詳細画面へフォワード
		request.setAttribute("itemDetailBean", itemDetailBean);
		request.getRequestDispatcher("/jsp/client/item/detail.jsp").forward(request, response);
	}
}

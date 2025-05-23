package jp.co.sss.shop.controller.client.item;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.dao.FavoriteDAO;

/**
 * お気に入り商品一覧表示機能（ログイン必須）
 */
@WebServlet("/favorite/list")
public class FavoriteListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエスト時の処理
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // セッションがない or ログインしていない場合はログイン画面にリダイレクト
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // ログイン中ユーザー情報を取得
        UserBean user = (UserBean) session.getAttribute("user");

        // ユーザーのお気に入り商品一覧を取得
        FavoriteDAO favoriteDao = new FavoriteDAO();
        List<ItemBean> favoriteItems = favoriteDao.findByUserId(user.getId());

        // リクエストにセット
        request.setAttribute("favoriteItems", favoriteItems);

        // JSPにフォワード
        request.getRequestDispatcher("/jsp/client/orizinal/favorite_list.jsp").forward(request, response);
    }
}

package jp.co.sss.shop.controller.dient.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dto.OrdersSum;

/**
 * ログイン中ユーザーの注文履歴一覧を表示するコントローラ
 */
@WebServlet("/order/list")
public class OrderShowListController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * DBから注文情報一覧を取得して表示する
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<OrdersSum> orderSumList = new ArrayList<>();

		try {
			HttpSession session = request.getSession();
			UserBean user_bean = (UserBean) session.getAttribute("user");

			// ログインしていない場合はログインページへリダイレクト
			if (user_bean == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}

			int user_id = user_bean.getId();
			orderSumList = OrderDao.findAllByOrderIdIncludeUserName(user_id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}

		request.setAttribute("orderSumList", orderSumList);
		request.getRequestDispatcher("/jsp/client/order/list.jsp").forward(request, response);
	}

	/**
	 * POSTリクエストもGETと同様に処理
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}

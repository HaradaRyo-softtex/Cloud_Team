package jp.co.sss.shop.controller.client.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.UserDao;
import jp.co.sss.shop.form.OrderForm;
import jp.co.sss.shop.validator.UserInputValid;

/**
 * Servlet implementation class OrderAddressInputController
 */
@WebServlet("/order/address/input")
public class OrderAddressInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String backflg = request.getParameter("backflg");
		if (backflg == null) {
			HttpSession session = request.getSession();
			UserBean user_bean = (UserBean) session.getAttribute("user");
			int user_id = user_bean.getId();
			try {
				UserDetailBean userdetailbean = UserDao.findOneByUserId(user_id);
				OrderForm orderform=new OrderForm();
				orderform.setPostalCode(userdetailbean.getPostalCode());
				orderform.setAddress(userdetailbean.getAddress());
				orderform.setName(userdetailbean.getName());
				orderform.setPhoneNumber(userdetailbean.getPhoneNumber());
				
				session.setAttribute("orderform",orderform);
				request.getRequestDispatcher("/jsp/client/order/input_address.jsp").forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		} else if (backflg.equals("off")) {
			HttpSession session = request.getSession();
			OrderForm orderform = new OrderForm();
			orderform = (OrderForm) session.getAttribute("orderform");

			orderform.setPostalCode(request.getParameter("postalCode"));
			orderform.setAddress(request.getParameter("address"));
			orderform.setName(request.getParameter("name"));
			orderform.setPhoneNumber(request.getParameter("phoneNumber"));

			//エラーメッセージ
			List<String> errorMessageList = null;
			try {
				errorMessageList = UserInputValid.orderInputErrorMessageList(orderform);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				System.out.println(7);
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (errorMessageList.size() > 0) {

				// 入力値にエラーがある場合
				// リクエストスコープにセット
				request.setAttribute("orderform", orderform);
				request.setAttribute("errorMessageList", errorMessageList);

				// 会員登録入力画面を表示
				request.getRequestDispatcher("/jsp/client/order/input_address.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合
				// セッションスコープにセット
				session.setAttribute("orderform", orderform);
				response.sendRedirect(request.getContextPath() + "/order/payment/input");
			}
			

		} else if(backflg.equals("on")){
			request.getRequestDispatcher("/jsp/client/order/input_address.jsp").forward(request, response);
		
		}else {
			// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}
	}

}

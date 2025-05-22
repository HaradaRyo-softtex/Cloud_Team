package jp.co.sss.shop.controller.client.order;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.dao.UserDao;

/**
 * Servlet implementation class OrderAddressInputController
 */
@WebServlet("/order/address/input ")
public class OrderAddressInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String backflg=request.getParameter("backflg");
		if(backflg==null) {
			HttpSession session = request.getSession();
			UserBean user_bean =  (UserBean) session.getAttribute("user");
			int user_id = user_bean.getId();
			try {
				UserDetailBean bean= UserDao.findOneByUserId(user_id);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}
	}
 
}

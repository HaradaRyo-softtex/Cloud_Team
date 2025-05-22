package jp.co.sss.shop.controller.client.user;

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

@WebServlet("/user/detail") 
public class UserShowDetailController  extends HttpServlet { 
protected void doGet(HttpServletRequest request, HttpServletResponse response)  
throws ServletException, IOException {
	
UserDetailBean userdetailbean=new UserDetailBean();
HttpSession session = request.getSession();
session.getAttribute("user");
UserBean userbean=(UserBean) session.getAttribute("user");
int id =userbean.getId();

session.getAttribute("user");
try {
	userdetailbean=UserDao.findOneByUserId(id);
	
} catch (ClassNotFoundException e) {
	// TODO 自動生成された catch ブロック
	e.printStackTrace();
} catch (SQLException e) {
	// TODO 自動生成された catch ブロック
	e.printStackTrace();
}

request.setAttribute("userDetailBean",userdetailbean); 
request.getRequestDispatcher("/jsp/client/user/detail.jsp").forward(request, 
response);
}
}
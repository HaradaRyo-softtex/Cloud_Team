package jp.co.sss.shop.controller.client.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/basket/deleteAll")
public class BasketDeleteAllController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションスコープから買い物かご情報を取得
		session.removeAttribute("basket"); // セッションスコープから買い物かごを削除

		// 買い物かご画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/basket/list");
	}
}

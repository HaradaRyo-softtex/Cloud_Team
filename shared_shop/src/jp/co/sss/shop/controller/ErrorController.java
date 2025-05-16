package jp.co.sss.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.shop.constant.URLConstant;

/**
 * システムメンテナンス画面表示用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/error")
public class ErrorController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * エラー発生時の画面表示
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String status = request.getParameter("status");

		if (status != null) {
			// Web.xmlの設定 HTTPステータスコードでのエラー
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + status);
		} else if (type != null) {
			// 処理中のエラー
			request.setAttribute("type", type);
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}

	}

}

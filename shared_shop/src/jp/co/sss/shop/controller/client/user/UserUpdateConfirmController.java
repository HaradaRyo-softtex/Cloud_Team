
package jp.co.sss.shop.controller.client.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.form.UserForm;

/*会員情報変更確認画面用コントローラ*/

@WebServlet("/user/update/confirm")
public class UserUpdateConfirmController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/*会員情報の変更確認画面を表示する*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserForm userForm = (UserForm) session.getAttribute("userForm");
		if (userForm == null) {
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
		} else {
			request.getRequestDispatcher("/jsp/client/user/update_confirm.jsp").forward(request, response);
		}
	}

}

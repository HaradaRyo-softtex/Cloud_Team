//会員登録入力画面

package jp.co.sss.shop.controller.client.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.form.UserForm;
import jp.co.sss.shop.validator.UserInputValid;

@WebServlet("/user/regist/input")
public class UserRegistInputController extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// トップページで、「新規会員登録」ボタンを押された時、会員登録画面を表示する
		HttpSession session = request.getSession();
		session.removeAttribute("userForm");
		request.getRequestDispatcher("/jsp/client/user/regist_input.jsp").forward(request, response);

	}

	//会員情報登録入力画面で入力された値を取得し入力チェックを行う 会員情報確認画面から戻るボタンで遷移した場合に会員情報登録入力画面を表示する
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String backflg = request.getParameter("backflg");
		HttpSession session = request.getSession();
		UserForm userForm = null;
		if (backflg != null && backflg.equals(Constant.BACKFLG_OFF)) {
			/* 会員登録入力画面で「確認」ボタンが押された時*/
			
			// 入力された値を取得
			userForm = new UserForm();
			userForm.setEmail(request.getParameter("email"));
			userForm.setPassword(request.getParameter("password"));
			userForm.setName(request.getParameter("name"));
			userForm.setPostalCode(request.getParameter("postalCode"));
			userForm.setAddress(request.getParameter("address"));
			userForm.setPhoneNumber(request.getParameter("phoneNumber"));
			
			userForm.setNewPassword(request.getParameter("newpassword"));
			
			// エラーチェック
			List<String> errorMessageList = null;
			try {
				errorMessageList = UserInputValid.makeInputErrorMessageList(userForm);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (errorMessageList.size() > 0) {
				// 入力値にエラーがある場合
				// リクエストスコープにセット
				request.setAttribute("userForm", userForm);
				request.setAttribute("errorMessageList", errorMessageList);
				
				// 会員登録入力画面を表示
				request.getRequestDispatcher("/jsp/client/user/regist_input.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合
				// セッションスコープにセット
				session.setAttribute("userForm", userForm);
				response.sendRedirect(request.getContextPath() + "/client/user/regist/confirm");
			}

		} else if (backflg != null && backflg.equals(Constant.BACKFLG_ON)) {
			// 会員登録確認画面で「戻る」ボタンが押された時
			// 登録入力画面からのリダイレクト遷移
			userForm = (UserForm) session.getAttribute("userForm");
			if (userForm == null) {
				// セッションスコープに情報がない場合システムエラーとする
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			} else {
				// 会員登録入力画面を表示
				request.getRequestDispatcher("/jsp/client/user/regist_input.jsp").forward(request, response);
			}
		} else {
			// 想定外の状態 backflgがnullもしくは、値が想定外
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
	
			}
		
	}
}

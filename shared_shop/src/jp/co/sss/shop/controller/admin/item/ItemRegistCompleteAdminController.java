package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.dto.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.util.SaveFile;
import jp.co.sss.shop.validator.URLValid;

/**
 * 商品情報登録完了処理用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/regist/complete")
public class ItemRegistCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 登録処理完了後のリダイレクト先、完了画面を表示する(2重送信防止用)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_ITEM_REGIST_CONFIRM)) {
			// 遷移元が確認画面の場合、完了画面を表示
			request.getRequestDispatcher("/jsp/admin/item/regist_complete.jsp").forward(request, response);
		} else {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}

	/**
	 * DBに商品情報を登録し、完了画面にリダイレクトする
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ItemForm itemForm = (ItemForm) session.getAttribute("itemForm");
		if (itemForm == null) {
			// セッションスコープに情報がない場合システムエラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}

		Item item = new Item();
		item.setName(itemForm.getName());
		item.setPrice(Integer.parseInt(itemForm.getPrice()));
		item.setDescription(itemForm.getDescription());
		item.setStock(Integer.parseInt(itemForm.getStock()));
		try {
			item.setImage(SaveFile.copyImageFile(itemForm.getImage(),
					getServletContext().getRealPath(URLConstant.IMAGE_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
			return;
		}

		item.setCategoryId(Integer.parseInt(itemForm.getCategoryId()));

		int ret = 0;
		try {
			ret = ItemDao.insert(item);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			// 登録に失敗
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);

		} else {
			// 正常に登録できた場合
			session.removeAttribute("itemForm");
			response.sendRedirect(request.getContextPath() + "/admin/item/regist/complete");
		}
	}

}

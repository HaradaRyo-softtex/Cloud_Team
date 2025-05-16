package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.dto.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.util.SaveFile;
import jp.co.sss.shop.validator.IdValid;
import jp.co.sss.shop.validator.URLValid;

/**
 * 商品情報変更完了画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/update/complete")
public class ItemUpdateCompleteAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品情報変更完了画面を表示する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ItemDetailBean item = null;
		// 1つ前のURL情報を取得しチェック
		if (URLValid.isNOTCorrectReferer(request.getHeader("REFERER"), URLConstant.URL_ADMIN_ITEM_UPDATE_CONFIRM)) {
			// 遷移元が確認画面以外の場合エラーとする
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
			return;
		}
		// idの検査 問題ありのときTrue
		if (IdValid.isNOTCorrectItemId(id)) {
			// 不正なID
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_ILLEGAL_ID);
			return;
		}

		try {
			// 変更完了したIDが有効なIDかを確認するためにDBに問い合わせ
			item = ItemDao.findOneByItemId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (item == null) {
			// 情報が取得できなかった場合エラーとする
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			return;
		}
		request.setAttribute("id", id);
		request.getRequestDispatcher("/jsp/admin/item/update_complete.jsp").forward(request, response);

	}

	/**
	 * 商品情報の変更内容をDBに反映し、完了画面表示処理にリダイレクトする
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
		item.setId(Integer.parseInt(itemForm.getId()));
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
//		item.setImage(itemForm.getImage());
		item.setCategoryId(Integer.parseInt(itemForm.getCategoryId()));

		int ret = 0;
		try {
			ret = ItemDao.update(item);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
			return;
		}
		if (ret == 0) {
			// DBで更新したレコードがない場合
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_UPDATE);
		} else {
			// 正常に更新できた場合
			session.removeAttribute("itemForm");
			response.sendRedirect(request.getContextPath() + "/admin/item/update/complete?id=" + item.getId());
		}
	}

}

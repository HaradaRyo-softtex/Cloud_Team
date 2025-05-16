package jp.co.sss.shop.controller.admin.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.dao.ItemDao;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.util.BeanCopy;
import jp.co.sss.shop.util.SaveFile;
import jp.co.sss.shop.validator.ItemInputValid;

/**
 * 商品情報変更入力画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/update/input")
@MultipartConfig
public class ItemUpdateInputAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品情報変更入力画面の表示、入力値の受付とチェック、戻るボタン後の入力画面表示を行う
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String backflg = request.getParameter("backflg");
		String id = null;

		HttpSession session = request.getSession();
		ItemForm itemForm = null;

		if (backflg == null) {
			// 一覧、詳細画面からの遷移
			id = request.getParameter("id");
			ItemDetailBean itemDetailBean = null;
			try {
				// DBから商品情報を取得する
				itemDetailBean = ItemDao.findOneByItemId(id);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (itemDetailBean == null) {
				response.sendRedirect(
						request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
			} else {
				// 商品情報をが取得できた
				itemForm = BeanCopy.copyDetailBeanToForm(itemDetailBean);
				// セッションスコープに入力値をセット
				session.setAttribute("itemForm", itemForm);
				request.getRequestDispatcher("/jsp/admin/item/update_input.jsp").forward(request, response);
			}
		} else if (backflg.equals(Constant.BACKFLG_ON)) {
			// 変更確認画面で「戻る」ボタンが押された時
			// 変更入力画面を表示
			request.getRequestDispatcher("/jsp/admin/item/update_input.jsp").forward(request, response);

		} else if (backflg.equals(Constant.BACKFLG_OFF)) {

			// 変更入力画面で「確認」ボタンが押された時

			itemForm = new ItemForm();
			// 入力された値を取得
			itemForm.setId(request.getParameter("id"));
			itemForm.setName(request.getParameter("name"));
			itemForm.setPrice(request.getParameter("price"));
			itemForm.setDescription(request.getParameter("description"));

			itemForm.setStock(request.getParameter("stock"));
			String imageFile = null;
			try {
				// name属性がimageFileのファイルをPartオブジェクトとして取得
				imageFile = SaveFile.saveTmpImageFile(request.getPart("imageFile"),
						getServletContext().getRealPath(URLConstant.IMAGE_PATH));
			} catch (IOException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
			}

			if (imageFile != null && imageFile.length() > 0) {
				itemForm.setImage(imageFile);
			} else {
				// 画像ファイル名が指定されていなかった場合、現在の設定情報を反映させるため、セッションスコープの情報を取り出す
				ItemForm sessionForm = (ItemForm) session.getAttribute("itemForm");
				if (sessionForm == null) {
					// セッションスコープに情報がない場合システムエラーとする
					response.sendRedirect(
							request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
					return;
				}
				itemForm.setImage(sessionForm.getImage());
			}
			itemForm.setCategoryId(request.getParameter("categoryId"));

			// エラーチェック
			List<String> errorMessageList = null;
			try {
				errorMessageList = ItemInputValid.makeInputErrorMessageList(itemForm);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
				return;
			}
			if (errorMessageList.size() > 0) {
				// 入力値にエラーがある場合
				// セッションスコープに入力値をセット
				session.setAttribute("itemForm", itemForm);
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("errorMessageList", errorMessageList);
				// 登録入力画面を表示
				request.getRequestDispatcher("/jsp/admin/item/update_input.jsp").forward(request, response);

			} else {
				// 入力値にエラーがない場合
				CategoryBean category = null;
				// カテゴリIDからカテゴリ名を取得
				try {
					category = CategoryDao.findOneByCategoryId(itemForm.getCategoryId());

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					response.sendRedirect(
							request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
					return;
				}
				if (category == null) {
					response.sendRedirect(
							request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB_NONE);
				} else {

					itemForm.setCategoryName(category.getName());
					// セッションスコープにセット
					session.setAttribute("itemForm", itemForm);
					response.sendRedirect(request.getContextPath() + "/admin/item/update/confirm");
				}
			}
		} else {
			// backflgが存在するが、値が想定外(Hidden情報が書き換えられた)
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}
}

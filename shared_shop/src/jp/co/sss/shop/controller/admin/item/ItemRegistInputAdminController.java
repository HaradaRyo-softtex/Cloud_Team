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
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.CategoryDao;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.util.SaveFile;
import jp.co.sss.shop.validator.ItemInputValid;

/**
 * 商品情報登録入力画面用コントローラ
 * 
 * @author System Shared.co.,Ltd.
 */
@WebServlet("/admin/item/regist/input")
@MultipartConfig
public class ItemRegistInputAdminController extends HttpServlet {
	/** シリアルID */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品情報登録入力画面を表示する *
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// セッション情報の初期化
		session.removeAttribute("itemForm");
		request.getRequestDispatcher("/jsp/admin/item/regist_input.jsp").forward(request, response);

	}

	/**
	 * 商品情報登録入力画面で入力された値を取得し入力チェックを行う 商品情報確認画面から戻るボタンで遷移した場合に商品情報登録入力画面を表示する
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String backflg = request.getParameter("backflg");
		HttpSession session = request.getSession();
		ItemForm itemForm = null;

		if (backflg != null && backflg.equals(Constant.BACKFLG_OFF)) {

			// 商品登録入力画面で「確認」ボタンが押された時
			// 入力された値を取得
			itemForm = new ItemForm();

			itemForm.setName(request.getParameter("name"));
			itemForm.setPrice(request.getParameter("price"));
			itemForm.setDescription(request.getParameter("description"));
			itemForm.setStock(request.getParameter("stock"));
			try {
				itemForm.setImage(SaveFile.saveTmpImageFile(request.getPart("imageFile"),
						getServletContext().getRealPath(URLConstant.IMAGE_PATH)));
			} catch (IOException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_SYS);
				return;
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
				// リクエストスコープにセット
				request.setAttribute("itemForm", itemForm);
				request.setAttribute("errorMessageList", errorMessageList);
				// 商品登録入力画面を表示
				request.getRequestDispatcher("/jsp/admin/item/regist_input.jsp").forward(request, response);

			} else {
				CategoryBean category = null;
				// 入力値にエラーがない場合
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
					return;
				}

				itemForm.setCategoryName(category.getName());
				// セッションスコープにセット
				session.setAttribute("itemForm", itemForm);
				response.sendRedirect(request.getContextPath() + "/admin/item/regist/confirm");
			}

		} else if (backflg != null && backflg.equals(Constant.BACKFLG_ON)) {
			// 登録確認画面で「戻る」ボタンが押された時

			// 登録入力画面を表示
			request.getRequestDispatcher("/jsp/admin/item/regist_input.jsp").forward(request, response);

		} else {
			// 想定外の状態 backflgがnullもしくは、値が想定外
			response.sendRedirect(
					request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_UNEXPECTED_TRANSITION);
		}

	}

//	/**
//	 * パラメタから画像ファイル名を取得
//	 * 
//	 * @param request リクエストオブジェクト
//	 * @return 画像ファイル名
//	 * @throws IOException      入出力エラー
//	 * @throws ServletException サーブレット処理エラー
//	 */
//	private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
//		// name属性がimageFileのファイルをPartオブジェクトとして取得
//		Part part = request.getPart("imageFile");
//		// ヘッダ情報からファイル名を取得
//		String filename = "";
//		// ヘッダ情報からファイル名を取得
//		filename = part.getSubmittedFileName();
//		if (filename != null && filename.length() > 0) {
//			// アップロードするフォルダ
//			String path = getServletContext().getRealPath(URLConstant.IMAGE_FOLDER);
//			// 現在の日時を「yyyyMMddhhmmss」形式の文字列として取得
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//			String date = dateFormat.format(new Date());
//			filename = date + "_" + filename;
//			// 実際にファイルが保存されるパス確認
//			System.out.println("filepath:" + path + filename);
//			// 書き込み
//			part.write(path + filename);
//		} else {
//			filename = Constant.FILENAME_NOIMAGE;
//		}
//		return filename;
//	}

}

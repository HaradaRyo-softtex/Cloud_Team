/**
 * 
 */
package jp.co.sss.shop.validator;

/**
 * URLの遷移チェックに関するクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */

public class URLValid {

	/**
	 * 遷移元が正しいかのチェック
	 * 
	 * @param strReferer     遷移元のURL
	 * @param strCorrectPath 遷移元ととして正しいパス
	 * @return True(正常)、False(異常）
	 * 
	 */
	public static boolean isCorrectReferer(String strReferer, String strCorrectPath) {
		boolean retflg = true;

		if (strReferer == null) {
			// 遷移元が空
			retflg = false;

		} else if (strReferer.indexOf(strCorrectPath) < 0) {
			// 遷移元のURLに正式なパス名が含まれていない
			retflg = false;
		}
		return retflg;
	}

	/**
	 * 遷移元が正しくないことをチェック
	 * 
	 * @param strReferer     遷移元のURL
	 * @param strCorrectPath 遷移元ととして正しいパス
	 * @return True(異常)、False(正常）
	 * 
	 */
	public static boolean isNOTCorrectReferer(String strReferer, String strCorrectPath) {
		return !(isCorrectReferer(strReferer, strCorrectPath));
	}
}

package jp.co.sss.shop.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 入力チェックに関する共通処理をまとめたクラス
 *
 * @author System Shared.co.,Ltd.
 *
 */
public class CommonValid {

	/** インスタンス化を禁止 */
	private CommonValid() {
	}

	/**
	 * 未入力のチェックを行うメソッド
	 *
	 * @param strVal 検査対象文字列
	 * @return true(空) false(空でない)
	 */
	public static boolean isEmpty(String strVal) {

		boolean ret = false;
		if (strVal == null) {
			ret = true;
		} else {
			// nullではない場合、空白を除去
			strVal.strip();
			if (strVal.isEmpty() || strVal.isBlank()) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * 数値かどうかのチェックを行うメソッド
	 *
	 * @param strVal 検査対象文字列
	 * @return true(数値でない) false(数値)
	 */
	public static boolean isNumber(String strVal) {
		try {
			Integer.parseInt(strVal);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	/**
	 * 指定した桁数を超えているかチェックを行うメソッド
	 *
	 * @param strVal   検査対象文字列
	 * @param maxDigit 最大桁数
	 * @return true(桁数を超えている) false(桁数を超えていない)
	 */
	public static boolean overLength(String strVal, int maxDigit) {
		boolean retflg = false;
		if (maxDigit < strVal.length()) {
			retflg = true;
		}
		return retflg;
	}

	/**
	 * 指定した桁数の範囲外かのチェックを行うメソッド
	 *
	 * @param strVal 対象の文字列
	 * @param min    最小桁数
	 * @param max    最大桁数
	 * @return true(桁数を超えている) false(桁数を超えていない)
	 */
	public static boolean overRange(String strVal, int min, int max) {
		boolean retflg = false;
		if (strVal.length() < min) {
			retflg = true;
		} else if (max < strVal.length()) {
			retflg = true;
		}
		return retflg;
	}

	/**
	 * 指定した数値の範囲外かのチェックを行うメソッド
	 *
	 * @param iVal 対象の数値
	 * @param min  最小数
	 * @param max  最大数
	 * @return true(範囲を超えている) false(範囲を超えていない)
	 */
	public static boolean overRange(int iVal, int min, int max) {
		boolean retflg = false;
		if (iVal < min || max < iVal) {
			retflg = true;
		}
		return retflg;
	}

	/**
	 * 形式の妥当性チェック
	 *
	 * @param strVal     検査対象文字列
	 * @param patternStr 形式の正規表現文字列
	 * @return true(パスワード形式ではない) false(正しい形式)
	 */
	public static boolean isPatternMisMatch(String strVal, String patternStr) {

		boolean retflg = false;
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(strVal);

		if (!m.find()) {
			// 形式が違う
			retflg = true;
		}
		return retflg;
	}

}
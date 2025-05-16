package jp.co.sss.shop.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import jp.co.sss.shop.constant.Constant;

/**
 * 画像ファイル保存処理用クラス
 * 
 * @author System Shared co.,ltd.
 *
 */
public class SaveFile {

	/**
	 * Webサーバ稼働中の一時領域に画像ファイルを保存する処理
	 * 
	 * @param part     ブラウザから送信されたデータ
	 * @param realpath 保存先の物理パス
	 * @return ファイル名
	 * @throws IOException 入出力エラー
	 */
	public static String saveTmpImageFile(Part part, String realpath) throws IOException {

		String fileName = Constant.FILENAME_NOIMAGE;
		// ヘッダ情報からファイル名を取得
		fileName = part.getSubmittedFileName();

		if (fileName.length() > 0 && !fileName.equals(Constant.FILENAME_NOIMAGE)) {
			// 現在の日時を「yyyyMMddhhmmss」形式の文字列として取得
			SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.IMG_FILE_DATE_FORMAT);
			String date = dateFormat.format(new Date());
			// ファイル名の前に日時を追加
			fileName = date + "_" + fileName;
			// 書き込み
			part.write(realpath + fileName);
		} else {
			fileName = null;
		}
		return fileName;
	}

	/**
	 * 画像ファイルをサーバ再起動後も参照可能な場所にコピーする処理
	 * 
	 * @param fileName    ファイル名
	 * @param srcRealPath 一時保管場所のパス
	 * @return ファイル名
	 * @throws IOException 入出力エラー
	 */
	public static String copyImageFile(String fileName, String srcRealPath) throws IOException {
		if (fileName != null && !fileName.equals(Constant.FILENAME_NOIMAGE) && fileName.length() > 0) {
			Path srcPath = Paths.get(srcRealPath + fileName);
			Path dstPath = Paths.get(Constant.IMAGE_REAL_PATH + fileName);
			if (Files.exists(srcPath)) {
				Files.copy(srcPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		return fileName;
	}

}

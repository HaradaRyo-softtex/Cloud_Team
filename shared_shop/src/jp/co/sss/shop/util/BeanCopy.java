package jp.co.sss.shop.util;

import jp.co.sss.shop.bean.CategoryBean;
import jp.co.sss.shop.bean.ItemDetailBean;
import jp.co.sss.shop.bean.OrderDetailBean;
import jp.co.sss.shop.bean.UserDetailBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.form.CategoryForm;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.form.OrderForm;
import jp.co.sss.shop.form.UserForm;

/**
 * オブジェクト間でのフィールドコピー処理を行うクラス
 *
 * @author System Shared.co.,Ltd.
 */
public class BeanCopy {

	/**
	 * 入力された会員情報から会員詳細情報を生成
	 *
	 * @param form 入力された会員情報
	 * @return 会員詳細情報
	 */
	public static UserDetailBean copyFormToDetailBean(UserForm form) {
		UserDetailBean bean = new UserDetailBean();

		bean.setId(Integer.parseInt(form.getId()));
		bean.setEmail(form.getEmail());
		bean.setPassword(form.getPassword());
		bean.setName(form.getName());
		bean.setPostalCode(form.getPostalCode());
		bean.setAddress(form.getAddress());
		bean.setPhoneNumber(form.getPhoneNumber());
		bean.setAuthority(Integer.parseInt(form.getAuthority()));
		bean.setAuthorityStr(Constant.AUTH_MAP.get(bean.getAuthority()));
		return bean;
	}

	/**
	 * 入力された注文情報から、注文詳細情報を生成
	 * 
	 * @param form 入力された注文情報
	 * @return 注文詳細情報
	 */
	public static OrderDetailBean copyFormToDetailBean(OrderForm form) {
		OrderDetailBean bean = new OrderDetailBean();

		bean.setName(form.getName());
		bean.setPostalCode(form.getPostalCode());
		bean.setAddress(form.getAddress());
		bean.setPhoneNumber(form.getPhoneNumber());
		bean.setPayMethod(Integer.parseInt(form.getPayMethod()));
		bean.setPayMethodStr(Constant.PAYMETHOD_MAP.get(bean.getPayMethod()));
		return bean;
	}

	/**
	 * 会員詳細情報から、入力欄の初期値用会員情報を生成
	 * 
	 * @param bean 会員詳細情報
	 * @return 入力欄の初期値
	 */
	public static UserForm copyDetailBeanToForm(UserDetailBean bean) {
		UserForm form = new UserForm();

		form.setId(String.valueOf(bean.getId()));
		form.setEmail(bean.getEmail());
		form.setPassword(bean.getPassword());
		form.setName(bean.getName());
		form.setPostalCode(bean.getPostalCode());
		form.setAddress(bean.getAddress());
		form.setPhoneNumber(bean.getPhoneNumber());
		form.setAuthority(String.valueOf(bean.getAuthority()));
		form.setAuthorityStr(Constant.AUTH_MAP.get(bean.getAuthority()));
		return form;
	}

	/**
	 * 商品詳細情報から、入力欄の初期値用商品情報を生成
	 * 
	 * @param bean 商品詳細情報
	 * @return 入力欄の初期値
	 */
	public static ItemForm copyDetailBeanToForm(ItemDetailBean bean) {
		ItemForm form = new ItemForm();

		form.setId(String.valueOf(bean.getId()));
		form.setName(bean.getName());
		form.setPrice(String.valueOf(bean.getPrice()));
		form.setDescription(bean.getDescription());
		form.setStock(String.valueOf(bean.getStock()));
		form.setImage(bean.getImage());
		form.setCategoryId(String.valueOf(bean.getCategoryId()));
		return form;
	}

	/**
	 * カテゴリ詳細情報から、入力欄の初期値用カテゴリ情報を生成
	 * 
	 * @param bean カテゴリ詳細情報
	 * @return 入力欄の初期値
	 */
	public static CategoryForm copyDetailBeanToForm(CategoryBean bean) {
		CategoryForm form = new CategoryForm();

		form.setId(String.valueOf(bean.getId()));
		form.setName(bean.getName());
		form.setDescription(bean.getDescription());
		return form;
	}
}

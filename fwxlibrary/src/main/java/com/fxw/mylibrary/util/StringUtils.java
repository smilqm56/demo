package com.fxw.mylibrary.util;

import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *@Title:string工具类
 */
public class StringUtils {


	/**
	 * 获取string的长度
	 * @param text
	 * @return
	 * @Description:
	 */
	public static int getStringLength(String text) {
		text = text.replaceAll("[^\\x00-\\xff]", "**");
		return text.length();
	}
	/**
	 * 检查字符串是否包含电话号码
	 * @param text
	 * @return
	 */
	public static boolean checkContainPhone(String text){
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(text);
		boolean result = false;
		StringBuffer mBuffer = new StringBuffer();
		while (m.find()) {
			mBuffer.append(m.group());
			System.out.println(mBuffer.toString());
			result = checkPhone(mBuffer.toString());
		}
		return result;
	}

	/**
	 * 验证手机号码是否合格
	 * @return
	 * @Description:
	 */
	public static boolean checkPhone(String phone) {
		String regex = "^1[3|5|7|8|][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	/**
	 * 验证邮箱格式是否合格
	 * @param email
	 * @return
	 * @Description:
	 */
	public static boolean checkEmail(String email) {
		String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 验证密码长度6-16位
	 * @param password
	 * @return
	 * @Description:
	 */
	public static boolean checkPasswordLength(String password) {
		int length = password.length();
		if (length > 16 || length < 6) {
			return false;
		}
		return true;
	}

	/**
	 * 添加删除线
	 * @param v
	 */
	public static void addStrikeThrough(TextView v){
		v.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
	}


	/**
	 * 改变字体颜色
	 * @param tv tv控件
	 * @param color 颜色值
	 * @param start 改变颜色的起始位置
	 * @param end  结束位置
	 */
	public static void setdiffrentColor(TextView tv, int color, int start,
			int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(tv.getText());
		ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
		style.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(style);
	}

	/**
	 * 改变 字体 颜色 大小
	 * 
	 * @param tv tv控件
	 * @param color 颜色值
	 * @param size 字体大小值
	 * @param start 改变颜色的起始位置
	 * @param end 结束位置
	 * @return
	 */
	public static void setdiffrentColor(TextView tv, int color, int size,
			int start, int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(tv.getText());
		ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
		style.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		RelativeSizeSpan sizeSpan = new RelativeSizeSpan(size);
		style.setSpan(sizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(style);
	}
}

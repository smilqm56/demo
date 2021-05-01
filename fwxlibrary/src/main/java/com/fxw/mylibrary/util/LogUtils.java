package com.fxw.mylibrary.util;

import android.util.Log;

/**
 * 
 *@Title:log工具类
 *@Description:
 *@Author:xianwei.fu
 *@Since:2015-7-20
 *@Version:1.1.0
 */
public class LogUtils {

	private static final String LOG_PREFIX = "orange_";

	private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
	private static final int MAX_LOG_TAG_LENGTH = 30;

	private LogUtils() {

	}

	public static final String makeLogTag(String str) {
		if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
			return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
		}
		return LOG_PREFIX + str;
	}

	public static final String makeLogTag(Class<?> cls) {
		return makeLogTag(cls.getSimpleName());
	}

	public static void LOGD(String tag, String message) {
		if (BuildConfig.DEBUG && message != null) {
			Log.d(tag, message);
		}
	}

	public static void LOGD(String tag, String message, Throwable throwable) {
		if (BuildConfig.DEBUG && message != null) {
			Log.d(tag, message, throwable);
		}
	}

	public static void LOGE(String tag, String message) {
		if (BuildConfig.DEBUG && message != null) {
			Log.e(tag, message);
		}
	}

	public static void LOGE(String tag, String message, Throwable throwable) {
		if (BuildConfig.DEBUG && message != null) {
			Log.e(tag, message, throwable);
		}
	}

	public static void LOGI(String tag, String message) {
		if (BuildConfig.DEBUG && message != null) {
			Log.i(tag, message);
		}
	}

	public static void LOGI(String tag, String message, Throwable throwable) {
		if (BuildConfig.DEBUG) {
			Log.i(tag, message, throwable);
		}
	}
}

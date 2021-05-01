package com.fxw.mylibrary.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title:常用工具类,UI相关
 * @Description:
 */
public class UIUtils {

    /**
     * 格式化字符串中的数字
     * @param value
     * @param color
     * @return
     * @Description:
     */
    public static SpannableStringBuilder formatStringWithNumber(String value, int color) {

        SpannableStringBuilder builder = new SpannableStringBuilder(value);
        Pattern pattern = Pattern.compile("[\\d]+?");
        Matcher isNum = pattern.matcher(value);
        while (isNum.find()) {
            int start = isNum.start();
            int end = isNum.end();
            builder.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return builder;
    }

    public static float dpToPx(Context context, final float dp) {
        return dp * (context.getResources().getDisplayMetrics().densityDpi / 160f);
    }

    public static float pxToDp(Context context, final float px) {
        return px / (context.getResources().getDisplayMetrics().densityDpi / 160f);
    }

    public static float spToPx(Context context, final float sp) {
        return sp * (context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static float pxToSp(Context context, final float px) {
        return px / (context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static float dpToPx(final float dp) {
        return dp * (Resources.getSystem().getDisplayMetrics().densityDpi / 160f);
    }

    public static float pxToDp(final float px) {
        return px / (Resources.getSystem().getDisplayMetrics().densityDpi / 160f);
    }

    public static float spToPx(final float sp) {
        return sp * (Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static float pxToSp(final float px) {
        return px / (Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /***
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getDisplayScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getDisplayScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获得状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * @param context
     * @param name
     * @return
     * @Description:根据图片名称获取图片资源id
     */
    public static int getImageResourceId(Context context, String name) {
        return context.getResources().getIdentifier(name, "build/intermediates/exploded-aar/com.android.support/appcompat-v7/23.1.1/res/drawable", context.getPackageName());
    }
}

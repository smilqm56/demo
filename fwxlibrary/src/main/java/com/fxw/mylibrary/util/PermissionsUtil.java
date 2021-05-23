package com.fxw.mylibrary.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title 请求权限util
 * @Description:
 * @Date 2016/03/22下午 1:59
 */
public class PermissionsUtil {

    private static RequestPerissionsListeners mListeners;

    private static void showMessageOKCancel(Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**
     * 多个权限检查是否APP已经拥有权限
     *
     * @param context
     * @param permissions 权限集合
     * @param requestCode 请求id判断
     */
    public static boolean requestArrayPermissions(Object context, final String[] permissions, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        final List<String> deniedPermissions = findDeniedPermissions(getActivity(context), permissions);
        if (deniedPermissions.size() > 0) {
            if (context instanceof Activity) {
                ((Activity) context).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else if (context instanceof Fragment) {
                ((Fragment) context).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
            return false;
        } else return true;
    }


    @TargetApi(Build.VERSION_CODES.M)
    private static List<String> findDeniedPermissions(Activity activity, String[] permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    /**
     * 判断是否获取权限成功
     *
     * @param context
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(Activity context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //未授权集合
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            //如果没有同意添加到集合
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.size() > 0) {
            mListeners.requestPerissionsFailed(requestCode, deniedPermissions);
        } else {
            mListeners.requestPerissionsSuccess(requestCode);
        }

    }

    /**
     * 获取当前activity
     *
     * @param object
     * @return
     */
    public static  Activity getActivity(Object object) {
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }


    public interface RequestPerissionsListeners {
        /**
         * 获取权限成功
         *
         * @param requestCode
         */
        void requestPerissionsSuccess(int requestCode);

        /**
         * 获取权限失败
         *
         * @param requestCode
         * @param deniedPermissions
         */
        void requestPerissionsFailed(int requestCode, List<String> deniedPermissions);
    }

    /**
     * 设置获取权限监听
     *
     * @param listeners
     */
    public static void setRequestPerissionsListeners(RequestPerissionsListeners listeners) {
        mListeners = listeners;
    }
}

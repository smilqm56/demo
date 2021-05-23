package com.fxw.libray;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 程序入口
 *
 * @author admin
 */
public class AppContext extends Application {

    public static AppContext instance;
    //存放activity栈
    private CopyOnWriteArrayList<Activity> mStack = new CopyOnWriteArrayList<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        init();
    }

    private void init() {

    }

    public static AppContext getInstance() {

        return instance;
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * finish一个activity
     *
     * @param activity
     * @Description:
     */
    public void addActivity(Activity activity) {
        try {
            if (activity != null && !activity.isFinishing()) {
                mStack.add(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finishActivity(Activity activity) {
        try {
            activity.finish();
            mStack.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finishActivity(Class<?> clazz) {
        try {
            for (Activity activity : mStack) {
                if (activity.getClass().getName().equals(clazz.getName())) {
                    activity.finish();
                    mStack.remove(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束所有activity
     *
     * @Description:
     */
    public void finishAllActivity() {
        try {
            for (Activity activity : mStack) {
                if (activity != null) {
                    activity.finish();
                }
            }
            mStack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束其它activity
     *
     * @param activity
     * @Description:
     */
    public void finishOthersActivity(Activity activity) {
        try {
            for (Activity a : mStack) {
                if (a != null && a != activity) {
                    a.finish();
                    mStack.remove(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

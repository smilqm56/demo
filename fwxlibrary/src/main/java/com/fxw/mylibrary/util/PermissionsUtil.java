package com.fxw.mylibrary.util;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title   请求权限util
 * @Description:
 * @Date 2016/03/22下午 1:59
 */
public class PermissionsUtil{
//    /**
//     * 单个权限检查是否APP已经拥有权限
//     * @param context 当前界面
//     * @param permissions 权限
//     * @param requestCode 请求id判断
//     * @param hint 提醒用户语言
//     * @return true 拥有 flase 没有
//     */
//    public static boolean requestSingerPermissions(final Activity context, final String permissions, final int requestCode,String hint){
//        int hasPermissions = ContextCompat.checkSelfPermission(context, permissions);
//        //判断是否授权过该权限
//        if (hasPermissions != PackageManager.PERMISSION_GRANTED){
//            //检查是否拒绝过该权限
//            if (!ActivityCompat.shouldShowRequestPermissionRationale(context,permissions)) {
//                showMessageOKCancel(context,hint,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ActivityCompat.requestPermissions(context, new String[]{permissions}, requestCode);
//                                return;
//                            }
//                        });
//            }else {
//                ActivityCompat.requestPermissions(context,new String[]{permissions},requestCode);
//                return false;
//            }
//            return false;
//        }else return true;
//    }
//
//    private static void showMessageOKCancel(Activity context, String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(context)
//                .setMessage(message)
//                .setPositiveButton("确定", okListener)
//                .setNegativeButton("取消", null)
//                .create()
//                .show();
//    }
//
//    /**
//     * 多个权限检查是否APP已经拥有权限
//     * @param context
//     * @param permissions 权限集合
//     * @param hint 提示集合
//     * @param requestCode 请求id判断
//     */
//    public void requestArrayPermissions(final Activity context,final List<String> permissions,List<String> hint,final int requestCode){
//        if (permissions.size() > 0){
//            if (hint.size() > 0){
//                StringBuffer buffer = new StringBuffer();
//                for (int i = 0; i < hint.size(); i++) {
//                    buffer.append(hint.get(i));
//                    if (i == hint.size()-1){
//                        buffer.append("。");
//                    }else buffer.append("，");
//                }
//                showMessageOKCancel(context,buffer.toString(),
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ActivityCompat.requestPermissions(context,permissions.toArray(new String[permissions.size()]),requestCode);
//                            }
//                        });
//                return;
//            }else {
//                ActivityCompat.requestPermissions(context, permissions.toArray(new String[permissions.size()]), requestCode);
//                return;
//            }
//        }
//    }
}

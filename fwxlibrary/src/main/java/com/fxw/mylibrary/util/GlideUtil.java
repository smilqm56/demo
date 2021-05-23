package com.fxw.mylibrary.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/4/22 0022 下午 5:14
 */
public class GlideUtil {

    /**
     * 赋值图片
     * @param uri 地址
     * @param imageView 控件
     */
    public static  void setImage(Context context, String uri, ImageView imageView){
        Glide.with(context).load(uri).crossFade().into(imageView);
    }

    /**
     * 赋值图片
     * @param uri 地址
     * @param imageView 控件
     * @param image		等待图片
     */
    public static  void setImage(Context context,String uri,ImageView imageView,int image){
        Glide.with(context).load(uri).placeholder(image).error(image).into(imageView);
    }

}

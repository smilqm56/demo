package com.fxw.mylibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageloaderUtil {
	/**
	 * 缓存图片设置 设置圆角
	 * 
	 * @param image
	 * @param radius
	 * @return
	 */
	public static DisplayImageOptions ImageOption(int image, int radius) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(image)// 图片未加载完时显示图片
				.showImageForEmptyUri(image)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(image) // 设置图片加载或解码过程中发生错误显示的图片
				.resetViewBeforeLoading(true)
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 启用EXIF和JPEG图像格式
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new RoundedBitmapDisplayer(radius))
				.build();
		return options;
	}
	/**
	 *  缓存图片设置
	 * @param image
	 * @return
	 */
	public static DisplayImageOptions ImageOption(int image) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				 .showImageOnLoading(image)// 图片未加载完时显示图片
				 .showImageForEmptyUri(image)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(image) // 设置图片加载或解码过程中发生错误显示的图片
				.resetViewBeforeLoading(true) // default
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 启用EXIF和JPEG图像格式
				.imageScaleType(ImageScaleType.EXACTLY) // default
				.bitmapConfig(Bitmap.Config.RGB_565) // default
				// .displayer(new RoundedBitmapDisplayer(radius))
				.build();
		return options;
	}
	/**
	 * 缓存图片设置
	 * @return
	 */
	public static DisplayImageOptions ImageOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
			.resetViewBeforeLoading(true)
			.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
			.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
			.considerExifParams(true) // 启用EXIF和JPEG图像格式
			.imageScaleType(ImageScaleType.EXACTLY) // default
			.bitmapConfig(Bitmap.Config.RGB_565) // default
			.build();
		return options;
	}
	public static void clearCache(Context context) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.clearMemoryCache(); // 清除内存缓存
		imageLoader.clearDiskCache(); // 清除SD卡中的缓存
		Toast.makeText(context, "清楚完成", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 赋值图片
	 * @param uri 地址
	 * @param imageView 控件
	 */
	public static  void setImage(String uri,ImageView imageView){
		ImageLoader.getInstance().displayImage(uri, imageView, ImageOption());
	}

	/**
	 * 赋值图片
	 * @param uri 地址
	 * @param imageView 控件
	 * @param image		等待图片
	 */
	public static  void setImage(String uri,ImageView imageView,int image){
		ImageLoader.getInstance().displayImage(uri,imageView,ImageOption(image));
	}

	/**
	 * 赋值图片
	 * @param uri	地址
	 * @param imageView 控件
	 * @param image		等待图片
	 * @param radius	圆弧
	 */
	public static  void setImage(String uri,ImageView imageView,int image,int radius){
		ImageLoader.getInstance().displayImage(uri,imageView,ImageOption(image,radius));
	}
}

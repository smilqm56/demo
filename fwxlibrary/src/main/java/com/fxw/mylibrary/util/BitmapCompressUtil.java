package com.fxw.mylibrary.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 压缩图片处理
 * @author Administrator
 *
 */
public class BitmapCompressUtil {
	
	/**
	 * 质量压缩图片
	 * @param filePath 文件地址
	 * @param reqWidth 所需图片宽度
	 * @param reqHeight	所需图片高度
	 * @return bitmap
	 */
	public static Bitmap bitmapToString(String filePath,int reqWidth,int reqHeight) {
		Bitmap bm = decodeSampledBitmap(filePath, reqWidth, reqHeight);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
		System.out.println("文件大小："+baos.toByteArray().length);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}
	/**
	 * 
	 * @param reqBitmap 
	 * @param reqWidth 所需图片宽度
	 * @param reqHeight	所需图片高度
	 * @return bitmap
	 */
	public static Bitmap bitmapToString(Bitmap reqBitmap,int reqWidth,int reqHeight) {
		Bitmap bm = decodeSampledBitmap(reqBitmap, reqWidth, reqHeight);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
		// Log.i(test,压缩后大小 + baos.toByteArray().length);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}
	/**
	 * 
	 * @param res 
	 * @param reqWidth 所需图片宽度
	 * @param reqHeight	所需图片高度
	 * @return bitmap
	 */
	public static Bitmap bitmapToString(Resources res,int resId,int reqWidth,int reqHeight) {
		Bitmap bm = decodeSampledBitmap(res,resId, reqWidth, reqHeight);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
		// Log.i(test,压缩后大小 + baos.toByteArray().length);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	// 计算图片的缩放值
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int picheight = options.outHeight;
		final int picwidth = options.outWidth;

		int targetheight = picheight;
		int targetwidth = picwidth;
		int inSampleSize = 1;

		if (targetheight > reqHeight || targetwidth > reqWidth) {
			while (targetheight >= reqHeight && targetwidth >= reqWidth) {
				inSampleSize += 1;
				targetheight = picheight / inSampleSize;
				targetwidth = picwidth / inSampleSize;
			}
		}

		return inSampleSize;
	}
   
    /**
     * 
     * @param res
     * @param resId 
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmap(Resources res,int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
         
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
 
    /**
     * @param filepath 文件地址
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmap(String filepath,int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath, options);
 
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filepath, options);
    }
    /**
     * 
     * @param bitmap    
     * @param reqWidth  所需图片压缩尺寸最小宽度
     * @param reqHeight 所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmap(Bitmap bitmap,int reqWidth, int reqHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] data = baos.toByteArray();
         
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }
 
}

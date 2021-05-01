package com.fxw.libray;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 *@Title:文件工具类
 */
public class FileUtils {

	private static FileUtils instance;
	// 文件缓存路径
	private static final String CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator + "demo" + File.separator;

	//下载目录
	private File downloadDir;
	//缓存目录
	private File cacheDir;
	//图片缓存目录
	private File cacheImageDir;
	//语音文件缓存目录
	private File cacheAudioDir;
	//视频文件缓存目录
	private File cacheVideoDir;
	//错误日志目录
	private File errorDir;

	public static FileUtils getInstance() {
		if (instance == null) {
			synchronized (FileUtils.class) {
				if (instance == null) {
					instance = new FileUtils(AppContext.getInstance());
				}
			}
		}
		return instance;
	}

	private FileUtils() {

	}

	private FileUtils(Context context) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			cacheDir = new File(CACHE_DIR, "/cache");
		} else {
			cacheDir = context.getCacheDir();
		}
		if (!cacheDir.exists())
			cacheDir.mkdirs();
		cacheImageDir = new File(CACHE_DIR, "/image/");
		if (!cacheImageDir.exists())
			cacheImageDir.mkdirs();
		downloadDir = new File(CACHE_DIR, "/download/");
		if (!downloadDir.exists())
			downloadDir.mkdirs();
		cacheAudioDir = new File(CACHE_DIR, "/audio/");
		if (!cacheAudioDir.exists())
			cacheAudioDir.mkdirs();
		errorDir = new File(CACHE_DIR, "/error/");
		if (!errorDir.exists())
			errorDir.mkdirs();
		cacheVideoDir = new File(CACHE_DIR, "/video/");
		if (!cacheVideoDir.exists())
			cacheVideoDir.mkdirs();
	}

	/**
	 * 获取缓存目录
	 * @return
	 * @Description:
	 */
	public File getCacheDir() {
		return cacheDir;
	}

	/**
	 * 获取错误日志目录
	 * @return
	 * @Description:
	 */
	public File getErrorDir() {
		return errorDir;
	}

	/**
	 * 创建一个临时图片文件
	 * @return
	 * @Description:
	 */
	public File newTempImageFile() {
		File file = new File(cacheImageDir, System.currentTimeMillis() + ".png");
		return file;
	}

	/**
	 * 创建一个临时语音文件
	 * @return
	 * @Description:
	 */
	public File newTempAudioFile() {
		return new File(cacheAudioDir, System.currentTimeMillis() + ".wav");
	}
	/**
	 * 创建一个临时语音文件
	 * @return
	 * @Description:
	 */
	public File newTempVideoFile() {
		return new File(cacheVideoDir, System.currentTimeMillis() + ".mp4");
	}

	/**
	 * 判断是否安装SD卡
	 * 
	 * @return
	 */
	public static boolean checkSaveLocationExists() {
		String sDCardStatus = Environment.getExternalStorageState();
		boolean status;
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除目录(包括：目录里的所有文件)
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/" + listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();

					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {

					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	public static final String getTempFilePath(Context context) {

		return context.getCacheDir() + File.separator + "temp";

	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.getPath();
	}

	/**
	 * 将图片保存到指定的路径
	 * @param filePath
	 * @param name
	 * @param bitmap
	 * @return
	 */
	public static String savePic(String filePath, String name, Bitmap bitmap) {
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();

		File f = new File(filePath + File.separator + name);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getPath();
	}

	/**
	 * 将图片保存到指定的路径
	 * @param filePath
	 * @param bitmap
	 * @return
	 */
	public static String savePic(String filePath, Bitmap bitmap) {
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getPath();
	}

}
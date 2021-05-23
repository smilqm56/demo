package com.fxw.libray;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/4/26 0026 下午 3:35
 */
public class initImageLoader {

    /**
     * 初始化Imageloader
     */
    public initImageLoader(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
        LruDiskCache diskCache = null;
        try {
            diskCache = new LruDiskCache(FileUtils.getInstance().getCacheDir(), new Md5FileNameGenerator(), 100 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageLoaderConfiguration config = null;
        config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(5)// 线程池内加载的数量
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileCount(100)// 缓存的文件数量
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(diskCache)
                .defaultDisplayImageOptions(options).build();

        ImageLoader.getInstance().init(config);
    }
}

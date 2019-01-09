package com.view.commonbanner.load;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner.load
 * @Description: 图片加载器
 * @date 2019/1/8 14:52
 */
public interface ImageLoadInterface<T extends View> extends Serializable {

		void displayImage(Context context, String url, T view);

		T createView(Context context);
}

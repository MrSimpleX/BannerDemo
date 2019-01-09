package com.demo.bannerdemo;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.view.commonbanner.load.ImageLoadInterface;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.demo.bannerdemo
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/8 16:46
 */
public class ImageLoad implements ImageLoadInterface<SimpleDraweeView> {

		@Override
		public void displayImage(Context context, String url, SimpleDraweeView view) {
				Uri uri = Uri.parse(url);
				view.setImageURI(uri);
		}

		@Override
		public SimpleDraweeView createView(Context context) {
				return new SimpleDraweeView(context);
		}
}

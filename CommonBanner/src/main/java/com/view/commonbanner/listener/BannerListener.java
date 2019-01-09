package com.view.commonbanner.listener;

import android.view.View;
import android.widget.ImageView;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/8 14:09
 */
public interface BannerListener {

		/**
		 * 单击图片事件
		 *
		 * @param position 点击图片位置
		 */
		public void onImageClick(int position);
}

package com.view.commonbanner.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.view.commonbanner.listener.BannerListener;

import java.util.List;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner
 * @Description: 轮播ViewPager适配器
 * @date 2019/1/8 14:07
 */
public class CommonBannerAdapter extends PagerAdapter {

		/**
		 * 轮播内容集合
		 */
		private List<View> mImageViewCacheList;

		/**
		 * 广告图片点击监听器
		 */
		private BannerListener mImageCycleViewListener;

		public CommonBannerAdapter(List<View> bannerList, BannerListener imageCycleViewListener) {
				mImageViewCacheList = bannerList;
				mImageCycleViewListener = imageCycleViewListener;
		}

		@Override
		public int getCount() {
				return mImageViewCacheList.size();
		}

		@Override
		public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
				return view == obj;
		}

		@NonNull
		@Override
		public Object instantiateItem(@NonNull ViewGroup container, final int position) {
				View view = mImageViewCacheList.get(position);
				// 设置图片点击监听
				view.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								mImageCycleViewListener.onImageClick(position);
						}
				});
				container.addView(view);
				return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
		}

}

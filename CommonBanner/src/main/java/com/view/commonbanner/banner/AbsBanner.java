package com.view.commonbanner.banner;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.view.commonbanner.adapter.CommonBannerAdapter;
import com.view.commonbanner.listener.BannerListener;
import com.view.commonbanner.load.ImageLoadInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner
 * @Description: 基类Banner
 * @date 2019/1/9 10:46
 */
public abstract class AbsBanner extends LinearLayout {

		/**
		 * context
		 */
		public Context mContext;
		/**
		 * 轮播主要的ViewPager
		 */
		private ViewPager mAdvPager;
		/**
		 * 主要轮播ViewPager 适配器
		 */
		public CommonBannerAdapter mAdvAdapter;
		/**
		 * 轮播View列表
		 */
		public List<View> mBannerView;
		/**
		 * 图片滚动当前图片下标
		 */
		private int mImageIndex = 0;

		public AbsBanner(Context context) {
				super(context);
		}

		public AbsBanner(Context context, @Nullable AttributeSet attrs) {
				super(context, attrs);
				initAttrs(attrs);
				initView(context);
		}

		public AbsBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
				super(context, attrs, defStyleAttr);
		}

		private void initView(Context context) {
				mContext = context;
				LayoutInflater.from(context).inflate(getBannerId(), this);
				mAdvPager = getViewPager();
				mAdvPager.addOnPageChangeListener(new BannerPageChangeListener());
				mAdvPager.setOnTouchListener(mOnTouchListener);
				initBanner();
		}

		View.OnTouchListener mOnTouchListener = new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
						switch (event.getAction()) {
								case MotionEvent.ACTION_UP:
										// 开始图片滚动
										startImageTimerTask();
										break;
								default:
										// 停止图片滚动
										stopImageTimerTask();
										break;
						}
						return false;
				}
		};

		/**
		 * 装填图片数据
		 *
		 * @param imageUrlList
		 */
		public void setImageResources(List<String> imageUrlList, ImageLoadInterface imageLoad,
		                              BannerListener bannerListener) {
				mAdvPager.setOffscreenPageLimit(imageUrlList.size());

				// 图片广告数量
				final int imageCount = imageUrlList.size();
				onImageLoadPrev(imageCount);
				mBannerView = new ArrayList<>();
				if (imageCount > 1) {
						for (int i = 0; i < imageCount; i++) {
								if (imageLoad != null) {
										View bannerView = imageLoad.createView(mContext);
										imageLoad.displayImage(mContext, imageUrlList.get(i), bannerView);
										mBannerView.add(bannerView);
								}
								onBannerIndex(i);
						}
				}
				mAdvAdapter = new CommonBannerAdapter(mBannerView, bannerListener);
				mAdvPager.setAdapter(mAdvAdapter);
				onImageLoadAfter();
				startImageTimerTask();
		}

		protected  void onImageLoadAfter(){

		}

		/**
		 * 开始轮播(手动控制自动轮播与否，便于资源控制)
		 */
		public void startImageCycle() {
				startImageTimerTask();
		}

		/**
		 * 暂停轮播——用于节省资源
		 */
		public void pushImageCycle() {
				stopImageTimerTask();
		}

		/**
		 * 开始图片滚动任务
		 */
		private void startImageTimerTask() {
				stopImageTimerTask();
				// 图片每3秒滚动一次
				mHandler.postDelayed(mImageTimerTask, 3000);
		}

		/**
		 * 停止图片滚动任务
		 */
		private void stopImageTimerTask() {
				mHandler.removeCallbacks(mImageTimerTask);
		}

		private Handler mHandler = new Handler();

		/**
		 * 图片自动轮播Task
		 */
		private Runnable mImageTimerTask = new Runnable() {

				@Override
				public void run() {
						if (mBannerView != null) {
								// 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
								if ((++mImageIndex) == mBannerView.size()) {
										mImageIndex = 0;
								}
								mAdvPager.setCurrentItem(mImageIndex);
						}
				}
		};

		private final class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

				@Override
				public void onPageScrollStateChanged(int state) {
						//设置轮播相关
						setPagerState(state);
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {

				}

				@Override
				public void onPageSelected(int index) {
						//设置图片下标
						mImageIndex = index;
						//设置下标显示
						setPagerIndex(index);
				}

		}

		/**
		 * 初始化自定义属性
		 *
		 * @param attrs attrs
		 */
		protected abstract void initAttrs(AttributeSet attrs);

		/**
		 * OnPageChangeListener.onPageScrollStateChanged
		 *
		 * @param state ViewPager滚动状态
		 */
		protected abstract void setPagerState(int state);

		/**
		 * OnPageChangeListener.onPageSelected
		 *
		 * @param index ViewPager当前轮播顺序
		 */
		protected abstract void setPagerIndex(int index);

		/**
		 * 获取不同Banner布局，方便扩展
		 *
		 * @return layoutId
		 */
		protected abstract int getBannerId();

		/**
		 * 获取当前使用的ViewPager
		 *
		 * @return ViewPager
		 */
		protected abstract ViewPager getViewPager();

		/**
		 * 构造方法初始化
		 */
		protected abstract void initBanner();

		/**
		 * 添加ViewPager显示内容
		 *
		 * @param i 第几个显示
		 */
		protected abstract void onBannerIndex(int i);

		/**
		 * Banner显示前初始化
		 * @param imageCount 总显示个数
		 */
		protected abstract void onImageLoadPrev(int imageCount);
}

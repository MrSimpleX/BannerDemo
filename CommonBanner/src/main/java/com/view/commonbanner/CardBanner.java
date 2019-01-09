package com.view.commonbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.view.commonbanner.R;
import com.view.commonbanner.banner.AbsBanner;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner.banner
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/9 11:48
 */
public class CardBanner extends AbsBanner {

		private RelativeLayout mRlContent;
		private float mViewMaragen;

		public CardBanner(Context context) {
				super(context);
		}

		public CardBanner(Context context, @Nullable AttributeSet attrs) {
				super(context, attrs);
		}

		public CardBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
				super(context, attrs, defStyleAttr);
		}

		@Override
		protected void initAttrs(AttributeSet attrs) {
				final TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CardBanner);
				mViewMaragen = attributes.getDimension(R.styleable.CardBanner_viewMargin, 0F);
				attributes.recycle();
		}

		@Override
		protected void setPagerState(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE)
						startImageCycle(); // 开始下次计时

				//设置banner可无限滑动
				switch (state) {
						case ViewPager.SCROLL_STATE_IDLE:// 空闲状态，没有任何滚动正在进行（表明完成滚动）
								if (getViewPager().getCurrentItem() == 0) {
										getViewPager().setCurrentItem(mBannerView.size() - 2, false);
								} else if (getViewPager().getCurrentItem() == mBannerView.size() - 1) {
										getViewPager().setCurrentItem(1, false);
								}
								break;
						case ViewPager.SCROLL_STATE_DRAGGING:// 正在拖动page状态
								break;
						case ViewPager.SCROLL_STATE_SETTLING:// 手指已离开屏幕，自动完成剩余的动画效果
								break;
				}
		}

		@Override
		protected void setPagerIndex(int index) {

		}

		@Override
		protected int getBannerId() {
				return R.layout.layout_card_banner;
		}

		@Override
		protected ViewPager getViewPager() {
				return findViewById(R.id.adv_pager);
		}

		@Override
		protected void onImageLoadAfter() {
				super.onImageLoadAfter();
				getViewPager().setCurrentItem(1);
		}

		@Override
		protected void initBanner() {
				mRlContent = findViewById(R.id.ll_content);
				mRlContent.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
								return getViewPager().dispatchTouchEvent(event);
						}
				});
				getViewPager().setPageMargin((int) mViewMaragen);
		}

		@Override
		protected void onBannerIndex(int i) {

		}

		@Override
		protected void onImageLoadPrev(int imageCount) {

		}
}

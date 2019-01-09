package com.view.commonbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.view.commonbanner.R;
import com.view.commonbanner.banner.AbsBanner;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.view.commonbanner.banner
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/9 11:11
 */
public class CommonBanner extends AbsBanner {

		private LinearLayout mGroup;
		private float mMaragenBottom;
		private int mGravity;
		private ImageView[] mImageViews = null;

		public CommonBanner(Context context) {
				super(context);
		}

		public CommonBanner(Context context, @Nullable AttributeSet attrs) {
				super(context, attrs);
		}

		public CommonBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
				super(context, attrs, defStyleAttr);
		}

		@Override
		protected void initBanner() {
				// 滚动图片右下指示器视图
				mGroup = findViewById(R.id.ll_viewGroup);
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mGroup.getLayoutParams();
				layoutParams.setMargins(0,0,0, (int) mMaragenBottom);
				mGroup.setLayoutParams(layoutParams);
				mGroup.setGravity(mGravity);
		}

		@Override
		protected void initAttrs(AttributeSet attrs) {
				final TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CommonBanner);
				mMaragenBottom = attributes.getDimension(R.styleable.CommonBanner_indicatorMarginBottom, 10 + 0.5f);
				mGravity = attributes.getInt(R.styleable.CommonBanner_indicatorGravity, Gravity.CENTER);
				attributes.recycle();
		}

		@Override
		protected void onBannerIndex(int i) {
				ImageView imageView = new ImageView(mContext);
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.setMargins(5, 0, 5, (int) mMaragenBottom);
				imageView.setLayoutParams(params);
				mImageViews[i] = imageView;
				if (i == 0) {
						mImageViews[i]
							.setBackground(mContext.getResources().getDrawable(R.drawable.shape_middle_select));
				} else {
						mImageViews[i]
							.setBackground(mContext.getResources().getDrawable(R.drawable.shape_middle_unselect));
				}
				mGroup.addView(mImageViews[i]);
		}

		@Override
		protected void onImageLoadPrev(int imageCount) {
				// 清除所有子视图
				mGroup.removeAllViews();

				mImageViews = new ImageView[imageCount];
		}

		@Override
		protected void setPagerState(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE)
						startImageCycle(); // 开始下次计时
		}

		@Override
		protected void setPagerIndex(int index) {
				// 设置图片滚动指示器背景
				mImageViews[index]
					.setBackground(mContext.getResources().getDrawable(R.drawable.shape_middle_select));
				for (int i = 0; i < mImageViews.length; i++) {
						if (index != i) {
								mImageViews[i]
									.setBackground(mContext.getResources().getDrawable(R.drawable.shape_middle_unselect));
						}
				}
		}

		@Override
		protected int getBannerId() {
				return R.layout.layout_common_banner;
		}

		@Override
		protected ViewPager getViewPager() {
				return findViewById(R.id.adv_pager);
		}
}

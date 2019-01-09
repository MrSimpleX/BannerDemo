package com.demo.bannerdemo;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.view.commonbanner.load.ImageLoadInterface;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: BannerDemo
 * @Package com.demo.bannerdemo
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/1/9 10:13
 */
public class CardImageLoad implements ImageLoadInterface<SimpleDraweeView> {

		@Override
		public void displayImage(Context context, String url, SimpleDraweeView view) {
				RoundingParams rp = new RoundingParams();
				rp.setCornersRadius(15);
				//获取GenericDraweeHierarchy对象
				GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
					//设置圆形圆角参数
					.setRoundingParams(rp)
					.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
					//构建
					.build();
				view.setHierarchy(hierarchy);
				Uri uri = Uri.parse(url);
				view.setImageURI(uri);
		}

		@Override
		public SimpleDraweeView createView(Context context) {
				return new SimpleDraweeView(context);
		}
}

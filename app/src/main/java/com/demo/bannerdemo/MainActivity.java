package com.demo.bannerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.view.commonbanner.CardBanner;
import com.view.commonbanner.CommonBanner;
import com.view.commonbanner.listener.BannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

		private CommonBanner mCommonBanner;
		private CardBanner mCardBanner;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);

				Fresco.initialize(this);
				mCommonBanner = findViewById(R.id.banner);

				final List<String> imageList = new ArrayList<>();
				imageList.add("http://tupian.qqjay.com/u/2017/1228/2_133548_16.jpg");
				imageList.add("http://pic2.ooopic.com/12/40/58/18bOOOPIC9c.jpg");
				imageList.add("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
				imageList.add("http://tupian.qqjay.com/u/2017/1228/2_133548_16.jpg");
				imageList.add("http://pic2.ooopic.com/12/40/58/18bOOOPIC9c.jpg");

				mCommonBanner.setImageResources(imageList, new ImageLoad(), new BannerListener() {
						@Override
						public void onImageClick(int position) {
								Toast.makeText(MainActivity.this, imageList.get(position), Toast.LENGTH_SHORT).show();
						}
				});

				mCardBanner = findViewById(R.id.cardBanner);

				final List<String> circleList = new ArrayList<>();
				//头部插入一条
				circleList.add("http://tupian.qqjay.com/u/2017/1228/2_133548_16.jpg");
				circleList.add("http://pic2.ooopic.com/12/40/58/18bOOOPIC9c.jpg");
				circleList.add("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
				circleList.add("http://tupian.qqjay.com/u/2017/1228/2_133548_16.jpg");
				//尾部插入一条
				circleList.add("http://pic2.ooopic.com/12/40/58/18bOOOPIC9c.jpg");

				mCardBanner.setImageResources(circleList, new CardImageLoad(), new BannerListener() {
						@Override
						public void onImageClick(int position) {
								Toast.makeText(MainActivity.this, imageList.get(position), Toast.LENGTH_SHORT).show();
						}
				});
		}
}

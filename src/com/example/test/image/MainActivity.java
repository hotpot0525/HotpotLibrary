package com.example.test.image;

import hot.lib.trace.TraceTime;
import hot.lib.trace.TraceTimes;
import hot.lib.trace.android.AndroidTraceTimes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.test.AndroidTestCase;
import android.util.Log;

public class MainActivity extends Activity {
	
	public long beforeTime;
	public MediaStoreTest mMediaStoreTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		beforeTime = System.currentTimeMillis();		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMediaStoreTest = new MediaStoreTest(this);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		final BitmapFactory.Options opt;
		int count = 3;
		final CountDownLatch sig = new CountDownLatch(count);
		
		AndroidTraceTimes.getInstance().startTrace("HOGE");
		// 複数読み込みテスト
		for(int i=0; i<count; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 画像読み込み
					mMediaStoreTest.testNormalLaod();
					sig.countDown();
				}
			}).start();
		}
		try {
			// 中断テスト
			Thread.sleep(100);
//			mMediaStoreTest.loadCanel();
			sig.await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Log.e("Thread All End", "ERROR!");
		}
		AndroidTraceTimes.getInstance().printTrace("HOGE");
		


		
//		findViewById(R.id.image).setBackgroundDrawable(new BitmapDrawable(bitmap));

	}
	
	@Override
	protected void onStop() {
		findViewById(R.id.image).setBackgroundDrawable(null);
		super.onStop();
	}


}

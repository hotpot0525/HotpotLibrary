package com.example.test.image;

import hot.lib.trace.TraceTime;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class BitmapTest {
	
	private Context mContext;
	
	public BitmapTest(Context c) {
		mContext = c;
	}
	
	/**
	 * デフォルトテスト
	 */
	public void bitmapLoadTest(){
		int COUNT = 20;
		TraceTime trace = new TraceTime();
		Resources res = mContext.getResources();
		
		int thum = R.drawable.thum;
		int sample = R.drawable.sample;
		int screen = R.drawable.screen;
		int rock = R.drawable.rock;
		
		
		trace.reset();
		for(int i=0; i< COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, screen);
		}
		Log.e("TIME", "2880x1800(1.0M) time:"+trace.calcTime()/COUNT+"ms");
				
		
		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, sample);
		}
		Log.e("TIME", "640x1136(1.2M) time:"+trace.calcTime()/COUNT+"ms");

		
		trace.reset();
		for(int i=0; i<COUNT;i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, rock);
		}
		Log.e("TIME", "640x960(0.233M) time:"+trace.calcTime()/COUNT+"ms");
		
		
		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, thum);
		}
		Log.e("TIME", "168x168(0.041M) time:"+trace.calcTime()/COUNT+"ms");
	}
	
	
	/**
	 * オプション付きの速度テスト
	 */
	public void bitmapLoadTest2(){
		int COUNT = 20;
		TraceTime trace = new TraceTime();
		Resources res = mContext.getResources();
		
		int thum = R.drawable.thum;
		int sample = R.drawable.sample;
		int screen = R.drawable.screen;
		int rock = R.drawable.rock;
		
		BitmapFactory.Options option565 = new Options();
		option565.inPreferredConfig = Bitmap.Config.RGB_565;
		BitmapFactory.Options option8888 = new Options();
		option8888.inPreferredConfig = Bitmap.Config.ARGB_8888;		
		
		
		trace.reset();
		for(int i=0; i< COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, screen, option565);
		}
		Log.e("TIME", "2880x1800(1.0M) RGB565 time:"+trace.calcTime()/COUNT+"ms");
		
		trace.reset();
		for(int i=0; i< COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, screen, option8888);
		}
		Log.e("TIME", "2880x1800(1.0M) RGB8888 time:"+trace.calcTime()/COUNT+"ms");		
				
		
		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, sample, option565);
		}
		Log.e("TIME", "640x1136(1.2M) RGB565 time:"+trace.calcTime()/COUNT+"ms");

		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, sample, option8888);
		}
		Log.e("TIME", "640x1136(1.2M) RGB8888 time:"+trace.calcTime()/COUNT+"ms");

		
		trace.reset();
		for(int i=0; i<COUNT;i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, rock, option565);
		}
		Log.e("TIME", "640x960(0.233M) RGB565 time:"+trace.calcTime()/COUNT+"ms");

		trace.reset();
		for(int i=0; i<COUNT;i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, rock, option8888);
		}
		Log.e("TIME", "640x960(0.233M) RGB8888 time:"+trace.calcTime()/COUNT+"ms");
		
		
		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, thum, option565);
		}
		Log.e("TIME", "168x168(0.041M) RGB565 time:"+trace.calcTime()/COUNT+"ms");
		
		trace.reset();
		for(int i=0; i<COUNT; i++){
			Bitmap bitmap = BitmapFactory.decodeResource(res, thum, option8888);
		}
		Log.e("TIME", "168x168(0.041M) RGB8888 time:"+trace.calcTime()/COUNT+"ms");		
		
	}	

}

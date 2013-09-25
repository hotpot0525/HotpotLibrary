package com.example.test.image;

import hot.lib.trace.TraceTime;

import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class MediaStoreTest {

	private Context mContext;
	private BitmapFactory.Options mOptions; 

	public MediaStoreTest(Context context) {
		mContext = context;
		if(mOptions == null){
			mOptions = new BitmapFactory.Options();	
		}
		mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
		mOptions.inSampleSize = 2;
	}
	
	
	public Bitmap testNormalLaod(){
		
		TraceTime trace = new TraceTime();
		// 画像リストを取得するクエリを発行
		ContentResolver resolver = mContext.getContentResolver();
		String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
		Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, sortOrder);
		cursor.moveToFirst();
		// 最新画像のキー(URI)を取得
		int fieldIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
		Long imageId = cursor.getLong(fieldIndex);		
		Uri uri = ContentUris.withAppendedId(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId);		

		// まずは画像のメタデータだけ取得
		try{
			mOptions.inJustDecodeBounds= true;
			InputStream inputStream = null;	
			inputStream = resolver.openInputStream(uri);
			BitmapFactory.decodeStream(inputStream, null, mOptions);
		}catch(Exception e){
		}
//		
		// 読み込むサイズを調整
//		WindowManager wm = (WindowManager) mContext.getSystemService(Activity.WINDOW_SERVICE);
//		Display disp = wm.getDefaultDisplay();
//		float scale = (float)disp.getWidth()/(float)mOptions.outWidth;
//		Log.e("SCALE", ""+ Math.round(scale));
//		mOptions.inSampleSize = Math.round(scale);
		mOptions.inSampleSize = 2;
		
		// 画像の読み込み
		InputStream inputStream = null;
		Bitmap bm = null;
		try{
			mOptions.inJustDecodeBounds = false;
			inputStream = resolver.openInputStream(uri);
			bm = BitmapFactory.decodeStream(inputStream, null, mOptions);
		}catch(Exception e){
		}
		if(bm != null)
			Log.e("MediaStoreTest", ""+bm.getWidth()+"x"+bm.getHeight()+" time:"+trace.calcTime());					
		return bm;
	}

	public void loadCanel() {
		if(mOptions == null){
			mOptions = new BitmapFactory.Options();
		}
		mOptions.requestCancelDecode();
		Log.e("CANCEL", "bitmap load CANCEL FLAG UP");
	}
}

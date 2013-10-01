package com.example.test.image;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class TouchEventTestActivity extends Activity{


	private View mTouchArea;
	private TextView mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_touch_event_test);
		
		mTouchArea = findViewById(R.id.touchArea);
		mText = (TextView)findViewById(R.id.pos);
		
		
		mTouchArea.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("TOUCH", "x,y="+event.getX()+","+event.getY());
				mText.setText("x,y="+event.getX()+","+event.getY());

				// 追従
				FrameLayout.LayoutParams params = (LayoutParams) mText.getLayoutParams();
				params.setMargins((int)event.getX(), (int)event.getY()-100, 0, 0);
				params.gravity = Gravity.TOP;
				mText.requestLayout();

				return true;
			}
		});
		
	}
	
	

}

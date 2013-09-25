package DesigneFrameDetailAnimeView;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceThread extends SurfaceView	implements Runnable, SurfaceHolder.Callback {

	/** FPS設定 */
	static final long FPS = 20;
	static final long FRAME_TIME = 1000 / FPS;
	
	/** view用 */
	SurfaceHolder surfaceHolder;
	int screen_width, screen_height;
	
	/** 非同期処理用 */
	Thread thread;


	public SurfaceThread(Context context) {
		super(context);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);		
	}

	@Override
	public void run() {
				
		Canvas canvas = null;
		long loopCount = 0;
		long waitTime = 0;
		long startTime = System.currentTimeMillis();

		while(thread != null){
			try{
				loopCount++;
				
				// canvasに書く				
				canvas = surfaceHolder.lockCanvas();
				onDrawCanvas(canvas);
				surfaceHolder.unlockCanvasAndPost(canvas);
				
				// FPS待機
				waitTime = (loopCount * FRAME_TIME) 
					- System.currentTimeMillis() - startTime;
				if( waitTime > 0 ){
					Thread.sleep(waitTime);
				}
			}
			catch(Exception e){}
		}
	}
	
	private void onDrawCanvas(Canvas canvas){
		
	}

	@Override
	public void surfaceChanged(
		SurfaceHolder holder, 
		int format, 
		int width, 
		int height) {
		screen_width = width;
		screen_height = height;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread = null;
	}

}

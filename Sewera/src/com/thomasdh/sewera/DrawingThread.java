package com.thomasdh.sewera;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {

	private SurfaceHolder surfaceHolder;
	private DrawingView dv;
	private boolean run;
	private final float fps = 50f;
	private final float tickTime = 1000 / fps;

	public DrawingThread(SurfaceHolder surfaceHolder, DrawingView dv) {
		this.surfaceHolder = surfaceHolder;
		this.dv = dv;
		this.run = true;
	}

	void setRunning(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {
		Canvas c;
		while (run) {
			long beginTime = System.currentTimeMillis();
			c = null;

			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					dv.logic();
					dv.doDraw(c);
				}
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			// //SLEEP
			long elapsedTime = System.currentTimeMillis() - beginTime;
			if (elapsedTime > tickTime) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep((long) (tickTime - elapsedTime));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

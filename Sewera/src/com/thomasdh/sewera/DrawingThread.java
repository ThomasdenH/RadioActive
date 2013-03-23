package com.thomasdh.sewera;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {

	private SurfaceHolder surfaceHolder;
	private DrawingView dv;
	private boolean run;

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
			long sleepTime = System.currentTimeMillis() - beginTime;
			if (sleepTime > 10) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

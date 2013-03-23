package com.thomasdh.sewera;

import java.util.ArrayList;

import com.thomasdh.sewera.entity.Player;

import UI.Knop;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {

	private DrawingThread thread;
	private SpritesheetAnimation animation;
	private Player p;
	private long lastTime;
	private DisplayMetrics displaymetrics;
	private ArrayList<Knop> k;

	private Paint antiAlias = new Paint(Paint.ANTI_ALIAS_FLAG);

	public DrawingThread getThread() {
		return thread;
	}

	public DrawingView(Context context) {
		super(context);
		getHolder().addCallback(this);
		animation = new SpritesheetAnimation(BitmapFactory.decodeResource(getResources(), R.drawable.wiel), 4, 100);
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new DrawingThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			thread.setRunning(false); // Tells thread to stop
			thread.join(); // Removes thread from mem.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startAnimation() {
		thread = new DrawingThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();
	}

	public void stopAnimation() {
		try {
			thread.setRunning(false); // Tells thread to stop
			thread.join(); // Removes thread from mem.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void logic() {
		if (p == null) {
			p = new Player(getWidth() / 2 - dpToPixel(100), 
					getHeight() / 2 - dpToPixel(25), 
					0, 
					dpToPixel(100), 
					dpToPixel(25), 
					dpToPixel(50), 
					BitmapFactory.decodeResource(getResources(), R.drawable.person));
			k = new ArrayList<Knop>();
			k.add(new Knop(BitmapFactory.decodeResource(getResources(), R.drawable.knop),
					new Rect(getWidth() - dpToPixel(70), getHeight() - dpToPixel(70), getWidth(), getHeight())) {
				@Override
				public void buttonPressed() {
					p.jump();
				}
			});
		}

		animation.update(System.currentTimeMillis() - lastTime);
		p.update(System.currentTimeMillis() - lastTime);
		lastTime = System.currentTimeMillis();
	}

	void doDraw(Canvas canvas) {
		canvas.drawColor(Color.GREEN);
		canvas.drawBitmap(animation.getImage(), animation.getSourceRect(), new Rect(0, 0, animation.sizeX * 2, animation.sizeY * 2), antiAlias);
		canvas.drawBitmap(p.getImage(), p.getSourceRect(), p.getDestRect(), antiAlias);
		for (Knop knop : k) {
			canvas.drawBitmap(knop.getImage(), knop.getSourceRect(), knop.getDestRect(), antiAlias);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		for (Knop knop : k) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				knop.setPressed(false);
			} else {
				if (knop.checkForPress((int) event.getX(), (int) event.getY())) {
				}
			}
		}
		return true;
	}

	public int pixelToDP(int pixel) {
		if (displaymetrics == null) {
			displaymetrics = getContext().getResources().getDisplayMetrics();
		}
		return (int) ((pixel / displaymetrics.density) + 0.5);
	}

	public int dpToPixel(int dp) {
		if (displaymetrics == null) {
			displaymetrics = getContext().getResources().getDisplayMetrics();
		}
		return (int) ((dp * displaymetrics.density) + 0.5);
	}
}

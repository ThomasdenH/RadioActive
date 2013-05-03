package com.thomasdh.sewera;

import com.thomasdh.sewera.entity.Player;
import com.thomasdh.sewera.game.Images;
import com.thomasdh.sewera.game.Level;
import com.thomasdh.sewera.game.Room;

import UI.Knop;
import UI.TouchPad;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {

	private DrawingThread thread;
	private SpritesheetAnimation animation;
	private Player p;
	private long lastTime;
	private DisplayMetrics displaymetrics;
	private Knop k;
	private TouchPad touchpad;
	private Images imgs;
	private Level l;

	private Paint antiAlias = new Paint(Paint.ANTI_ALIAS_FLAG);

	public DrawingThread getThread() {
		return thread;
	}

	public DrawingView(Context context) {
		super(context);
		getHolder().addCallback(this);
		animation = new SpritesheetAnimation(BitmapFactory.decodeResource(getResources(), R.drawable.wiel), 4, 100);
		lastTime = System.currentTimeMillis();

		imgs = new Images(this);
		l = new Level(imgs);
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
			touchpad = new TouchPad(BitmapFactory.decodeResource(getResources(), R.drawable.movepad), new Rect(0, getHeight() - dpToPixel(140), dpToPixel(140), getHeight())) {
				@Override
				public void buttonPressed() {
					double atan = Math.atan2(this.getyTouchLocation(), this.getxTouchLocation());
					p.setX((float) (p.getX() + 2 * Math.cos(atan)));
					p.setY((float) (p.getY() + 2 * Math.sin(atan)));
					Log.w("touchPad pressed",
							"x is " + this.getxTouchLocation() + ", y is " + this.getyTouchLocation() + ", atan is " + atan + ", the added y value is " + Math.sin(atan));
				}
			};
			p = new Player(getWidth() / 2 - dpToPixel(100),
					getHeight() / 2 - dpToPixel(25),
					0,
					dpToPixel(100),
					dpToPixel(25),
					dpToPixel(50),
					BitmapFactory.decodeResource(getResources(), R.drawable.person));
			k = new Knop(BitmapFactory.decodeResource(getResources(), R.drawable.knop),
					new Rect(getWidth() - dpToPixel(70), getHeight() - dpToPixel(70), getWidth(), getHeight())) {
				@Override
				public void buttonPressed() {
					p.jump();
				}
			};
		}
		touchpad.actionIfPressed();
		animation.update(System.currentTimeMillis() - lastTime);
		p.update(System.currentTimeMillis() - lastTime);
		lastTime = System.currentTimeMillis();
	}

	Rect screen = new Rect(0, 0, 0 + getWidth(), 0 + getHeight());

	void doDraw(Canvas canvas) {
		int translateX = (int) p.getX() - getWidth() / 2;
		int translateY = (int) p.getY() - getHeight() / 2;
		canvas.translate(-translateX, -translateY);
		canvas.drawColor(Color.BLUE);

		screen.set(translateX, translateY, translateX + getWidth(), translateY + getHeight());
		Room cRoom = l.getCurrentRoom();
		for (int x = 0; x < cRoom.level.size(); x++) {
			if (Rect.intersects(cRoom.level.get(x).getDestRect(), screen)) {
				canvas.drawBitmap(cRoom.level.get(x).getImage(), cRoom.level.get(x).getX(), cRoom.level.get(x).getY(), antiAlias);
			}
		}

		canvas.drawBitmap(animation.getImage(), animation.getSourceRect(), new Rect(0, 0, animation.sizeX * 2, animation.sizeY * 2), antiAlias);
		canvas.drawBitmap(p.getImage(), p.getSourceRect(), p.getDestRect(), antiAlias);

		canvas.translate(translateX, translateY);
		canvas.drawBitmap(k.getImage(), k.getSourceRect(), k.getDestRect(), antiAlias);
		canvas.drawBitmap(touchpad.getImage(), touchpad.getSourceRect(), touchpad.getDestRect(), antiAlias);
	}

	int knopPointer, touchpadPointer;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		int pointer;
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			if (!k.checkForPress((int) event.getX(), (int) event.getY(), false))
				touchpad.checkForPress((int) event.getX(), (int) event.getY(), false);
			break;
		case MotionEvent.ACTION_MOVE:
			if (!k.checkForPress((int) event.getX(), (int) event.getY(), false))
				touchpad.checkForPress((int) event.getX(), (int) event.getY(), false);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			pointer = event.getPointerId(1);
			if (!k.checkForPress((int) event.getX(pointer), (int) event.getY(pointer), false))
				touchpad.checkForPress((int) event.getX(pointer), (int) event.getY(pointer), false);
			break;
		case MotionEvent.ACTION_UP:
			if (!k.checkForPress((int) event.getX(), (int) event.getY(), true))
				touchpad.checkForPress((int) event.getX(), (int) event.getY(), true);
			break;
		case MotionEvent.ACTION_CANCEL:
			if (!k.checkForPress((int) event.getX(), (int) event.getY(), true))
				touchpad.checkForPress((int) event.getX(), (int) event.getY(), true);
			break;
		case MotionEvent.ACTION_POINTER_UP:
			pointer = event.getPointerId(1);
			if (!k.checkForPress((int) event.getX(pointer), (int) event.getY(pointer), true))
				touchpad.checkForPress((int) event.getX(pointer), (int) event.getY(pointer), true);
			break;
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

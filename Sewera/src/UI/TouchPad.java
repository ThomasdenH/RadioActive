package UI;

import android.graphics.Bitmap;
import android.graphics.Rect;


public abstract class TouchPad {
	private Bitmap b;
	private int width;
	private int height;
	private int pressed;
	private Rect destRect;
	private int xTouchLocation, yTouchLocation;

	public TouchPad(Bitmap b, Rect destRect) {
		this.b = b;
		this.pressed = 0;
		this.setWidth(b.getWidth() / 2);
		this.setHeight(b.getHeight());
		this.destRect = destRect;
	}

	public void actionIfPressed() {
		if (pressed != 0) {
			this.buttonPressed();
		}
	}

	public Bitmap getImage() {
		return b;
	}

	public Rect getDestRect() {
		return destRect;
	}

	public Rect getSourceRect() {
		if (pressed != 0) {
			return new Rect(getWidth(), 0, 2 * getWidth(), getHeight());
		}
		return new Rect(0, 0, getWidth(), getHeight());
	}

	public boolean checkForPress(int xTouch, int yTouch, boolean end) {
		Rect check = getDestRect();
		if (check.contains(xTouch, yTouch)) {
			setxTouchLocation(xTouch - getX() - getWidth() / 2);
			setyTouchLocation(yTouch - getY() - getHeight() / 2);
			if (end) {
				setPressed(0);
			} else {
				setPressed(1);
			}
			return true;
		} else {
			setPressed(0);
			return false;
		}
	}

	public void setPressed(int pressedd) {
		if (pressedd != 0) {
			pressed = pressedd;
			buttonPressed();
		} else {
			pressed = 0;
		}
	}

	public abstract void buttonPressed();

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getY() {
		return destRect.top;
	}

	public void setY(int y) {
		this.destRect.top = y;
	}

	public int getX() {
		return this.destRect.left;
	}

	public void setX(int x) {
		this.destRect.left = x;
	}

	public int getxTouchLocation() {
		return xTouchLocation;
	}

	public void setxTouchLocation(int xTouchLocation) {
		this.xTouchLocation = xTouchLocation;
	}

	public int getyTouchLocation() {
		return yTouchLocation;
	}

	public void setyTouchLocation(int yTouchLocation) {
		this.yTouchLocation = yTouchLocation;
	}
}

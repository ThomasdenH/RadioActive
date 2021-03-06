package UI;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Knop {
	private Bitmap b;
	private int width;
	private int height;
	private boolean pressed;
	private Rect destRect;

	public Knop(Bitmap b, Rect destRect) {
		this.b = b;
		this.pressed = false;
		this.setWidth(b.getWidth() / 2);
		this.setHeight(b.getHeight());
		this.destRect = destRect;
	}

	public Bitmap getImage() {
		return b;
	}

	public Rect getDestRect() {
		return destRect;
	}

	public Rect getSourceRect() {
		if (pressed == true) {
			return new Rect(getWidth(), 0, 2 * getWidth(), getHeight());
		}
		return new Rect(0, 0, getWidth(), getHeight());
	}

	public boolean checkForPress(int x, int y, boolean end) {
		Rect check = getDestRect();
		if (check.contains(x, y)) {
			if (end) {
				setPressed(false);
			} else {
				setPressed(true);
			}
			return true;
		} else {
			setPressed(false);
			return false;
		}
	}

	public void setPressed(boolean pressedd) {
		if (pressedd) {
			pressed = true;
			buttonPressed();
		} else {
			pressed = false;
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
}

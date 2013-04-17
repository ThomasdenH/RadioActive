package UI;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public abstract class TouchPad {
	private Bitmap b;
	private int width;
	private int height;
	private int pressed;
	private Rect destRect;

	public TouchPad(Bitmap b, Rect destRect) {
		this.b = b;
		this.pressed = 0;
		this.setWidth(b.getWidth() / 2);
		this.setHeight(b.getHeight());
		this.destRect = destRect;
	}
	
	public void actionIfPressed() {
		if(pressed != 0){
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

	public boolean checkForPress(int x, int y, boolean end) {
		Rect check = getDestRect();
		if(check.contains(x, y)){
			if(end){
				setPressed(0);
			}else{
				setPressed(1);
				
			}
			return true;
		}else{
			setPressed(0);
			return false;
		}
	}
	
	public void setPressed(int pressedd){
		if(pressedd != 0){
			pressed = pressedd;
			buttonPressed();
			Log.w("PRessed", "YEs");
		}else{
			Log.w("Pressed", "no");
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
}

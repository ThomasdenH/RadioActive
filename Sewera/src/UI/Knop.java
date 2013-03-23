package UI;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public abstract class Knop {
	private Bitmap b;
	private int width, height;
	private boolean pressed;
	private Rect destRect;

	public Knop(Bitmap b, Rect destRect) {
		this.b = b;
		this.pressed = false;
		this.width = b.getWidth() / 2;
		this.height = b.getHeight();
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
			return new Rect(width, 0, 2 * width, height);
		}
		return new Rect(0, 0, width, height);
	}

	public boolean checkForPress(int x, int y) {
		Rect check = getDestRect();
		if(check.contains(x, y)){
			setPressed(true);
			return true;
		}else{
			setPressed(false);
			return false;
		}
	}
	
	public void setPressed(boolean pressedd){
		if(pressedd){
			pressed = true;
			buttonPressed();
			Log.w("PRessed", "YEs");
		}else{
			Log.w("Pressed", "no");
			pressed = false;
		}
	}

	public abstract void buttonPressed();
}

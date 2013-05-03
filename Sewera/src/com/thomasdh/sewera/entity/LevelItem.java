package com.thomasdh.sewera.entity;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class LevelItem {
	private float x;
	float y;
	float z;
	private Bitmap b;
	private Rect destRect;
	public abstract boolean updatable();
	public abstract boolean collidable();

	public LevelItem(int x, int y, int z, Bitmap b) {
		this.b = b;
		this.setX(x);
		this.y = y;
		this.z = z;
		destRect = new Rect((int) this.getX(), (int) this.z + (int) this.y, (int) this.getX() + (int) b.getWidth(), (int) this.y + (int) this.z + (int) b.getHeight());
	}
	
	public abstract Bitmap getImage();

	public Rect getDestRect() {
		return destRect;
	}

	public Bitmap getB() {
		return b;
	}

	public void setB(Bitmap b) {
		this.b = b;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
}

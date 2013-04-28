package com.thomasdh.sewera.entity;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Entity {
	float x, y, z, sizeX, sizeY, sizeZ;
	Bitmap b;
	private Rect sourceRect;
	private Rect destRect;

	public Entity(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Bitmap b) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		sourceRect = new Rect(0, 0, b.getWidth(), b.getHeight());
		destRect = new Rect((int) this.x, (int) this.z + (int) this.y - (int) this.sizeZ, (int) this.x + (int) this.sizeX, (int) this.y + (int) this.z + (int) this.sizeY);
	}

	public abstract Bitmap getImage();

	public Rect getSourceRect() {
		return sourceRect;
	}

	public Rect getDestRect() {
		return destRect;
	}

	public void update(long deltaTime) {
		updateLocation(deltaTime);
		updateRects();
	}

	public abstract void updateLocation(long deltaTime);

	public void updateRects() {
		destRect.set((int) x, (int) z + (int) y - (int) sizeZ, (int) x + (int) sizeX, (int) y + (int) z + (int) sizeY);
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

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	public float getSizeZ() {
		return sizeZ;
	}

	public void setSizeZ(float sizeZ) {
		this.sizeZ = sizeZ;
	}

}

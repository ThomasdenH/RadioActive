package com.thomasdh.sewera.entity;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Entity {
	int x, y, z, sizeX, sizeY, sizeZ;
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
		destRect = new Rect(this.x, this.z + this.y - this.sizeZ, this.x + this.sizeX, this.y + this.z + this.sizeY);
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
		destRect.set(x, z + y - sizeZ, x + sizeX, y + z + sizeY);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getSizeZ() {
		return sizeZ;
	}

	public void setSizeZ(int sizeZ) {
		this.sizeZ = sizeZ;
	}

}

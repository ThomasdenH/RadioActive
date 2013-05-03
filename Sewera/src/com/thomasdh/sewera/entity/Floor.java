package com.thomasdh.sewera.entity;

import com.thomasdh.sewera.game.Images;

import android.graphics.Bitmap;

public class Floor extends LevelItem {

	public Floor(int x, int y, int z, Images imgs) {
		super(x, y, z, imgs.getFloor());
	}

	@Override
	public Bitmap getImage() {
		return this.getB();
	}

	@Override
	public boolean updatable() {
		return false;
	}

	@Override
	public boolean collidable() {
		return false;
	}

}

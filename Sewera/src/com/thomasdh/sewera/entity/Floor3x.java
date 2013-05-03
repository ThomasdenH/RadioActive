package com.thomasdh.sewera.entity;

import com.thomasdh.sewera.game.Images;

import android.graphics.Bitmap;

public class Floor3x extends LevelItem {

	public Floor3x(int x, int y, int z, Images imgs) {
		super(x, y, z, imgs.getFloor3x());

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

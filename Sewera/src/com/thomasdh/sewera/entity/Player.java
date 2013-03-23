package com.thomasdh.sewera.entity;

import android.graphics.Bitmap;
import android.util.Log;

import com.thomasdh.sewera.SpritesheetAnimationMultipleLayers;

public class Player extends MovingEntity {

	private boolean jumping;
	private int jumpAmount;

	public Player(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Bitmap b) {
		super(x, y, z, sizeX, sizeY, sizeZ, new SpritesheetAnimationMultipleLayers(b, 2, 200, 1));
		jumping = false;
	}

	@Override
	public void updateLocation(long deltaTime) {
		Log.w("Jump Amount", "" + jumpAmount);
		if (jumping) {
			z += jumpAmount;
			jumpAmount++;
		}
		
		if (z >= 0) {
			jumping = false;
		}
	}

	public void jump() {
		if (jumping == false) {
			jumping = true;
			jumpAmount = -10;
		}
	}
}

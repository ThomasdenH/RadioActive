package com.thomasdh.sewera.entity;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.thomasdh.sewera.SpritesheetAnimation;

public abstract class MovingEntity extends Entity {

	private SpritesheetAnimation animation;

	public MovingEntity(int x, int y, int z, int sizeX, int sizeY, int sizeZ, SpritesheetAnimation a) {
		super(x, y, z, sizeX, sizeY, sizeZ, a.getImage());
		this.animation = a;
		this.b = animation.getImage();
	}

	@Override
	public Bitmap getImage() {
		return animation.getImage();

	}
	
	public SpritesheetAnimation getAnimation(){
		return animation;
	}

	public void updateAnimation(long deltaTime) {
		animation.update(deltaTime);
	}

	@Override
	public void update(long deltaTime) {
		updateAnimation(deltaTime);
		super.update(deltaTime);
	}
	
	@Override
	public Rect getSourceRect() {
		return animation.getSourceRect();
	}
	
}

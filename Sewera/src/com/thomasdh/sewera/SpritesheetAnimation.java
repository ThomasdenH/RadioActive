package com.thomasdh.sewera;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class SpritesheetAnimation {
	int sizeX, sizeY, frameNumber, totalFrames, interval;
	Bitmap image;
	Rect sourceRect;
	long totalTime;

	public SpritesheetAnimation(Bitmap limage, int ltotalFrames, int linterval) {
		sizeY = limage.getHeight();
		image = limage;
		interval = linterval;
		sourceRect = new Rect(0, 0, sizeX, sizeY);
		totalFrames = ltotalFrames;
		sizeX = image.getWidth() / ltotalFrames;
	}

	public void update(long time) {
		totalTime += time;
		frameNumber = (int) ((totalTime) / interval) % totalFrames;
		sourceRect.set(frameNumber * sizeX, 0, (frameNumber + 1)* sizeX, sizeY);
	}

	public Rect getSourceRect() {
		return sourceRect;
	}
	
	public Bitmap getImage(){
		return image;
	}
}

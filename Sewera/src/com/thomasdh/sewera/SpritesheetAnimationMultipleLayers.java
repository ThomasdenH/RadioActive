package com.thomasdh.sewera;

import android.graphics.Bitmap;

public class SpritesheetAnimationMultipleLayers extends SpritesheetAnimation {
	int layers, currentLayer, layerHeight;

	public SpritesheetAnimationMultipleLayers(Bitmap limage, int ltotalFrames, int linterval, int llayers) {
		super(limage, ltotalFrames, linterval);
		layers = llayers;
		sizeY /= llayers;
	}

	public void setLayer(int llayer) {
		currentLayer = llayer;
	}

	@Override
	public void update(long time) {
		totalTime += time;
		frameNumber = (int) ((totalTime) / interval) % totalFrames;
		sourceRect.set(frameNumber * sizeX, currentLayer * sizeY, (frameNumber + 1) * sizeX, (currentLayer + 1) * sizeY);
		/*Log.w("Animatie", "Left " + sourceRect.left);
		Log.w("Animatie", "Right " + sourceRect.right);
		Log.w("Animatie", "Top " + sourceRect.top);
		Log.w("Animatie", "Bottom " + sourceRect.bottom);*/
	}
}

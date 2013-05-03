package com.thomasdh.sewera.game;

import com.thomasdh.sewera.DrawingView;
import com.thomasdh.sewera.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Images {
	Bitmap floor, floor3x;

	public Images(DrawingView view) {
		setFloor(BitmapFactory.decodeResource(view.getResources(), R.drawable.floor));
		setFloor3x(BitmapFactory.decodeResource(view.getResources(), R.drawable.floorlarger));
	}

	public Bitmap getFloor() {
		return floor;
	}

	public void setFloor(Bitmap floorImage) {
		this.floor = floorImage;
	}

	public Bitmap getFloor3x() {
		return floor3x;
	}

	public void setFloor3x(Bitmap floor3x) {
		this.floor3x = floor3x;
	}

}

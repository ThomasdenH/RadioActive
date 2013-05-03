package com.thomasdh.sewera.game;

import java.util.ArrayList;

import com.thomasdh.sewera.entity.Floor3x;
import com.thomasdh.sewera.entity.LevelItem;

public class Room {

	public ArrayList<LevelItem> level;
	private int tilesX = 64, tilesY = 64;

	public Room(Images imgs) {

		level = new ArrayList<LevelItem>();
		for (int y = 0; y < getTilesY(); y++) {
			for (int x = 0; x < getTilesX(); x++) {
				level.add(new Floor3x(x * imgs.getFloor3x().getWidth(), y * imgs.getFloor3x().getWidth(), 0, imgs));
			}
		}
	}

	public int getTilesX() {
		return tilesX;
	}

	public void setTilesX(int tilesX) {
		this.tilesX = tilesX;
	}

	public int getTilesY() {
		return tilesY;
	}

	public void setTilesY(int tilesY) {
		this.tilesY = tilesY;
	}
}

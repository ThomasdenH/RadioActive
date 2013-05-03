package com.thomasdh.sewera.game;

import java.util.ArrayList;

public class Level {

	private ArrayList<Room> rooms;
	private Images imgs;
	private int currentRoom;
	
	public Level(Images imgs){
		setRooms(new ArrayList<Room>());
		getRooms().add(new Room(imgs));
	}
	
	public void addRoom(){
		getRooms().add(new Room(imgs));
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public Room getCurrentRoom(){
		return this.rooms.get(currentRoom);
	}
	
}

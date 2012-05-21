package com.gej.map;

import java.util.ArrayList;

public class MapManager {
	
	private static ArrayList<String> maps       = new ArrayList<String>();
	private static int               currentMap = 0;
	
	public static void addMap(String mapName){
		maps.add(mapName);
	}
	
	public static boolean hasNextMap(){
		if (maps.size()>currentMap){
			return true;
		}
		return false;
	}
	
	public static boolean hasPreviousMap(){
		if (currentMap>0){
			return true;
		}
		return false;
	}
	
	public static void loadNextMap(MapLoader loader){
		if (hasNextMap()){
			currentMap++;
			Map.loadMap(maps.get(currentMap), loader);
		}
	}
	
	public static void loadPreviousMap(MapLoader loader){
		if (hasPreviousMap()){
			currentMap--;
			Map.loadMap(maps.get(currentMap), loader);
		}
	}
	
	public static void reloadMap(MapLoader loader){
		Map.loadMap(maps.get(currentMap), loader);
	}
	
	public static void loadNullMap(){
		Map.clearObjects();
	}
	
	public static void loadMap(int mapNum, MapLoader loader){
		if (maps.size()<mapNum){
			currentMap = mapNum;
			Map.loadMap(maps.get(currentMap), loader);
		}
	}

}
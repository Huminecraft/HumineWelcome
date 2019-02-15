package com.aymegike.huminewelcome.manager;

import java.util.ArrayList;

import com.aymegike.huminewelcome.utils.Map;

public class MapManager {
	
	public static final String PATH = "plugins/huminewelcome/cinematic_map";

	private ArrayList<Map> maps;
	
	public MapManager() {
		maps = new ArrayList<Map>();
	}
	
	public void addMap(Map map) {
		maps.add(map);
	}
	
	public void removeMap(Map map) {
		maps.remove(map);
	}
	
	public Map getMap(String name) {
		for (Map map : getMaps()) {
			if (map.getName().equals(name)) {
				return map;
			}
		}
		return null;
	}
	
	public ArrayList<Map> getMaps() {
		return maps;
	}
	
	
}

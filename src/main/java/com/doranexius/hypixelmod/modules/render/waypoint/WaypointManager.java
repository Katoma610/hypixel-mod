package com.doranexius.hypixelmod.modules.render.waypoint;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;

public class WaypointManager {
	
	private static Map<String, Triple<Integer, Integer, Integer>> waypointList = new HashMap<String, Triple<Integer,Integer,Integer>>();
	
	public static void addWaypoint(String name, int x, int y, int z) {
		waypointList.put(name, Triple.of(x, y, z));
	}
	
	public static void deleteWaypoint(String name) {
		waypointList.remove(name);
	}
	
	public static Map<String, Triple<Integer, Integer, Integer>> getWaypointList(){
		return waypointList;
	}
	
}

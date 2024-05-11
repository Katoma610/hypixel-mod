package com.doranexius.hypixelmod.commands;

import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PrintUtils {
	
	public static void print(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
	}
	
	public static void printCommands() {
		print("§6[Hypixel Mod]§6§3 Here are all the arguments for the main /hypixelmod command: ");
		
		for (String arg : CommandManager.getCommandList().keySet()) {
			print(String.format(" - §3/hypixelmod %s - %s", arg, CommandManager.getCommandList().get(arg)));
		}
	}
	
	public static void printWaypoints() {
		
		String waypoint = "";
		
		for (String command : WaypointManager.getWaypointList().keySet()) {
			waypoint += command + ", ";
		}
		
		print("§6[Hypixel Mod]§6§3 Here are all the active waypoint: " + waypoint);
	}
}

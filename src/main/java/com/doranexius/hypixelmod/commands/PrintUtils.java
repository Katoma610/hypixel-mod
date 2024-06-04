package com.doranexius.hypixelmod.commands;

import com.doranexius.hypixelmod.HypixelMod;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PrintUtils {
	
	public static void print(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
	}
	
	public static void printCommands() {
		print(HypixelMod.BASE_MESSAGE_START + "Here are all the arguments for the main /hypixelmod command: ");
		
		for (String arg : MainCommand.getCommandList().keySet()) {
			print(String.format(" - §b/hypixelmod %s - %s", arg, MainCommand.getCommandList().get(arg)));
		}
	}
	
	public static void printWaypoints() {
		
		String waypoint = "";
		
		for (String command : WaypointManager.getWaypointList().keySet()) {
			waypoint += command + ", ";
		}
		
		print(HypixelMod.BASE_MESSAGE_START + "Here are all the active waypoint: " + waypoint);
	}
}

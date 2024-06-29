package com.doranexius.hypixelmod.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doranexius.hypixelmod.HypixelMod;
import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.gui.NewMainGUI;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;

import com.doranexius.hypixelmod.utils.PrintUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class MainCommand implements ICommand {
	
	private static Map<String, String> commandList = new HashMap<String, String>();
	
	public static Map<String, String> getCommandList() {
		return commandList;
	}
	
	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "hypixelmod";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "This is a main command for all the modules of Hypixel Mod!";
	}

	@Override
	public List<String> getCommandAliases() {
		List<String> commandAlliases = new ArrayList<String>();
		commandAlliases.add("hmod");
		return commandAlliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		commandList.put("commandlist", "Prints this list.");
		commandList.put("addwaypoint {name}", "Adds a waypoint with that name at player's coordinates.");
		commandList.put("addwaypoint {name} {x} {y} {z}", "Adds a waypoint with that name at x, y, z coordinates.");
		commandList.put("delwaypoint {name}", "Removes a waypoint with that name.");
		
		if (sender instanceof EntityPlayer) {
			if (args.length > 0) {
				
				if (args[0].equals("addwaypoint") && args.length > 1) {
					if (args.length == 5) {
						WaypointManager.addWaypoint(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
						PrintUtils.print(String.format(HypixelMod.BASE_MESSAGE_START + "Added waypoint %s at x: %d y: %d z: %d.", args[1] , Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
					} else if (args.length == 2) {
						EntityPlayer player = Minecraft.getMinecraft().thePlayer;
						WaypointManager.addWaypoint(args[1], (int) player.posX, (int) player.posY, (int) player.posZ);
						PrintUtils.print(HypixelMod.BASE_MESSAGE_START + "Added a waypoint " + args[1] + " at player's coordinates.");
					}
				} else if (args[0].equals("delwaypoint") && args.length > 1) {
					if (WaypointManager.getWaypointList().containsKey(args[1])) {
						WaypointManager.deleteWaypoint(args[1]);
						PrintUtils.print(HypixelMod.BASE_MESSAGE_START + "Deleted waypoint " + args[1]);
					}
				} else if (args[0].equals("commandlist")) {
					PrintUtils.printCommands();
				} else if (args[0].equals("waypoints")) {
					PrintUtils.printWaypoints();
				}
				
				else {
					PrintUtils.print(HypixelMod.BASE_MESSAGE_START + "Invalid argument(s) for command! Type /hypixelmod commandlist for help!");
				}
				
			} else {
				ModClientEventHandler.guiToDisplay = new NewMainGUI();
				PrintUtils.print(HypixelMod.BASE_MESSAGE_START + "This is a main command of Hypixel Mod!");
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
	// TODO: Create a command that enables modules
}

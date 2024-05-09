package com.doranexius.hypixelmod.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.gui.MainGUI;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.Fullbright;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class CommandManager implements ICommand {
	
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
		
		commandList.put("mobesp", "Toggles MobESP.");
		commandList.put("modulelist", "Prints the list of all modules.");
		commandList.put("commandlist", "Prints this list.");
		commandList.put("chestesp", "Toggles ChestESP.");
		commandList.put("tracers", "Toggles Tracers.");
		commandList.put("fullbright", "Toggles Fullbright.");
		
		if (sender instanceof EntityPlayer) {
			if (args.length > 0) {
				
				if (args[0].equals("mobesp")) {
					ModuleManager.getModList().get(0).toggle();
					PrintUtils.print(String.format("§6[Hypixel Mod]§6§3 Set MobESP to: %s", ModuleManager.getModList().get(0).isToggled()));
				} else if (args[0].equals("modulelist")) {
					PrintUtils.printModules();
				} else if (args[0].equals("commandlist")) {
					PrintUtils.printCommands();
				} else if (args[0].equals("chestesp")) {
					ModuleManager.getModList().get(1).toggle();
					PrintUtils.print(String.format("§6[Hypixel Mod]§6§3 Set ChestESP to: %s", ModuleManager.getModList().get(1).isToggled()));
				} else if (args[0].equals("tracers")) {
					ModuleManager.getModList().get(2).toggle();
					PrintUtils.print(String.format("§6[Hypixel Mod]§6§3 Set Tracers to: %s", ModuleManager.getModList().get(2).isToggled()));
				} else if (args[0].equals("fullbright")) {
					//Fullbright.toggleFullbright();
					//PrintUtils.print(String.format("§6[Hypixel Mod]§6§3 Set Fullbright to: %s", Fullbright.isFBToggled()));
				} else {
					PrintUtils.print("§6[Hypixel Mod]§6§c Invalid argument(s) for command! Type /hypixelmod commandlist for help!");
				}
				
			} else {
				ModClientEventHandler.guiToDisplay = new MainGUI();
				PrintUtils.print("§6[Hypixel Mod]§6§3 This is a main command of Hypixel Mod! Current implemented modules: MobESP, ChestESP, Tracers.");
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

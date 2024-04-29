package com.doranexius.hypixelmod.commands;

import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PrintUtils {
	
	public static void print(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
	}
	
	public static void printModules() {
		print("§6[Hypixel Mod]§6§3 Here are all the modules:");
		
		for (Module module : ModuleManager.getModList()) {
			String iEnabled = module.isToggled() ? "§aEnabled§a" : "§cDisabled§c";
			print(String.format(" - §3%s§3 : %s", module.getName(), iEnabled));
		}
	}
	
	public static void printCommands() {
		print("§6[Hypixel Mod]§6§3 Here are all the arguments for the main /hypixelmod command: ");
		
		for (String arg : CommandManager.getCommandList().keySet()) {
			print(String.format(" - /hypixelmod §3%s - %s", arg, CommandManager.getCommandList().get(arg)));
		}
	}
}

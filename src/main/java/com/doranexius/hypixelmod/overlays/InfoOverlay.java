package com.doranexius.hypixelmod.overlays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class InfoOverlay extends GuiIngame {
	
	public static ArrayList<String> moduleNamesList = new ArrayList<String>();
	
	public InfoOverlay(Minecraft mc) {
		super(mc);
	}
	
	public static void printInfo(String name) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fontRenderer = mc.fontRendererObj;
		
		fontRenderer.drawString(name, 4, 4, 0x18d41b);
		fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 70, 4, 0x18d41b);
		
		int listStartY = 25;
		Collections.sort(moduleNamesList, Comparator.comparing(String::length).reversed());
		
		for (String moduleName : moduleNamesList) {
			fontRenderer.drawString(moduleName, 4, listStartY, 0x18d41b);
			listStartY += 12;
		}
	}
}

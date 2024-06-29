package com.doranexius.hypixelmod.overlays;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class InfoOverlay extends GuiIngame {
	
	public static ArrayList<String> moduleNamesList = new ArrayList<String>();
	private static long colorChangeDelay = 75;
	private static int[] colorCodes = {
            0xFF094EFB,
            0xFF005BFF,
            0xFF006CF1,
            0xFF0077EC,
            0xFF0080EC,
            0xFF0089EE,
            0xFF0091F1,
            0xFF0099F6,
            0xFF00A1FA,
            0xFF00A9FF,
            0xFF00B1FF,
            0xFF00B9FF,
            0xFF00C1FF,
            0xFF1EC9FF,
            0xFF43D0FF,
            0xFF5CD7FF,
            0xFF71DEFD,
            0xFF85E5FD,
            0xFF98ECFC,
            0xFFAAF2FD
    };
	
	private static long startTime = System.currentTimeMillis();
	private static int colorInd = 1;
	private static int colorInc = 1;
	private static int currentColor = colorCodes[colorInd];
	
	public InfoOverlay(Minecraft mc) {
		super(mc);
	}
	
	public static void printInfo(String name) {
		
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fontRenderer = mc.fontRendererObj;
		
		fontRenderer.drawString(name, 4, 4, currentColor);
		fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 70, 4, currentColor);
		
		int listStartY = 25;
		Collections.sort(moduleNamesList, Comparator.comparing(String::length).reversed());
		
		for (String moduleName : moduleNamesList) {
			fontRenderer.drawString(" - " + moduleName, 4, listStartY, currentColor);
			listStartY += 12;
		}
		
		if (colorInd == colorCodes.length - 1 || colorInd == 0) {
			colorInc = -colorInc;
			colorInd += colorInc;
		}
		
		if (Math.abs(startTime - System.currentTimeMillis()) >= colorChangeDelay) {
			colorInd += colorInc;
			startTime = System.currentTimeMillis();
		}
		
		currentColor = colorCodes[colorInd];
	}
	
	public static int getCurrentGradientColor() {
		return currentColor;
	}

	public static int[] getColorCodes() {
		return colorCodes;
	}
}

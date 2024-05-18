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
	
	
	private static ArrayList<Integer> colorList = new ArrayList<Integer>(Arrays.asList(0x094efb, 0x006ef0, 0x0082ec, 0x0093f3, 0x00a4fc, 0x00b5ff, 0x02c6ff, 0x56d6ff, 0x83e4fd, 0xaaf2fd));
	private static int[] colorCodes = {
            0x094EFB,
            0x005BFF,
            0x006CF1,
            0x0077EC,
            0x0080EC,
            0x0089EE,
            0x0091F1,
            0x0099F6,
            0x00A1FA,
            0x00A9FF,
            0x00B1FF,
            0x00B9FF,
            0x00C1FF,
            0x1EC9FF,
            0x43D0FF,
            0x5CD7FF,
            0x71DEFD,
            0x85E5FD,
            0x98ECFC,
            0xAAF2FD
    };
	
	private static long startTime = System.currentTimeMillis();
	private static Color startColor = new Color(8, 76, 251);
	private static Color endColor = new Color(173, 243, 253);
	private static int colorInd = 1;
	private static int colorInc = 1;
	
	public InfoOverlay(Minecraft mc) {
		super(mc);
	}
	
	public static void printInfo(String name) {
		int currentColor = colorCodes[colorInd];
		//int hex = Integer.parseInt(String.format("F%02X%02X%02X", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue()), 16);
		
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
		
		
	}
	
	private static int getNextGradientColor() {
		//float progress = (System.currentTimeMillis() - startTime) / 1000f % 2f;
        //float percent = reverseDirection ? 1f - progress : progress;
        
//		int red = (int) (startColor.getRed() * percent + endColor.getRed() * (1 - percent));
//		int green = (int) (startColor.getGreen() * percent + endColor.getGreen() * (1 - percent));
//		int blue = 252;
//		
//		int currentColor = (0xFF << 24) | (red << 16) | (green << 8) | blue;
//		
//		red = Math.max(Math.min(red, 173), 8);
//        green = Math.max(Math.min(green, Math.min(blue, 243)), 76);
		
//		if (Math.abs(currentColor - colorToRGB(startColor)) == 10 || Math.abs(currentColor - colorToRGB(endColor)) == 10) {
//			reverseDirection = !reverseDirection;
//		}
        
        
		
		return 1;
	}
	
	private static int colorToRGB(Color color) {
        int alpha = 0xFF; // Fully opaque
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        // Combine alpha (shifted by 24 bits), red, green, and blue
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}

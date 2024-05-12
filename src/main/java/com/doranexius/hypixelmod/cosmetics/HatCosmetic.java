package com.doranexius.hypixelmod.cosmetics;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.renderUtils.RainbowHatRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;

public class HatCosmetic {
	
	private static double rainbowSpeedInc = 0.01;
	private static double r = 0.5;
	private static double g = 0.5;
	private static double b = 0.5;
	
	public static void drawHat() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;
		
		GL11.glTranslated(0, 1.9, 0);
		RainbowHatRenderer.drawCircle(0.6, 231, 121, 231);
		GL11.glTranslated(0, -1.9, 0);
		
//		r += rainbowSpeedInc;
//		g += rainbowSpeedInc;
//		b += rainbowSpeedInc;
//		
//		System.out.println(String.format("%f %f %f", r,g,b));
//		
//		if (r > 1 || g > 1 || b > 1 || r < 0.1 || g < 0.1 || b < 0.1) {
//			rainbowSpeedInc = -rainbowSpeedInc;
//		}
	}
	
}

package com.doranexius.hypixelmod.cosmetics;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.renderUtils.RainbowHatRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;

public class HatCosmetic {
	
	private static double redSpeedInc = 1;
	private static double greenSpeedInc = 1;
	private static double blueSpeedInc = 1;
	private static double r = 121;
	private static double g = 20;
	private static double b = 12;
	
	public static void drawHat() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;
		
		GL11.glTranslated(0, 1.9, 0);
		RainbowHatRenderer.drawCircle(0.6, r, g, b);
		GL11.glTranslated(0, -1.9, 0);
		
		r += redSpeedInc;
		g += greenSpeedInc;
		b += blueSpeedInc;
		
		System.out.println(String.format("%f %f %f", r,g,b));
		
		if (r > 200 || r < 10) {
			redSpeedInc = -redSpeedInc;
		} else if (g > 200 || g < 10) {
			greenSpeedInc = -greenSpeedInc;
		} else if (b > 200 || b < 10) {
			blueSpeedInc = -blueSpeedInc;
		}
	}
	
}

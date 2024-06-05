package com.doranexius.hypixelmod.cosmetics;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.RainbowHatRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class HatCosmetic {
	
	private static double redSpeedInc = 1;
	private static double greenSpeedInc = 1;
	private static double blueSpeedInc = 1;
	private static double r = 121;
	private static double g = 20;
	private static double b = 12;
	
	private static double heightIncIfSneaking;
	
	public static void drawHat() {
		//GL11.glDisable(GL11.GL_DEPTH_TEST);
		GlStateManager.enableBlend();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		heightIncIfSneaking = Minecraft.getMinecraft().thePlayer.isSneaking() ? 0.4 : 0;
		
		GL11.glTranslated(0, 1.9-heightIncIfSneaking, 0);
		RainbowHatRenderer.drawHat(0.6, r, g, b);
		GL11.glTranslated(0, -1.9+heightIncIfSneaking, 0);
		
		r += redSpeedInc;
		g += greenSpeedInc;
		b += blueSpeedInc;
		
		if (r > 200 || r < 10) {
			redSpeedInc = -redSpeedInc;
		} else if (g > 200 || g < 10) {
			greenSpeedInc = -greenSpeedInc;
		} else if (b > 200 || b < 10) {
			blueSpeedInc = -blueSpeedInc;
		}
		
		GlStateManager.disableBlend();
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
}

package com.doranexius.hypixelmod.cosmetics;

import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.RainbowHatRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class HatCosmetic {
	
	public static void drawHat(float partialTicks) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		RainbowHatRenderer.drawHat0(player, partialTicks);
	}
	
}

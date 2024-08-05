package com.doranexius.hypixelmod.cosmetics;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.RainbowHatRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class HatCosmetic extends Module {

	public HatCosmetic() {
		super("China Hat", Category.COSMETIC);
	}

	public static void drawHat(float partialTicks) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		RainbowHatRenderer.drawHat0(player, partialTicks);
	}

	@Override
	public void onEnable(float partialTicks) {
		drawHat(partialTicks);
	}
}

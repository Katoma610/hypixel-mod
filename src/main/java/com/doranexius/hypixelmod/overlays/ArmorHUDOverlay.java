package com.doranexius.hypixelmod.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;

// TODO: Add this overlay as a GUI module
public class ArmorHUDOverlay extends GuiIngame {
	
	public ArmorHUDOverlay(Minecraft mcIn) {
		super(mcIn);
	}

	public static void renderArmorOverlay() {
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		int startX = 390;
		int y = 441;
		for (int i = 3; i >= 0; i--) {
			itemRenderer.renderItemAndEffectIntoGUI(player.getCurrentArmor(i), startX, y);
			itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, player.getCurrentArmor(i), startX, y, "");
			startX += 20;
		}
		
	}
	
}

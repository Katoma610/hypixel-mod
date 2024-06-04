package com.doranexius.hypixelmod.overlays;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;

public class ArmorHUDOverlay extends Module {

	private static boolean isToggled = false;

	public ArmorHUDOverlay() {
		super("ArmorHUD", Category.HUD);
	}

	public static void renderArmorOverlay() {
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		int startX = 490;
		int y = 445;
		for (int i = 3; i >= 0; i--) {
			itemRenderer.renderItemAndEffectIntoGUI(player.getCurrentArmor(i), startX, y);
			itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, player.getCurrentArmor(i), startX, y, "");
			startX += 20;
		}
		
	}
	
	@Override
	public void onEnable() {
		renderArmorOverlay();
	}

	@Override
	public void toggle(){
		super.toggle();
		isToggled = !isToggled;
	}

	public static boolean getToggled() {
		return isToggled;
	}
	
}

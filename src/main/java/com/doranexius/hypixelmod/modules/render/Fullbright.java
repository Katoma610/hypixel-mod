package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.overlays.InfoOverlay;

import net.minecraft.client.Minecraft;

public class Fullbright extends Module{

	public Fullbright() {
		super("Fullbright", Category.RENDER);
	}
	
	public static float lastFullbightSetting = Minecraft.getMinecraft().gameSettings.gammaSetting;
	private static boolean isToggled = false;
	
	@Override
	public void toggle() {
		isToggled = !isToggled;
		System.out.println(Minecraft.getMinecraft().gameSettings.gammaSetting);		
		
		if (isToggled) {
			Minecraft.getMinecraft().gameSettings.gammaSetting = 1000;
			InfoOverlay.moduleNamesList.add("Fullbright");
		} else {
			Minecraft.getMinecraft().gameSettings.gammaSetting = lastFullbightSetting;
			InfoOverlay.moduleNamesList.remove("Fullbright");
		}
	}
	
	@Override
	public boolean isToggled() {
		return isToggled;
	}
	
	public static void checkFullbright() {
		if (!isToggled && Minecraft.getMinecraft().gameSettings.gammaSetting > 1) {
			Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
			lastFullbightSetting = 1;
		}
	}

}

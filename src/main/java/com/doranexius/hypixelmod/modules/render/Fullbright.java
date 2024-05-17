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
		if (Minecraft.getMinecraft().gameSettings.gammaSetting != 1000) {
			Minecraft.getMinecraft().gameSettings.gammaSetting = 1000;
		} else {
			Minecraft.getMinecraft().gameSettings.gammaSetting = lastFullbightSetting;
		}
		
		isToggled = !isToggled;
		
		if (isToggled) {
			InfoOverlay.moduleNamesList.add("Fullbright");
		} else {
			InfoOverlay.moduleNamesList.remove("Fullbright");
		}
	}
	
	@Override
	public boolean isToggled() {
		return isToggled;
	}
	
//	public static boolean isFBToggled() {
//		return isToggled;
//	}

}

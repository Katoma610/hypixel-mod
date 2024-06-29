package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

public class ShowInvisibleEntities extends Module {
	
	public static boolean isToggled = false;
	
	public ShowInvisibleEntities() {
		super("Invis. Entities", Category.RENDER);
	}
	
	@Override
	public void toggle() {
		super.toggle();
		ShowInvisibleEntities.isToggled = !ShowInvisibleEntities.isToggled;
	}
}

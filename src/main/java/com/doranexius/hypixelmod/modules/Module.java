package com.doranexius.hypixelmod.modules;

import net.minecraft.client.Minecraft;

public class Module {
	
	protected Minecraft mc = Minecraft.getMinecraft();
	private String name;
	private boolean toggled;
	private Category category;
	
	public Module(String name, Category c) {
		this.name = name;
		this.category = c;
		toggled = false;
	}
	
	public void toggle() {
		toggled = !toggled;
//		if (toggled) {
//			onEnable();
//		} else {
//			onDisable();
//		}
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onRender() {}
	public void onUpdate() {}

	public Minecraft getMc() {
		return mc;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isToggled() {
		return toggled;
	}

	
}

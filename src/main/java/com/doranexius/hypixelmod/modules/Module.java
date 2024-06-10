package com.doranexius.hypixelmod.modules;

import com.doranexius.hypixelmod.overlays.InfoOverlay;

public class Module {
	
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
		System.out.println(String.format("%s is toggled to: %b", this.name, this.toggled));
		if (toggled) {
			InfoOverlay.moduleNamesList.add(this.name);
		} else {
			InfoOverlay.moduleNamesList.remove(this.name);
		}
	}
	
	public void onEnable(float partialTicks) {}
	public void onDisable() {}
	public void onRender() {}
	public void onUpdate() {}
	
	public String getName() {
		return name;
	}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public Category getCategory() {
		return category;
	}

	
}

package com.doranexius.hypixelmod.modules;

import com.doranexius.hypixelmod.overlays.InfoOverlay;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.Map;

public class Module {
	
	private String name;
	private boolean toggled;
	private Category category;
	private HashMap<String, Boolean> options = new HashMap<>();
	
	public Module(String name, Category c) {
		this.name = name;
		this.category = c;
		toggled = false;
	}

	public Module(String name, Category c, HashMap<String, Boolean> options) {
		this.name = name;
		this.category = c;
		this.options = options;
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

	public HashMap<String, Boolean> getOptions() {
		return options;
	}
}

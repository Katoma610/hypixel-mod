package com.doranexius.hypixelmod.modules;

import java.util.ArrayList;

public class ModuleManager {
	
	private static ArrayList<Module> mods = new ArrayList<Module>();
	
	public ModuleManager() {
		
	}
	
	public static void newMod(Module module) {
		mods.add(module);
	}
	
	public static ArrayList<Module> getModList() {
		return mods;
	}
	
	public static void onUpdate() {
		for (Module module : mods) {
			module.onUpdate();
		}
	}
	
	public static void onRender() {
		for (Module module : mods) {
			module.onRender();
		}
	}
	
}

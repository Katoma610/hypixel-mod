package com.doranexius.hypixelmod.modules;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;

import com.doranexius.hypixelmod.modules.render.esp.MobESP;
import com.mojang.realmsclient.dto.RealmsServer.McoServerComparator;

public class ModuleManager {
	
	private static ArrayList<Module> mods = new ArrayList<Module>();
	private static ArrayList<Module> renderMods = new ArrayList<Module>();
	
	public ModuleManager() {
		// RENDER
		
	}
	
	public static void newMod(Module module) {
		mods.add(module);
	}
	
	public static void newRenderMod(Module module) {
		renderMods.add(module);
	}
	
	public static ArrayList<Module> getModList() {
		return mods;
	}
	
	public static ArrayList<Module> getRenderModList() {
		return renderMods;
	}
	
	public static void mergeModList(ArrayList<Module> modules) {
		mods.addAll(modules);
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

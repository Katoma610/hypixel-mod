package com.doranexius.hypixelmod.renderUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class RenderGUI extends GuiIngame {
	
	public RenderGUI(Minecraft mc) {
		super(mc);
	}
	
	public static void printClientName(String name) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fontRenderer = mc.fontRendererObj;
		
		//drawRect(2, 2, 95, 14, 0x080707);
		fontRenderer.drawString(name, 4, 4, 0x18d41b);
		fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 70, 4, 0x18d41b);
		
	}
}

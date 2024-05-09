package com.doranexius.hypixelmod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class CategoryButton extends GuiButton {
	
	private int buttonX;
	private int buttonY;
	private String buttonText;
	private String defaultButtonText;
	public int buttonId;
	private String category;
	
	
	public CategoryButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.buttonX = x;
		this.buttonY = y;
		this.buttonText = buttonText;
		this.buttonId = buttonId;
		this.category = buttonText;
		
		this.defaultButtonText = buttonText;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		drawString(mc.fontRendererObj, buttonText, buttonX, buttonY, 0xFF787878);
	}
	
	public void setButtonText(String text) {
		this.buttonText = text;
	}
	
	public void restoreDefaultButtonText() {
		this.buttonText = defaultButtonText;
	}
	
	public String getCategory() {
		return category;
	}
}

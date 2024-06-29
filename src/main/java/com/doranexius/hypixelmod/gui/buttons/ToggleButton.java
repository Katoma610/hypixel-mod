package com.doranexius.hypixelmod.gui.buttons;

import com.doranexius.hypixelmod.modules.Category;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ToggleButton extends GuiButton {
	
	private int buttonX;
	private int buttonY;
	private boolean isToggled;
	private int buttonId;
	private Category category;
	
	public ToggleButton(int buttonId, int x, int y, boolean isToggled, Category category) {
		super(buttonId, x, y, 16, 16, "");
		this.buttonX = x;
		this.buttonY = y;
		this.isToggled = isToggled;
		this.buttonId = buttonId;
		this.category = category;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		
//		drawRect(buttonX + 1, buttonY + 1, buttonX + 17, buttonY + 17, 0xFF000000);
//		drawRect(buttonX, buttonY, buttonX + 16, buttonY + 16, 0xFF000000);
//		drawRect(buttonX + 1, buttonY + 1, buttonX + 15, buttonY + 15, 0xFF464646);
		
		if (isToggled) {
			drawRect(buttonX + 2, buttonY + 2, buttonX + 12, buttonY + 12, 0xFF00FF00);
		} else {
			drawRect(buttonX + 2, buttonY + 2, buttonX + 12, buttonY + 12, 0xFFFF0000);
		}
	}
	
	public boolean isToggled() {
		return isToggled;
	}
	
	public void toggle() {
		isToggled = !isToggled;
	}
	
	public int getButtonId() {
		return buttonId;
	}
	
	public Category getCategory() {
		return category;
	}

}

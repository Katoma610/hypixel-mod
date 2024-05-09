package com.doranexius.hypixelmod.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ToggleButton extends GuiButton {
	
	private int buttonX;
	private int buttonY;
	private boolean isToggled;
	private int buttonId;
	
	public ToggleButton(int buttonId, int x, int y, boolean isToggled) {
		super(buttonId, x, y, 16, 16, "");
		this.buttonX = x;
		this.buttonY = y;
		this.isToggled = isToggled;
		this.buttonId = buttonId;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		
		drawRect(buttonX + 1, buttonY + 1, buttonX + 17, buttonY + 17, 0xFF000000);
		drawRect(buttonX, buttonY, buttonX + 16, buttonY + 16, 0xFF000000);
		drawRect(buttonX + 1, buttonY + 1, buttonX + 15, buttonY + 15, 0xFF464646);
		
		if (isToggled) {
			drawRect(buttonX + 3, buttonY + 3, buttonX + 13, buttonY + 13, 0xFF22B14C);
		} else {
			drawRect(buttonX + 3, buttonY + 3, buttonX + 13, buttonY + 13, 0xFFED1C24);
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

}

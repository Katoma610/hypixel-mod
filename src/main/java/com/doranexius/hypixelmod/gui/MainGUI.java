package com.doranexius.hypixelmod.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.gui.buttons.CategoryButton;
import com.doranexius.hypixelmod.gui.buttons.ToggleButton;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.overlays.InfoOverlay;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import com.doranexius.hypixelmod.modules.Module;

public class MainGUI extends GuiScreen {
	
	private String currCategory = "None";
	private CategoryButton lastCategoryButton = null;
	
	private List<ToggleButton> renderGuiButtons = new ArrayList<ToggleButton>();
	
	private int toggleButtonStartIndex;
	
	@Override
	public void initGui() {
		
		super.initGui();
		
		GL11.glPushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.disableTexture2D();
		
		int x = width / 2 + 120;
		int y = height / 2 - 160;
		
		int heightInc = 25;
		int id = 1;
		for (Category category: Category.values()) {
			buttonList.add(new CategoryButton(id, width / 2 - 175, height / 2 - 185 + heightInc, 50, 12, category.toString()));
			heightInc += 25;
			id += 1;
		}
		
		toggleButtonStartIndex = id;
		
		for (Module module : ModuleManager.getRenderModList()) {
			ToggleButton button = new ToggleButton(id, x, y, false);
			if (ModuleManager.getRenderModList().get(id - toggleButtonStartIndex).isToggled()) {
				button.toggle();
			} 
			
			renderGuiButtons.add(button);
			id += 1;
			y += 25; // Space between buttons
		}
		
		
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GL11.glPopMatrix();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawRect(width / 2 - 202, height / 2 - 202, width / 2 + 202, height / 2 + 202, InfoOverlay.getCurrentGradientColor());
		drawRect(width / 2 - 200, height / 2 - 200, width / 2 + 200, height / 2 + 200, 0xFF3B4341);
		drawCenteredString(fontRendererObj, "Hypixel Mod v1.0", width / 2 - 150, height / 2 - 185, 0x18d41b);
		drawRect(width / 2 - 100, height / 2 - 200, width / 2 - 105, height / 2 + 200, 0xFF000000);
		
		if (!currCategory.equals("None")) {
			drawCenteredString(fontRendererObj, currCategory + " Modules", width / 2 + 50, height / 2 - 190, 0xFF787878);
			if (currCategory.equals("RENDER")) {
				this.drawRenderCategory(width / 2 - 100, height / 2 - 200, 300, 400);
			} else if (currCategory.equals("MOVEMENT")) {
				this.drawMovementCategory(width / 2 - 100, height / 2 - 200, 300, 400);
			}
		} else {
			drawCenteredString(fontRendererObj, "No category is selected.", width / 2 + 50, height / 2, 0xFF787878);
			drawCenteredString(fontRendererObj, "Select the module category from the list on the left.", width / 2 + 50, height / 2 + 15, 0xFF787878);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			ToggleButton toggleButton = (ToggleButton) button;
			toggleButton.toggle();
			
			if (currCategory.equals("RENDER")) {
				ModuleManager.getRenderModList().get(toggleButton.getButtonId() - toggleButtonStartIndex).toggle();
			}
			
		} else if (button instanceof CategoryButton) {
			CategoryButton categoryButton = (CategoryButton) button;
			currCategory = categoryButton.getCategory();
			if (lastCategoryButton != null) {
				lastCategoryButton.restoreDefaultButtonText();
			}
			
			if (currCategory.equals("RENDER")) {
				for (ToggleButton guiButton : renderGuiButtons) {
					buttonList.add((GuiButton) guiButton);
				}
				
			} else {
				for (ToggleButton guiButton : renderGuiButtons) {
					if (buttonList.contains((GuiButton) guiButton)) {
						buttonList.remove((GuiButton) guiButton);
					}
				}
			}
			
			categoryButton.setButtonText("§f§n" + categoryButton.getCategory() + "§n§f");
			lastCategoryButton = categoryButton;
		}
	}
	
	private void drawRenderCategory(int x, int y, int width, int height) {
		int startX = x + width/3;
		int startY = y + height/9;
		
		for (Module module : ModuleManager.getRenderModList()) {
			drawCenteredString(fontRendererObj, module.getName(), startX, startY, 0xFF787878);
			startY += 25; // Space between module names
		}
	}
	
	private void drawMovementCategory(int x, int y, int width, int height) {
		drawCenteredString(fontRendererObj, "This category is empty due to", x+width/2, y+height/2, 0xFF787878);
		drawCenteredString(fontRendererObj, "the lack of Movement modules.", x+width/2, y+height/2+15, 0xFF787878);
		drawCenteredString(fontRendererObj, "They're coming in the future!", x+width/2, y+height/2+30, 0xFF787878);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}

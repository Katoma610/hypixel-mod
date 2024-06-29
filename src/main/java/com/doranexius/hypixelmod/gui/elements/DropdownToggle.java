package com.doranexius.hypixelmod.gui.elements;

import com.doranexius.hypixelmod.gui.buttons.ToggleButton;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DropdownToggle extends GuiButton {

    private int buttonX, buttonY, width, height, defaultY;
    private HashMap<String, Boolean> options;
    private List<ToggleButton> buttons;
    private boolean isToggled;
    private Module module;
    private Category category;

    public DropdownToggle(int buttonId, int x, int y, Module module) {
        super(buttonId, x + Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) + 10, y, 100 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) - 10, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 10, "");
        this.buttonX = x;
        this.buttonY = y;
        this.defaultY = buttonY;
        this.width = 100;
        this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 10;
        this.module = module;
        this.isToggled = false;
        this.options = module.getOptions();
        this.buttons = new ArrayList<>();
        this.category = module.getCategory();

        int id = buttonId + 1;
        int startY = this.buttonY + this.height + 17;
        if (!options.isEmpty()) {
            for (String key : options.keySet()) {
                this.buttons.add(new ToggleButton(id, this.buttonX + this.width / 2 + 30, startY, options.get(key), this.module.getCategory()));
                ++id;
                startY += 20;
            }
        }
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        int heightInc = 0;
        if (this.isToggled && !this.options.isEmpty()) {
            heightInc = 20 * options.size() + 15;
        }
        drawRect(this.buttonX, this.buttonY, this.buttonX + this.width, this.buttonY + this.height + heightInc, 0xFF000000);

        int startY = this.buttonY + this.height + 20;

        if (this.isToggled && !this.options.isEmpty()) {
            GlStateManager.pushMatrix();
            drawHorizontalLine(this.buttonX + this.width / 2 - 30, this.buttonX + this.width / 2 + 30, this.buttonY + this.height + 5, 0xFFFFFFFF);
            //GlStateManager.scale(0.95, 0.95, 1);

            for (String key : options.keySet()) {
                drawString(Minecraft.getMinecraft().fontRendererObj, key, this.buttonX + this.width / 2 - 30, startY, 0xFFFFFFFF);
                startY += 20;
            }

            GlStateManager.popMatrix();
        }
    }

    public boolean isToggled() {
        return isToggled;
    }

    public void toggle() {
        this.isToggled = !this.isToggled;
    }

    public int getOptionsLength() {
        return options.size();
    }

    public Module getModule() {
        return module;
    }

    public List<ToggleButton> getButtons() {
        return buttons;
    }

    public void setY(int y) {
        this.buttonY = y;
    }

    public int getY() {
        return this.buttonY;
    }

    public Category getCategory() {
        return this.category;
    }

    public void updateY(boolean isOpened, int size) {
        if (isOpened && size > 0) {
            this.buttonY += size * 20 + 15;
        } else {
            this.buttonY = defaultY;
        }
        this.yPosition = buttonY;
    }

    private void updateToggles() {

    }
}

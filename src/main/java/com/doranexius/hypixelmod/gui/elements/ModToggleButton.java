package com.doranexius.hypixelmod.gui.elements;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ModToggleButton extends GuiButton {

    private int toggleX, toggleY, width, height, color, defaultY;
    private String toggleText;
    private Module module;
    private boolean isToggled;
    private Category category;

    public ModToggleButton(int buttonId, int x, int y, Module module) {
        super(buttonId, x, y, Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) + 10, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 10, module.getName());
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName());
        this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 10;
        this.toggleX = x;
        this.toggleY = y;
        this.defaultY = toggleY;
        this.toggleText = module.getName();
        this.module = module;
        this.isToggled = module.isToggled();
        this.color = this.isToggled ? 0xFFFFFFFF : 0xFF696969;
        this.category = module.getCategory();
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        drawString(Minecraft.getMinecraft().fontRendererObj, this.toggleText, this.toggleX + 10, this.toggleY + this.height / 3, this.color);
    }

    public Module getModule() {
        return this.module;
    }

    public void toggle() {
        this.isToggled = !this.isToggled;
        if (this.isToggled) {
            this.color = 0xFFFFFFFF;
        } else {
            this.color = 0xFF696969;
        }
        this.module.toggle();
    }

    public boolean isToggled() {
        return this.isToggled;
    }

    public Category getCategory() {
        return this.category;
    }

    public void updateY(boolean isOpened, int size) {
        if (isOpened && size > 0) {
            this.toggleY += size * 20 + 15;
        } else {
            this.toggleY = defaultY;
        }
        this.yPosition = toggleY;
    }
}

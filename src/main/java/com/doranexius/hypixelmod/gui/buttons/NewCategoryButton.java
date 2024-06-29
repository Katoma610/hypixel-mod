package com.doranexius.hypixelmod.gui.buttons;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.overlays.InfoOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NewCategoryButton extends GuiButton {

    private String buttonText;
    private String defaultText;
    private int buttonX;
    private int buttonY;
    private int buttonWidth;
    private int buttonHeight;
    private int buttonId;
    private Category category;
    private boolean isEnabled = false;

    private int i;
    private List<Integer> colors = Arrays.stream(InfoOverlay.getColorCodes()).boxed().collect(Collectors.toList());

    public NewCategoryButton(int buttonId, int x, int y, String buttonText, Category category) {
        super(buttonId, x, y, 10 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(buttonText), 10 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, buttonText);
        this.buttonText = buttonText;
        this.defaultText = buttonText;
        this.buttonId = buttonId;
        this.buttonWidth = 10 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(buttonText);
        this.buttonHeight = 10 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        this.buttonX = x;
        this.buttonY = y;
        this.category = category;
        Collections.reverse(this.colors);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (i < this.colors.toArray().length) {
            drawRect(this.buttonX, this.buttonY, this.buttonX + this.buttonWidth, this.buttonY + this.buttonHeight, 0xFF000000);
            drawCenteredString(mc.fontRendererObj, buttonText, this.buttonX + this.buttonWidth / 2, this.buttonY + this.buttonHeight / 3, this.colors.get(i));
            ++i;
        } else if (this.isEnabled) {
            drawRect(this.buttonX, this.buttonY, this.buttonX + this.buttonWidth, this.buttonY + this.buttonHeight, 0xFF000000);
            drawCenteredString(mc.fontRendererObj, buttonText, this.buttonX + this.buttonWidth / 2, this.buttonY + this.buttonHeight / 3, this.colors.get(this.colors.toArray().length - 1));
        } else {
            drawRect(this.buttonX, this.buttonY, this.buttonX + this.buttonWidth, this.buttonY + this.buttonHeight, 0xFF000000);
            drawCenteredString(mc.fontRendererObj, buttonText, this.buttonX + this.buttonWidth / 2, this.buttonY + this.buttonHeight / 3, 0xFFFFFFFF);
        }
    }

    public void startAnim() {
        i = 0;
    }

    public void restoreDefaultText() {
        this.buttonText = defaultText;
    }

    public void setButtonText(String text) {
        this.buttonText = text;
    }

    public Category getCategory() {
        return category;
    }

    public void enable() {
        this.isEnabled = true;
        this.startAnim();
    }

    public void disable() {
        this.isEnabled = false;
        this.restoreDefaultText();
    }
}

package com.doranexius.hypixelmod.gui;

import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.overlays.InventoryOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.io.IOException;

public class HUDEditorGUI extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();


    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int overlayX = InventoryOverlay.getX();
        int overlayY = InventoryOverlay.getY();
        int overlayWidth = InventoryOverlay.getWidth();
        int overlayHeight = InventoryOverlay.getHeight();

        if (mouseButton == 0 && mouseX >= overlayX && mouseX <= overlayX + overlayWidth && mouseY >= overlayY && mouseY <= overlayY + overlayHeight) {
            InventoryOverlay.enableDragging();
            InventoryOverlay.setMouseDraggingInitX(mouseX);
            InventoryOverlay.setMouseDraggingInitY(mouseY);
            InventoryOverlay.setMouseDraggedX(mouseX);
            InventoryOverlay.setMouseDraggedY(mouseY);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        InventoryOverlay.disableDragging();
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        InventoryOverlay.setMouseDraggedX(mouseX);
        InventoryOverlay.setMouseDraggedY(mouseY);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        int width = sr.getScaledWidth();
        int height = sr.getScaledHeight();
        drawCenteredString(mc.fontRendererObj, "Drag and drop elements", width / 2, height / 2, 0xFFF5D422);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}

package com.doranexius.hypixelmod.overlays;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class InventoryOverlay extends Module {

    private static boolean isToggled = false;
    private static int overlayX = 100;
    private static int overlayY = 100;
    private static final int overlayWidth = 9 * 20 + 10;
    private static final int overlayHeight = 3 * 20 + 10;
    private static boolean isDragging = false;
    private static int mouseDraggingInitX;
    private static int mouseDraggingInitY;
    private static int mouseDraggedX;
    private static int mouseDraggedY;

    public InventoryOverlay() {
        super("Inventory", Category.HUD);
    }

    // TODO: Implement overlay dragging while chat is open
    public static void renderInventory(int mouseX, int mouseY) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        InventoryPlayer inventory = player.inventory;
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        int color = isDragging ? 0xFF000000 : 0x33000000;
        if (isDragging) {
            overlayX += mouseDraggedX - mouseDraggingInitX;
            overlayY += mouseDraggedY - mouseDraggingInitY;
            mouseDraggingInitX = mouseDraggedX;
            mouseDraggingInitY = mouseDraggedY;
        }

        GuiScreen.drawRect(overlayX, overlayY, overlayX + overlayWidth, overlayY + overlayHeight, color);

        int startY = overlayY + 5;
        int startX = overlayX + 5;
        for (int i = 9; i < 36; i++) {
            ItemStack item = inventory.getStackInSlot(i);
            String itemStackSizeString = "";
            if (item != null) {
                itemStackSizeString = item.stackSize == 1 ? "" : item.stackSize + "";
            }

            itemRenderer.renderItemAndEffectIntoGUI(item, startX, startY);
            itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, item, startX, startY, itemStackSizeString);

            if ((i + 1) % 9 == 0) {
                startY += 20;
                startX = overlayX + 5;
            } else {
                startX += 20;
            }
        }
    }

    @Override
    public void toggle() {
        super.toggle();
        isToggled = !isToggled;
    }

    public static boolean getToggled() {
        return isToggled;
    }

    public static int getX() {
        return overlayX;
    }

    public static int getY() {
        return overlayY;
    }

    public static int getWidth() {
        return overlayWidth;
    }

    public static int getHeight() {
        return overlayHeight;
    }

    public static void addToOverlayX(int newX) {
        overlayX += newX;
    }

    public static void addToOverlayY(int newY) {
        overlayY += newY;
    }

    public static void enableDragging() {
        isDragging = true;
    }

    public static void disableDragging() {
        isDragging = false;
    }

    public static boolean isDragging() {
        return isDragging;
    }

    public static void setMouseDraggingInitX(int newX) {
        mouseDraggingInitX = newX;
    }

    public static void setMouseDraggingInitY(int newY) {
        mouseDraggingInitY = newY;
    }

    public static void setMouseDraggedX(int x) {
        mouseDraggedX = x;
    }

    public static void setMouseDraggedY(int y) {
        mouseDraggedY = y;
    }
}

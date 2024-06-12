package com.doranexius.hypixelmod.modules.render.esp;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.utils.BoundingBoxUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class ChestESP extends Module {
	
	public ChestESP() {
		super("ChestESP", Category.RENDER);
	}
	
	public static void drawBlockOutline() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();
		
		for (TileEntity block : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
			if (block instanceof TileEntityChest) {
				GL11.glTranslated(lastX + block.getPos().getX()+0.5, lastY + block.getPos().getY(), lastZ + block.getPos().getZ()+0.5);
				BoundingBoxUtils.renderBoundingBox(1, 1, 0, 255, 0);
				lastX = -block.getPos().getX()-0.5;
				lastY = -block.getPos().getY();
				lastZ = -block.getPos().getZ()-0.5;
			}
		}
		//GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onEnable(float partialTicks) {
		drawBlockOutline();
	}
	
}

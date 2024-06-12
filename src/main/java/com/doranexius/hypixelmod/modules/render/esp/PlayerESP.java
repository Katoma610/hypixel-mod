package com.doranexius.hypixelmod.modules.render.esp;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.utils.BoundingBoxUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerESP extends Module {

	public PlayerESP() {
		super("PlayerESP", Category.RENDER);
	}
	
	public static void drawPlayerESP() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();

	    for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
			if (player.getDisplayNameString().contains(" ") || player.equals(Minecraft.getMinecraft().thePlayer)) continue;

	    	GL11.glTranslated(lastX+player.posX, lastY+player.posY, lastZ+player.posZ);
    		lastX = -player.posX;
    		lastY = -player.posY;
    		lastZ = -player.posZ;
			BoundingBoxUtils.renderBoundingBox(1, 2, 0, 0, 255);
	    }
		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	    //GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
	
	@Override
	public void onEnable(float partialTicks) {
		drawPlayerESP();
	}

}

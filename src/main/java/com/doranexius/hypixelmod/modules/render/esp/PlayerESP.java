package com.doranexius.hypixelmod.modules.render.esp;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.renderUtils.RenderBoundingBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
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
		
	    for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
	    	GL11.glTranslated(lastX+entity.posX, lastY+entity.posY, lastZ+entity.posZ);
    		lastX = -entity.posX;
    		lastY = -entity.posY;
    		lastZ = -entity.posZ;
    		
	    	if (entity instanceof EntityPlayer) {
	    		RenderBoundingBox.renderBB(1, 2, 0, 0, 255);
			}
	    }
	    
	    GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
	
	@Override
	public void onEnable() {
		drawPlayerESP();
	}

}

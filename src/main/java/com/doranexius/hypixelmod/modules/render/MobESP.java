package com.doranexius.hypixelmod.modules.render;

import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.renderUtils.RenderBoundingBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

public class MobESP extends Module {
	
	@SideOnly(Side.CLIENT)
	public MobESP() {
		super("MobESP", Category.RENDER);
	}
	
	public static void drawESPBoxes() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;
		
	    for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
	    	GL11.glTranslated(lastX+entity.posX, lastY+entity.posY, lastZ+entity.posZ);
    		lastX = -entity.posX;
    		lastY = -entity.posY;
    		lastZ = -entity.posZ;
    		
	    	if(entity instanceof EntitySkeleton) {
	    		RenderBoundingBox.renderBB(1, 2, 255, 0, 0);
	    	} else if (entity instanceof EntityPlayer) {
	    		RenderBoundingBox.renderBB(1, 2, 0, 0, 255);
			}
	    }
	    
	    GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
}

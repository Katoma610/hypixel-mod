package com.doranexius.hypixelmod.modules.render.esp;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.BoundingBoxUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

import java.util.HashMap;
import java.util.Map;

public class MobESP extends Module {

	@SideOnly(Side.CLIENT)
	public MobESP() {
		super("MobESP", Category.RENDER, new HashMap<String, Boolean>() {{
			put("Hostile", false);
			put("Passive", false);
		}});
	}
	
	public static void drawMobESP() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();
		GlStateManager.resetColor();

	    for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
	    	GL11.glTranslated(lastX+entity.posX, lastY+entity.posY, lastZ+entity.posZ);
    		lastX = -entity.posX;
    		lastY = -entity.posY;
    		lastZ = -entity.posZ;
    		
	    	if(entity instanceof EntitySkeleton) {
	    		BoundingBoxUtils.renderBoundingBox(1, 2, 255, 0, 0);
	    	}
	    }
		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	    //GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
	
	@Override
	public void onEnable(float partialTicks) {
		drawMobESP();
	}
}

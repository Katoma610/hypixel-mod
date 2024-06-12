package com.doranexius.hypixelmod.modules.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;

public class Tracers extends Module {
	
	public Tracers() {
		super("Tracers", Category.RENDER);
	}

	public static void drawTracers() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();
		
		for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
			if (entity instanceof EntitySkeleton) {
				
				GL11.glColor3d(200, 50, 100);
			    GL11.glLineWidth(2.0f);
			    
			    GL11.glBegin(GL11.GL_LINE_STRIP);
				
				GL11.glVertex3d(0,0,0);
				GL11.glVertex3d(entity.posX - playerX, entity.posY - playerY, entity.posZ - playerZ);
				
				GL11.glEnd();
			}
		}

		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onEnable(float partialTicks) {
		drawTracers();
	}
	
}

package com.doranexius.hypixelmod.modules.render.waypoints;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;

public class WaypointUtils {
	
	public static void drawNametag(String name, BlockPos blockPos) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		FontRenderer fRenderer = Minecraft.getMinecraft().fontRendererObj;
		
		float f = 1.6F;
        float f1 = 0.016666668F * f;
        
        double distance = Minecraft.getMinecraft().thePlayer.getDistanceSq(blockPos) / 150;
        
        if (distance < 1.25) {
			distance = 1.25;
		} else if (distance > 6) {
			distance = 6;
		}
		
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef((float) (-f1 * distance), (float) (-f1 * distance), (float) (f1 * distance));
		
        Gui gui = new Gui();
        gui.drawCenteredString(fRenderer, name, 0, 0, 0xFFFF0000);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
}

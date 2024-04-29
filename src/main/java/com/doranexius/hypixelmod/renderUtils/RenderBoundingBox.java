package com.doranexius.hypixelmod.renderUtils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Vec3;

public class RenderBoundingBox {
	
	public RenderBoundingBox() {
		
	}
	
	// TODO: Add support for other mob types
	public static void renderBB(double width, double height, double r, double g, double b) {
		// Some necessary properties
		GL11.glColor3d(r, g, b);
	    GL11.glLineWidth(2.0f);
	    
		// Top Part
	    GL11.glBegin(GL11.GL_LINE_STRIP);
	    
		GL11.glVertex3d(width / 2, height, width / 2);
		GL11.glVertex3d(width / 2, height, -width / 2);
		GL11.glVertex3d(-width / 2, height, -width / 2);
		GL11.glVertex3d(-width / 2, height, width / 2);
		GL11.glVertex3d(width / 2, height, width / 2);
		// Bottom Part
		GL11.glVertex3d(width / 2, 0, width / 2);
		GL11.glVertex3d(width / 2, 0, -width / 2);
		GL11.glVertex3d(-width / 2, 0, -width / 2);
		GL11.glVertex3d(-width / 2, 0, width / 2);
		GL11.glVertex3d(width / 2, 0, width / 2);
		// Side Parts
		GL11.glVertex3d(width / 2, 0, -width / 2);
		GL11.glVertex3d(width / 2, height, -width / 2);
		GL11.glVertex3d(-width / 2, height, -width / 2);
		GL11.glVertex3d(-width / 2, 0, -width / 2);
		GL11.glVertex3d(-width / 2, 0, width / 2);
		GL11.glVertex3d(-width / 2, height, width / 2);
		
		GL11.glEnd();
	}
	
}

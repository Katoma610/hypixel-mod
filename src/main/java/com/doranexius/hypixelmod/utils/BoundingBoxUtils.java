package com.doranexius.hypixelmod.utils;

import org.lwjgl.opengl.GL11;

public class BoundingBoxUtils {

	public static void renderBoundingBox(double width, double height, double r, double g, double b) {
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

package com.doranexius.hypixelmod.utils;

import org.lwjgl.opengl.GL11;

public class RainbowHatRenderer {
	
	public static void drawHat(double radius, double r, double g, double b) {
		
		
		
//		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
//        for (int i = 0; i <= 360; i += 10) {
//            GL11.glVertex3d(Math.sin(Math.toRadians(i)) * radius, 0.0f+centerY, Math.cos(Math.toRadians(i)) * radius);
//        }
//		GL11.glEnd();
		
		
		
		GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int i = 0; i <= 360; i += 1) {
        	GL11.glColor4d(r / 255.0, g / 255.0, b / 255.0, 0.7f);
    		GL11.glLineWidth(1.0f);
            double x1 = Math.sin(Math.toRadians(i)) * radius;
            double z1 = Math.cos(Math.toRadians(i)) * radius;
            double x2 = Math.sin(Math.toRadians(i + 1)) * radius;
            double z2 = Math.cos(Math.toRadians(i + 1)) * radius;

            GL11.glVertex3d(0, 0.3, 0);
            GL11.glVertex3d(x1, 0, z1);
            GL11.glVertex3d(x2, 0, z2);
        }
        GL11.glEnd();
        
        
	}
	
}

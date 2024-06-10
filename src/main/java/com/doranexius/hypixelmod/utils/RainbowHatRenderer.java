package com.doranexius.hypixelmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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
        	GL11.glColor4d(r / 255.0, g / 255.0, b / 255.0, 0.7);
    		GL11.glLineWidth(1.0f);
            double x1 = Math.sin(Math.toRadians(i)) * radius;
            double z1 = Math.cos(Math.toRadians(i)) * radius;
            double x2 = Math.sin(Math.toRadians(i + 1)) * radius;
            double z2 = Math.cos(Math.toRadians(i + 1)) * radius;

            //GL11.glVertex3d(0, 0.3, 0);
            GL11.glVertex3d(x1, 0, z1);
            GL11.glVertex3d(x2, 0, z2);
        }
        GL11.glEnd();
        
        
	}

    public static void drawHat0(EntityPlayer entityPlayer, float f2) {
        Minecraft mc = Minecraft.getMinecraft();
        RenderManager renderManager = mc.getRenderManager();
        if (renderManager == null || renderManager.options == null || entityPlayer == mc.thePlayer && mc.gameSettings.thirdPersonView == 0) {
            return;
        }
        double d2 = renderManager.viewerPosX;
        double d3 = renderManager.viewerPosY;
        double d4 = renderManager.viewerPosZ;
        double d5 = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double) f2 - d2;
        double d6 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double) f2 + (double) (entityPlayer.height / 2.0f) - d3;
        double d7 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double) f2 - d4;
        double d8 = (entityPlayer.isSneaking() ? -0.15 : 0.08);
        GL11.glPushMatrix();
        GL11.glTranslated((double) d5, (double) ((float) (d6 + (double) (entityPlayer.height / 2.0f) + d8)), (double) d7);
        GL11.glBlendFunc((int) 770, (int) 771);
        GL11.glEnable((int) 3042);
        GL11.glDisable((int) 2896);
        GL11.glDisable((int) 3553);
        GL11.glDisable((int) 2884);
        GL11.glRotatef((float) (-entityPlayer.rotationYaw), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        GL11.glBegin((int) 6);
        GL11.glVertex3d((double) 0.0, 0.3, (double) 0.0);
        double d9 = 0.6;
        for (int i2 = 0; i2 < 361; ++i2) {
            ColorUtil.setColor(ColorUtil.b(ColorUtil.a(i2, 361, 0.7f), 0.7f));
            GL11.glVertex3d((double) (Math.cos((double) i2 * Math.PI / 180.0) * d9), (double) 0.0, (double) (Math.sin((double) i2 * Math.PI / 180.0) * d9));
            GL11.glVertex3d((double) (Math.cos(Math.toRadians(i2)) * d9), (double) 0.0, (double) (Math.sin(Math.toRadians(i2)) * d9));
        }
        GL11.glVertex3d((double) 0.0, (double) 0.3, (double) 0.0);
        GL11.glEnd();
        GlStateManager.resetColor();
        GL11.glEnable((int) 2884);
        GL11.glEnable((int) 3553);
        GL11.glEnable((int) 2896);
        GL11.glDisable((int) 3042);
        GL11.glPopMatrix();
    }
	
}

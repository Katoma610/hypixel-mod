package com.doranexius.hypixelmod.modules.render.waypoints;

import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.renderUtils.RenderBoundingBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.BlockPos;

public class RenderWaypoints {
	
	public static void renderWaypoints() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;
		
		for (String name : WaypointManager.getWaypointList().keySet()) {
			Triple<Integer, Integer, Integer> waypoint = WaypointManager.getWaypointList().get(name);
			FontRenderer fRenderer = Minecraft.getMinecraft().fontRendererObj;
			
			double strWidth = fRenderer.getStringWidth(name) / 36;
			
			GL11.glColor3d(0, 255, 0);
		    GL11.glLineWidth(2.0f);
		    GL11.glDisable(GL11.GL_TEXTURE_2D);
		    
		    GL11.glBegin(GL11.GL_LINE_STRIP);
			
			GL11.glVertex3d(0,0,0);
			GL11.glVertex3d(waypoint.getLeft() - playerX, waypoint.getMiddle() - playerY, waypoint.getRight() - playerZ);
			
			GL11.glEnd();
			
			GL11.glTranslated(-playerX + waypoint.getLeft(), -playerY + waypoint.getMiddle(), -playerZ + waypoint.getRight());
			RenderBoundingBox.renderBB(1, 1, 0, 255, 0);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			GL11.glTranslated(0, 1.5, 0);
			
			WaypointUtils.drawNametag(name, new BlockPos(waypoint.getLeft(), waypoint.getMiddle(), waypoint.getRight()));
			
			GL11.glTranslated(playerX - waypoint.getLeft(), playerY - waypoint.getMiddle()-1.5, playerZ - waypoint.getRight());
			
			
		}
	}
	
}

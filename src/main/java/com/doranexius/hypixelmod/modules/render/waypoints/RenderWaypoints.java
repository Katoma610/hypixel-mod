package com.doranexius.hypixelmod.modules.render.waypoints;

import com.doranexius.hypixelmod.utils.WaypointUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.BoundingBoxUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.BlockPos;

import java.util.Map;

public class RenderWaypoints {

	public static String currName = "";

	public static void renderWaypoints(float partialTicks) {
		renderWaypointsUtil(WaypointManager.getWaypointList(), partialTicks);
	}

	private static void renderWaypointsUtil(Map<String, Triple<Integer, Integer, Integer>> waypoints, float partialTicks){

		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();

		for (String name : waypoints.keySet()) {
			//System.out.println("Rendering " + name);
			currName = name;
			int r = 0;
			int g = 255;
			int b = 0;

			if (name.contains("Grotto")) {
				r = 255;
				g = 0;
				b = 255;
			}
			Triple<Integer, Integer, Integer> waypoint = waypoints.get(name);

			GL11.glColor3d(r, g, b);

			GL11.glLineWidth(2.0f);
			//GL11.glDisable(GL11.GL_TEXTURE_2D);

			//GlStateManager.disableDepth();
			//GlStateManager.pushMatrix();

			GL11.glBegin(GL11.GL_LINE_STRIP);

			GL11.glVertex3d(0,0,0);
			GL11.glVertex3d(waypoint.getLeft()-0.5 - playerX, waypoint.getMiddle() - playerY, waypoint.getRight()-0.5 - playerZ);
			GL11.glVertex3d(0,0,0);

			GL11.glEnd();

			GL11.glTranslated(-playerX + waypoint.getLeft()-0.5, -playerY + waypoint.getMiddle(), -playerZ + waypoint.getRight()-0.5);
			//GlStateManager.resetColor();
			BoundingBoxUtils.renderBoundingBox(1, 1, r, g, b);

			//GL11.glEnable(GL11.GL_TEXTURE_2D);
			GlStateManager.enableTexture2D();
			GlStateManager.enableDepth();

			//GL11.glTranslated(0, 1.5, 0);
			GL11.glTranslated(playerX - waypoint.getLeft()+0.5, playerY - waypoint.getMiddle(), playerZ - waypoint.getRight()+0.5);
			WaypointUtils.renderWaypointText(name, new BlockPos(waypoint.getLeft(), waypoint.getMiddle(), waypoint.getRight()), partialTicks);

			GlStateManager.disableTexture2D();
			GlStateManager.disableDepth();
			//GlStateManager.popMatrix();
		}

		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();

	}
	
}

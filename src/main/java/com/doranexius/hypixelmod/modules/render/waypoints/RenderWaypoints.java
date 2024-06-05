package com.doranexius.hypixelmod.modules.render.waypoints;

import com.doranexius.hypixelmod.utils.HypixelUtils.FairyGrottoScanner;
import com.doranexius.hypixelmod.utils.WaypointUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.RenderBoundingBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.BlockPos;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RenderWaypoints {
	
	public static void renderWaypoints(float partialTicks) {
		if (!WaypointManager.getWaypointList().isEmpty()) {
			renderWaypointsUtil(WaypointManager.getWaypointList(), partialTicks);
		}
	}

	private static void renderWaypointsUtil(Map<String, Triple<Integer, Integer, Integer>> waypoints, float partialTicks){

		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		double playerX = renderManager.viewerPosX;
		double playerY = renderManager.viewerPosY;
		double playerZ = renderManager.viewerPosZ;

		for (String name : waypoints.keySet()) {
			int r = 0;
			int g = 255;
			int b = 0;

			if (name.contains("Grotto")) {
				r = 255;
				g = 0;
				b = 255;
			}
			Triple<Integer, Integer, Integer> waypoint = waypoints.get(name);
			FontRenderer fRenderer = Minecraft.getMinecraft().fontRendererObj;

			GL11.glColor3d(r, g, b);

			GL11.glLineWidth(2.0f);
			GL11.glDisable(GL11.GL_TEXTURE_2D);


			GL11.glBegin(GL11.GL_LINE_STRIP);

			GL11.glVertex3d(0,0,0);
			GL11.glVertex3d(waypoint.getLeft()-0.5 - playerX, waypoint.getMiddle() - playerY, waypoint.getRight()-0.5 - playerZ);

			GL11.glEnd();

			GL11.glTranslated(-playerX + waypoint.getLeft()-0.5, -playerY + waypoint.getMiddle(), -playerZ + waypoint.getRight()-0.5);
			RenderBoundingBox.renderBB(1, 1, r, g, b);

			GL11.glEnable(GL11.GL_TEXTURE_2D);

			//GL11.glTranslated(0, 1.5, 0);
			GL11.glTranslated(playerX - waypoint.getLeft()+0.5, playerY - waypoint.getMiddle(), playerZ - waypoint.getRight()+0.5);
			WaypointUtils.renderWaypointText(name, new BlockPos(waypoint.getLeft(), waypoint.getMiddle(), waypoint.getRight()), partialTicks);


		}
	}
	
}

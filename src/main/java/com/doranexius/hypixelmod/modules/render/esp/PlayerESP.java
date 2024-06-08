package com.doranexius.hypixelmod.modules.render.esp;

import com.doranexius.hypixelmod.utils.HypixelUtils.SkyblockInfo;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.utils.RenderBoundingBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerESP extends Module {

	public PlayerESP() {
		super("PlayerESP", Category.RENDER);
	}
	
	public static void drawPlayerESP() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;

	    for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
			if (player.getDisplayNameString().contains(" ")) continue;

	    	GL11.glTranslated(lastX+player.posX, lastY+player.posY, lastZ+player.posZ);
    		lastX = -player.posX;
    		lastY = -player.posY;
    		lastZ = -player.posZ;
			RenderBoundingBox.renderBB(1, 2, 0, 0, 255);
	    }
	    
	    GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
	
	@Override
	public void onEnable() {
		drawPlayerESP();
	}

}

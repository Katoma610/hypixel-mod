package com.doranexius.hypixelmod.modules.render;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.utils.BoundingBoxUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class ESP extends Module {

	public ESP() {
		super("ESP", Category.RENDER, new HashMap<String, Boolean>() {{
			put("Hostile", false);
			put("Passive", false);
			put("Players", false);
			put("Chests", false);
			put("TeamSplit", false);
			put("Invisible", false);
			put("§6Starred§f", false);
		}});
	}
	
	public void drawESP() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		double lastX = -renderManager.viewerPosX;
		double lastY = -renderManager.viewerPosY;
		double lastZ = -renderManager.viewerPosZ;

		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GlStateManager.disableTexture2D();
		GlStateManager.resetColor();

	    for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
	    	GL11.glTranslated(lastX+entity.posX, lastY+entity.posY, lastZ+entity.posZ);
    		lastX = -entity.posX;
    		lastY = -entity.posY;
    		lastZ = -entity.posZ;

			if (this.options.get("Hostile") && entity.isCreatureType(EnumCreatureType.MONSTER, false)) {
				BoundingBoxUtils.renderBoundingBox(1, 2, 120, 0, 0);
			} else if (this.options.get("Passive") && !entity.isCreatureType(EnumCreatureType.MONSTER, false) && entity.isCreatureType(EnumCreatureType.CREATURE, false) && !entity.equals(Minecraft.getMinecraft().thePlayer)) {
				BoundingBoxUtils.renderBoundingBox(1, 2, 170, 0, 165);
			} else if (this.options.get("Players") && (entity instanceof EntityPlayer || entity instanceof EntityPlayerMP || entity instanceof EntityPlayerSP) && !entity.equals(Minecraft.getMinecraft().thePlayer)) {
				boolean isOnCrimTeam = Minecraft.getMinecraft().thePlayer.getDisplayName().getFormattedText().contains("§4");

				if (this.options.get("Invisible") && entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
					BoundingBoxUtils.renderBoundingBox(1, 2, 0, 0, 255);
				} else if (this.options.get("TeamSplit") && entity.getDisplayName().getFormattedText().contains("§3") && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
					if (isOnCrimTeam) {
						BoundingBoxUtils.renderBoundingBox(1, 2, 255, 0, 0);
					} else {
						BoundingBoxUtils.renderBoundingBox(1, 2, 0, 255, 0);
					}
				} else if (this.options.get("TeamSplit") && entity.getDisplayName().getFormattedText().contains("§4") && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
					if (isOnCrimTeam) {
						BoundingBoxUtils.renderBoundingBox(1, 2, 0, 255, 0);
					} else {
						BoundingBoxUtils.renderBoundingBox(1, 2, 255, 0, 0);
					}
				} else if (!entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
					BoundingBoxUtils.renderBoundingBox(1, 2, 0, 0, 255);
				}
			} else if (this.options.get("§6Starred§f") && entity.getDisplayName().getFormattedText().contains("✯")) {
				GL11.glTranslated(0, -2, 0);
				BoundingBoxUtils.renderBoundingBox(1, 2, 245, 170, 39);
				GL11.glTranslated(0, 2, 0);
			}
	    }

		GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);

		lastX = -renderManager.viewerPosX;
		lastY = -renderManager.viewerPosY;
		lastZ = -renderManager.viewerPosZ;

		if (this.options.get("Chests")) {
			for (TileEntity block : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
				if (block instanceof TileEntityChest) {
					GL11.glTranslated(lastX + block.getPos().getX()+0.5, lastY + block.getPos().getY(), lastZ + block.getPos().getZ()+0.5);
					BoundingBoxUtils.renderBoundingBox(1, 1, 0, 255, 0);
					lastX = -block.getPos().getX()-0.5;
					lastY = -block.getPos().getY();
					lastZ = -block.getPos().getZ()-0.5;
				}
			}
		}

		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	    //GL11.glTranslated(lastX+renderManager.viewerPosX, lastY+renderManager.viewerPosY, lastZ+renderManager.viewerPosZ);
	}
	
	@Override
	public void onEnable(float partialTicks) {
		drawESP();
	}
}

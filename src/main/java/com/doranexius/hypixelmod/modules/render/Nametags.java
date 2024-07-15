package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.utils.TextUtils;
import com.doranexius.hypixelmod.utils.WaypointUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;

public class Nametags extends Module {

    public Nametags() {
        super("Nametags", Category.RENDER, new HashMap<String, Boolean>() {{
            put("Health", false);
            put("Mobs", false);
            put("Other", false);
        }});
    }

    public void drawNametags(float partialTicks) {
//        for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
//            //player.refreshDisplayName(); player.getDisplayName().getFormattedText().contains(" ") ||
//            if (player.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) || player.equals(Minecraft.getMinecraft().thePlayer)) continue;
//            float health = Math.round(player.getHealth());
//            if (this.options.get("Health")) {
//                nametagsUtil(player.getDisplayName().getFormattedText() + " " + String.format("§f[§c%.0f§f]", health), player.posX, player.posY, player.posZ, partialTicks);
//            } else {
//                nametagsUtil(player.getDisplayName().getFormattedText(), player.posX, player.posY, player.posZ, partialTicks);
//            }
//        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer localPlayer = mc.thePlayer;

        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            double height = entity.height;
            // ✯
            if (entity instanceof EntityPlayer && !entity.equals(localPlayer)) {
                EntityPlayer player = (EntityPlayer) entity;
                float health = Math.round(player.getHealth());
                if (this.options.get("Health")) {
                    nametagsUtil(player.getDisplayName().getFormattedText() + " " + String.format("§f[§c%.0f§f]", health), player.posX, player.posY, player.posZ, height, partialTicks);
                } else {
                    nametagsUtil(player.getDisplayName().getFormattedText(), player.posX, player.posY, player.posZ, height, partialTicks);
                }
            } else if (this.options.get("Mobs") && !entity.isInvisibleToPlayer(localPlayer) && entity instanceof EntityLiving) {
                EntityLiving living = (EntityLiving) entity;
                float health = Math.round(living.getHealth());
                if (this.options.get("Health")) {
                    nametagsUtil(entity.getDisplayName().getFormattedText() + " " + String.format("§f[§c%.0f§f]", health), living.posX, living.posY, living.posZ, height, partialTicks);
                } else {
                    nametagsUtil(entity.getDisplayName().getFormattedText(), living.posX, living.posY, living.posZ, height, partialTicks);
                }
            } else if (this.options.get("Other") && !entity.isInvisibleToPlayer(localPlayer) && !(entity instanceof EntityPlayer) && !(entity instanceof EntityLiving)) {
                nametagsUtil(entity.getDisplayName().getFormattedText(), entity.posX, entity.posY, entity.posZ, height, partialTicks);
            }
        }
    }

    private void nametagsUtil(String str, double playerX, double playerY, double playerZ, double height, float partialTicks){
        GlStateManager.alphaFunc(516, 0.1F);

        GlStateManager.pushMatrix();

        Entity viewer = Minecraft.getMinecraft().getRenderViewEntity();
        double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * partialTicks;
        double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * partialTicks;
        double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * partialTicks;

        double x = playerX - viewerX;
        double y = playerY - viewerY + 0.15;
        double z = playerZ - viewerZ;

        double distSq = x*x + y*y + z*z;
        double dist = Math.sqrt(distSq);
        if(distSq > 100) {
            x *= 12/dist;
            y *= 12/dist;
            z *= 12/dist;
        }
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0, viewer.getEyeHeight(), 0);

        TextUtils.draw2DFloatingText(str);

        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0, -0.25f, 0);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);

        GlStateManager.popMatrix();

        //GlStateManager.disableLighting();
    }

    @Override
    public void onEnable(float partialTicks) {
        drawNametags(partialTicks);
    }
}

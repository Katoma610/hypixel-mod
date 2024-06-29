package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.utils.TextUtils;
import com.doranexius.hypixelmod.utils.WaypointUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Nametags extends Module {

    public Nametags() {
        super("Nametags", Category.RENDER);
    }

    public static void drawNametags(float partialTicks) {

        for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
            if (player.getDisplayNameString().contains(" ") || player.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) || player.equals(Minecraft.getMinecraft().thePlayer)) continue;

            nametagsUtil(player.getName(), player.posX, player.posY, player.posZ, partialTicks);
        }
    }

    private static void nametagsUtil(String str, double playerX, double playerY, double playerZ, float partialTicks){
        GlStateManager.alphaFunc(516, 0.1F);

        GlStateManager.pushMatrix();

        Entity viewer = Minecraft.getMinecraft().getRenderViewEntity();
        double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * partialTicks;
        double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * partialTicks;
        double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * partialTicks;

        double x = playerX - viewerX;
        double y = playerY - viewerY + 0.65;
        double z = playerZ - viewerZ;

        double distSq = x*x + y*y + z*z;
        double dist = Math.sqrt(distSq);
        if(distSq > 100) {
            x *= 10/dist;
            y *= 10/dist;
            z *= 10/dist;
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

package com.doranexius.hypixelmod.events;

import com.doranexius.hypixelmod.utils.HypixelUtils.FairyGrottoScanner;
import com.doranexius.hypixelmod.utils.HypixelUtils.SkyblockInfo;
import com.doranexius.hypixelmod.utils.PrintUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;
import com.doranexius.hypixelmod.*;
import com.doranexius.hypixelmod.cosmetics.HatCosmetic;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.Fullbright;
import com.doranexius.hypixelmod.modules.render.waypoints.RenderWaypoints;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;
import com.doranexius.hypixelmod.overlays.ArmorHUDOverlay;
import com.doranexius.hypixelmod.overlays.InfoOverlay;

public class ModClientEventHandler {
	
	public static GuiScreen guiToDisplay = null;

	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.equals(Minecraft.getMinecraft().thePlayer)) return;
    	Fullbright.checkFullbright();
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		ModuleManager.onUpdate();
	}
	
	@SubscribeEvent
	public void onLastEvent(RenderWorldLastEvent event) {
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		for (Module module : ModuleManager.getModList()) {
			Category modCategory = module.getCategory();
			if (modCategory.equals(Category.RENDER) && module.isToggled()) {
				module.onEnable();
			}
		}

		if (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0) {
			HatCosmetic.drawHat();
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);

		if (!WaypointManager.getWaypointList().isEmpty()) {
			RenderWaypoints.renderWaypoints(event.partialTicks);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	@SubscribeEvent
	public void onGUIRender(RenderGameOverlayEvent.Post event) {
		if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
			return;
		}
		
		InfoOverlay.printInfo(HypixelMod.NAME);
		
		
		
		if (guiToDisplay != null) {
			Minecraft.getMinecraft().displayGuiScreen(guiToDisplay);
			guiToDisplay = null;
		}
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		
		if (event.phase.equals(TickEvent.Phase.START)) {
		      return;
		}

		if (!(mc.currentScreen == null) && !mc.ingameGUI.getChatGUI().getChatOpen()) {
		      return;
		}

		final EntityPlayer player = mc.thePlayer;
		if (player == null || player.capabilities.isCreativeMode || player.isSpectator()) {
		      return;
		}
		
		if (ArmorHUDOverlay.getToggled()) {
			ArmorHUDOverlay.renderArmorOverlay();
		}
		
		
	}
	
}

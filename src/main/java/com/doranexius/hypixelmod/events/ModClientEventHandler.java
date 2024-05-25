package com.doranexius.hypixelmod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

import java.nio.FloatBuffer;

import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import com.doranexius.hypixelmod.*;
import com.doranexius.hypixelmod.cosmetics.HatCosmetic;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.Fullbright;
import com.doranexius.hypixelmod.modules.render.Tracers;
import com.doranexius.hypixelmod.modules.render.esp.ChestESP;
import com.doranexius.hypixelmod.modules.render.esp.MobESP;
import com.doranexius.hypixelmod.modules.render.esp.PlayerESP;
import com.doranexius.hypixelmod.modules.render.waypoints.RenderWaypoints;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointUtils;
import com.doranexius.hypixelmod.overlays.ArmorHUDOverlay;
import com.doranexius.hypixelmod.overlays.InfoOverlay;
import com.doranexius.hypixelmod.renderUtils.WorldToScreen;

public class ModClientEventHandler {
	
	public static GuiScreen guiToDisplay = null;
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			ChatComponentText comp = new ChatComponentText(String.format(HypixelMod.BASE_MESSAGE_START + "Hi, %s", player.getName()));
			ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/kill")).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click this lol")));;
			comp.setChatStyle(style);
			player.addChatMessage(comp);
		}
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
		//GL11.glDisable(GL11.GL_TEXTURE);
		
		for (Module module : ModuleManager.getModList()) {
			Category modCategory = module.getCategory();
			if (modCategory.equals(Category.RENDER) && module.isToggled()) {
				module.onEnable();
			}
		}
		
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		if (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0) {
			HatCosmetic.drawHat();
		}
		
		//GL11.glDisable(GL11.GL_DEPTH);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glEnable(GL11.GL_TEXTURE);
		
		
		
		if (!WaypointManager.getWaypointList().isEmpty()) {
			RenderWaypoints.renderWaypoints(event.partialTicks);
		}
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
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

		if (!(mc.currentScreen == null)) {
		      return;
		}

		final EntityPlayer player = mc.thePlayer;
		if (player == null || player.capabilities.isCreativeMode || player.isSpectator()) {
		      return;
		}
		
		if (ModuleManager.getModList().get(5).isToggled()) {
			ArmorHUDOverlay.renderArmorOverlay();
		}
		
		
	}
	
}

package com.doranexius.hypixelmod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.*;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.Tracers;
import com.doranexius.hypixelmod.modules.render.esp.ChestESP;
import com.doranexius.hypixelmod.modules.render.esp.MobESP;
import com.doranexius.hypixelmod.modules.render.esp.PlayerESP;
import com.doranexius.hypixelmod.modules.render.waypoint.RenderWaypoints;
import com.doranexius.hypixelmod.modules.render.waypoint.WaypointManager;
import com.doranexius.hypixelmod.renderUtils.RenderGUI;

public class ModClientEventHandler {
	
	private String baseMessageStart = "§6[Hypixel Mod]§6§c ";
	private MobESP mobESP = new MobESP();
	public static GuiScreen guiToDisplay = null;
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			ChatComponentText comp = new ChatComponentText(String.format(baseMessageStart + "Hi, %s", player.getName()));
			ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/kill")).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click this lol")));;
			comp.setChatStyle(style);
			player.addChatMessage(comp);
		}
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		ModuleManager.onUpdate();
	}
	
	@SubscribeEvent
	public void onLastEvent(RenderWorldLastEvent event) {
		
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		if (ModuleManager.getRenderModList().get(0).isToggled()) {
			MobESP.drawMobESP();
		}
		if (ModuleManager.getRenderModList().get(1).isToggled()) {
			ChestESP.drawBlockOutline();
		}
		if (ModuleManager.getRenderModList().get(2).isToggled()) {
			PlayerESP.drawPlayerESP();
		}
		if (ModuleManager.getRenderModList().get(3).isToggled()) {
			Tracers.drawTracers();
		}
		
		if (!WaypointManager.getWaypointList().isEmpty()) {
			RenderWaypoints.renderWaypoints();
		}
		
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	@SubscribeEvent
	public void onGUIRender(RenderGameOverlayEvent event) {
		RenderGUI.printClientName(HypixelMod.MODID);
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if (guiToDisplay != null) {
			Minecraft.getMinecraft().displayGuiScreen(guiToDisplay);
			guiToDisplay = null;
		}
	}
}

package com.doranexius.hypixelmod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;

import javax.management.loading.PrivateClassLoader;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Yellow;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.doranexius.hypixelmod.*;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.MobESP;
import com.doranexius.hypixelmod.modules.render.Tracers;
import com.doranexius.hypixelmod.modules.render.ChestESP;
import com.doranexius.hypixelmod.proxy.ClientProxy;
import com.doranexius.hypixelmod.renderUtils.RenderBoundingBox;
import com.doranexius.hypixelmod.renderUtils.RenderGUI;

import jline.internal.Ansi;

import com.doranexius.hypixelmod.modules.Module;

public class ModClientEventHandler {
	
	private String baseMessageStart = "§6[Hypixel Mod]§6§c ";
	private MobESP mobESP = new MobESP();
	
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
		
		if (ModuleManager.getModList().get(0).isToggled()) {
			MobESP.drawESPBoxes();
		}
		if (ModuleManager.getModList().get(1).isToggled()) {
			ChestESP.drawBlockOutline(0, 255, 0);
		}
		if (ModuleManager.getModList().get(2).isToggled()) {
			Tracers.drawTracers();
			System.out.println("Drawing tracers");
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
}

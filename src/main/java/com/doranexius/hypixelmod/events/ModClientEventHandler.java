package com.doranexius.hypixelmod.events;

import com.doranexius.hypixelmod.gui.HUDEditorGUI;
import com.doranexius.hypixelmod.gui.NewMainGUI;
import com.doranexius.hypixelmod.overlays.InventoryOverlay;
import com.doranexius.hypixelmod.utils.ScoreboardManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
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
import org.lwjgl.input.Mouse;

public class ModClientEventHandler {
	
	public static GuiScreen guiToDisplay = null;
	public static int mouseX = 0;
	public static int mouseY = 0;

	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.equals(Minecraft.getMinecraft().thePlayer)) return;
		//PrintUtils.print(ScoreboardManager.getScoreboardTitle());
    	Fullbright.checkFullbright();
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		ModuleManager.onUpdate();
	}
	
	@SubscribeEvent
	public void onLastEvent(RenderWorldLastEvent event) {
		//HatCosmetic.drawHat(event.partialTicks);

//		GL11.glPushMatrix();
//		GL11.glDisable(GL11.GL_LIGHTING);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		for (Module module : ModuleManager.getModList()) {
			Category modCategory = module.getCategory();
			if ((modCategory.equals(Category.RENDER) || modCategory.equals(Category.COSMETIC)) && module.isToggled()) {
				module.onEnable(event.partialTicks);
			}
		}

		//GL11.glEnable(GL11.GL_DEPTH_TEST);

		if (!WaypointManager.getWaypointList().isEmpty()) {
			RenderWaypoints.renderWaypoints(event.partialTicks);
		}

//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glPopMatrix();


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
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		
		if (event.phase.equals(TickEvent.Phase.START)) {
		      return;
		}

		if (InventoryOverlay.getToggled() && (mc.currentScreen instanceof HUDEditorGUI || mc.currentScreen == null || mc.ingameGUI.getChatGUI().getChatOpen())) {
			InventoryOverlay.renderInventory(mouseX, mouseY);
		}

//		if (!(mc.currentScreen == null) && !mc.ingameGUI.getChatGUI().getChatOpen()) {
//		      return;
//		}



		final EntityPlayer player = mc.thePlayer;
		if (player == null || player.capabilities.isCreativeMode || player.isSpectator()) {
		      return;
		}
		
		if (ArmorHUDOverlay.getToggled() && (mc.currentScreen instanceof HUDEditorGUI || mc.currentScreen instanceof NewMainGUI || mc.currentScreen == null || mc.ingameGUI.getChatGUI().getChatOpen())) {
			ArmorHUDOverlay.renderArmorOverlay();
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		ScoreboardManager.tick();
	}

	@SubscribeEvent
	public void onMouseEvent(GuiScreenEvent.MouseInputEvent event) {
		mouseX = Mouse.getEventX();
		mouseY = Mouse.getEventY();
//		System.out.println("X: " + mouseX);
//		System.out.println("Y: " + mouseY);

//		if (Mouse.isButtonDown(0)) {
//
//		}
	}
}

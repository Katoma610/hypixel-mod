package com.doranexius.hypixelmod.modules.hud;

import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.gui.HUDEditorGUI;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import net.minecraft.client.Minecraft;

public class HUDEditor extends Module {

    public HUDEditor() {
        super("Editor", Category.HUD);
    }

    @Override
    public void toggle() {
        Minecraft.getMinecraft().displayGuiScreen(null);
        ModClientEventHandler.guiToDisplay = new HUDEditorGUI();
    }
}

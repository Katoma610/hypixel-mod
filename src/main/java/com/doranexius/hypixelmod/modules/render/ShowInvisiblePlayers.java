package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;

public class ShowInvisiblePlayers extends Module {

    public static boolean isToggled = false;

    public ShowInvisiblePlayers() {
        super("Invis. Players", Category.RENDER);
    }

    @Override
    public void toggle() {
        super.toggle();
        ShowInvisiblePlayers.isToggled = !ShowInvisiblePlayers.isToggled;
    }

}

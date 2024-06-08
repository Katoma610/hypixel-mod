package com.doranexius.hypixelmod.utils.HypixelUtils;

import com.doranexius.hypixelmod.utils.PrintUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkyblockInfo {

    public static boolean isOnCrystalHollows() {
        if (Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap().isEmpty()) return false;
        List<NetworkPlayerInfo> players = new ArrayList<>(Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap());

        for (NetworkPlayerInfo info : players) {
            String name = Minecraft.getMinecraft().ingameGUI.getTabList().getPlayerName(info);
            if (name.contains("Crystal Hollows")) return true;
        }

        return false;
    }
}

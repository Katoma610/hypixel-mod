package com.doranexius.hypixelmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

public class ScoreboardManager {

    private static String scoreboardTitle = "null";
    private static String strippedScoreboardTitle = "null";

    public static void tick() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null) {
            return;
        }

        Scoreboard scoreboard = mc.theWorld.getScoreboard();
        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);

        if (objective == null || scoreboard == null) {
            return;
        }

        // Update titles
        scoreboardTitle = objective.getDisplayName();
    }

    public static String getScoreboardTitle() {
        return scoreboardTitle;
    }

    public static String getStrippedScoreboardTitle() {
        return strippedScoreboardTitle;
    }

}

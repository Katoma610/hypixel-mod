package com.doranexius.hypixelmod.modules.render;

import com.doranexius.hypixelmod.HypixelMod;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.render.waypoints.WaypointManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FairyGrottoScanner extends Module {

//    private static List<Triple<Integer, Integer, Integer>> coords = new ArrayList<>();
//    private static Map<String, Triple<Integer, Integer, Integer>> waypoints = new HashMap<>();

    private static int grottoNum = 1;

    public FairyGrottoScanner() {
        super("Grotto Find", Category.HYPIXEL);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        if (!this.isToggled()) return;
        CompletableFuture.runAsync(() -> grottoChunkScan(event.getChunk()));
    }

    public static void grottoChunkScan(Chunk chunk) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 200; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    BlockPos pos = new BlockPos(chunk.xPosition * 16 + x, y, chunk.zPosition * 16 + z);
                    if (block == Blocks.stained_glass && Minecraft.getMinecraft().theWorld.getBlockState(pos).getValue(BlockColored.COLOR).equals(EnumDyeColor.MAGENTA) && !WaypointManager.getWaypointList().values().contains(Triple.of(pos.getX(), pos.getY(), pos.getZ()))) {
                        ChatComponentText message = new ChatComponentText(String.format(HypixelMod.BASE_MESSAGE_START + String.format("A possible §dFairy Grotto§b has been found at %d %d %d! §eCLICK§b to add a waypoint", pos.getX(), pos.getY(), pos.getZ())));
                        String command = String.format("/hmod addwaypoint Grotto%d %d %d %d", grottoNum, pos.getX()+1, pos.getY(), pos.getZ()+1);
                        ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("CLICK to add a waypoint")));
                        message.setChatStyle(style);
                        player.addChatMessage(message);
                        grottoNum += 1;
                        return;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onUnload(WorldEvent.Unload event){
//        waypoints.clear();
//        coords.clear();
        Map<String, Triple<Integer, Integer, Integer>> filteredWaypoints = WaypointManager.getWaypointList().entrySet().stream().filter(waypoint -> !waypoint.getKey().contains("Grotto")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        WaypointManager.getWaypointList().clear();
        WaypointManager.getWaypointList().putAll(filteredWaypoints);
        grottoNum = 1;
    }

//    public static Map<String, Triple<Integer, Integer, Integer>> getGrottoWaypoints() {
//        return waypoints;
//    }

}

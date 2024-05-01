package com.doranexius.hypixelmod;

import com.doranexius.hypixelmod.commands.CommandManager;
import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.ChestESP;
import com.doranexius.hypixelmod.modules.render.Fullbright;
import com.doranexius.hypixelmod.modules.render.MobESP;
import com.doranexius.hypixelmod.modules.render.Tracers;
import com.doranexius.hypixelmod.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import scala.reflect.internal.Trees.New;

@Mod(modid = HypixelMod.MODID, version = HypixelMod.VERSION)
public class HypixelMod
{
    public static final String MODID = "Hypixel Mod";
    public static final String VERSION = "1.0";
    public static HypixelMod instance = new HypixelMod();
    public static ModuleManager moduleManager;
    
    @SidedProxy(clientSide = "com.doranexius.hypixelmod.proxy.ClientProxy", serverSide = "com.doranexius.hypixelmod.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public void startClient() {
    	// RENDER Modules
    	ModuleManager.newMod(new MobESP());
    	ModuleManager.newMod(new ChestESP());
    	ModuleManager.newMod(new Tracers());
    	ModuleManager.newMod(new Fullbright());
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ModClientEventHandler());
        startClient();
        
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandManager());
    }
}

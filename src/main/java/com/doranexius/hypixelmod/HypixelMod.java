package com.doranexius.hypixelmod;

import com.doranexius.hypixelmod.commands.MainCommand;
import com.doranexius.hypixelmod.events.ModClientEventHandler;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.modules.render.Fullbright;
import com.doranexius.hypixelmod.modules.render.ShowInvisibleEntities;
import com.doranexius.hypixelmod.modules.render.ShowInvisiblePlayers;
import com.doranexius.hypixelmod.modules.render.Tracers;
import com.doranexius.hypixelmod.modules.render.esp.ChestESP;
import com.doranexius.hypixelmod.modules.render.esp.MobESP;
import com.doranexius.hypixelmod.modules.render.esp.PlayerESP;
import com.doranexius.hypixelmod.overlays.ArmorHUDOverlay;
import com.doranexius.hypixelmod.proxy.CommonProxy;

import com.doranexius.hypixelmod.utils.HypixelUtils.FairyGrottoScanner;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(name = HypixelMod.NAME, modid = HypixelMod.MODID, version = HypixelMod.VERSION, clientSideOnly = true)
public class HypixelMod
{
    public static final String MODID = "hypixelmod";
    public static final String VERSION = "1.0";
    public static final String NAME = "Hypixel Mod";
    public static final String BASE_MESSAGE_START = "§9[§bHypixel §3Mod§9]§b ";
    public static HypixelMod instance = new HypixelMod();
    public static ModuleManager moduleManager;

    private FairyGrottoScanner fairyGrottoInstance = new FairyGrottoScanner();
    
    @SidedProxy(clientSide = "com.doranexius.hypixelmod.proxy.ClientProxy", serverSide = "com.doranexius.hypixelmod.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public void startClient() {
    	// RENDER Modules
    	ModuleManager.newMod(new MobESP());
    	ModuleManager.newMod(new ChestESP());
    	ModuleManager.newMod(new PlayerESP());
    	ModuleManager.newMod(new Tracers());
    	ModuleManager.newMod(new Fullbright());
    	ModuleManager.newMod(new ShowInvisibleEntities());
        ModuleManager.newMod(new ShowInvisiblePlayers());
        ModuleManager.newMod(fairyGrottoInstance);
    	
    	
    	// HUD Modules
    	ModuleManager.newMod(new ArmorHUDOverlay());
    	
    	// Add all module lists to the main module list
    	//ModuleManager.mergeModList(ModuleManager.getRenderModList());
    	
    	
    	
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
        MinecraftForge.EVENT_BUS.register(fairyGrottoInstance);
        ClientCommandHandler.instance.registerCommand(new MainCommand());
        startClient();
        
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
    
//    @EventHandler
//    public void onServerStart(FMLServerStartingEvent event) {
//        event.registerServerCommand(new MainCommand());
//    }
}

package net.darkaqua.blacksmith;

import net.darkaqua.blacksmith.render.internal.BSTextureManager;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by cout970 on 16/03/2016.
 * This is not a mod, it's just a library to share code between mods
 */
public class Blacksmith {

    private static Blacksmith apiInstance;
    private static boolean preInit;
    private static boolean init;
    private static boolean postInit;

    public static void preInit(){
        if (!preInit) {
            preInit = true;
            apiInstance = new Blacksmith();
            MinecraftForge.EVENT_BUS.register(apiInstance);
        }
    }

    public static void init(){
        if (!init) {
            init = true;
        }
    }

    public static void postInit(){
        if (!postInit) {
            postInit = true;
        }
    }

    @SubscribeEvent
    public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        BSTextureManager.onTextureStitchEvent(event);
    }
}

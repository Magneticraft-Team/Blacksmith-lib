package net.darkaqua.blacksmith;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 19/03/2016.
 */
public class Game {

    public static boolean isClient(){
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

    public static boolean isServer(){
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    public static boolean isDeobfuscatedEnvironment(){
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}

package net.darkaqua.blacksmith;

import net.minecraft.launchwrapper.Launch;

/**
 * Created by cout970 on 19/03/2016.
 */
public class Game {
    public static boolean isDevMode() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}

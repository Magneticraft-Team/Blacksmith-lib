package net.darkaqua.blacksmith.network.internal;


import net.darkaqua.blacksmith.network.INetworkContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkClientContext implements INetworkContext.IClientContext {

    @Override
    public void addScheduledTask(Runnable task) {
        Minecraft.getMinecraft().addScheduledTask(task);
    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public World getWorld() {
        return Minecraft.getMinecraft().theWorld;
    }
}

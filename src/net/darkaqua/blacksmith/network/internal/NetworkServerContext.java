package net.darkaqua.blacksmith.network.internal;

import net.darkaqua.blacksmith.network.INetworkContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkServerContext implements INetworkContext.IServerContext {

    @Override
    public World getWorld(int dimension) {
        return DimensionManager.getWorld(dimension);
    }

    @Override
    public MinecraftServer getServer() {
        return MinecraftServer.getServer();
    }

    @Override
    public void addScheduledTask(Runnable task) {
        MinecraftServer.getServer().addScheduledTask(task);
    }
}

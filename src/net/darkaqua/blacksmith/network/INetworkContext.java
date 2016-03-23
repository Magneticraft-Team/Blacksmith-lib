package net.darkaqua.blacksmith.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface INetworkContext {

    Side getSide();

    IClientContext getClientContext();

    IServerContext getServerContext();

    interface IClientContext {

        void addScheduledTask(Runnable task);

        EntityPlayer getPlayer();

        World getWorld();
    }

    interface IServerContext {

        void addScheduledTask(Runnable task);

        World getWorld(int dimension);

        MinecraftServer getServer();
    }
}

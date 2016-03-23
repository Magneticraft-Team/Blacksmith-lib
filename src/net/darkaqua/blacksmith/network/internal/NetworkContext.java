package net.darkaqua.blacksmith.network.internal;

import net.darkaqua.blacksmith.Game;
import net.darkaqua.blacksmith.network.INetworkContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkContext implements INetworkContext {

    @Override
    public Side getSide() {
        return Game.isClient() ? Side.CLIENT : Side.SERVER;
    }

    @Override
    public IClientContext getClientContext() {
        return new NetworkClientContext();
    }

    @Override
    public IServerContext getServerContext() {
        return new NetworkServerContext();
    }

}

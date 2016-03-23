package net.darkaqua.blacksmith.network;

import net.darkaqua.blacksmith.network.internal.BS_NetworkChannelFactory;

/**
 * Created by cout970 on 24/12/2015.
 */
public abstract class NetworkChannelFactory {

    protected static NetworkChannelFactory INSTANCE;

    static {
        BS_NetworkChannelFactory.init();
    }

    public static INetworkChannel createNetworkChannel(String channelName) {
        return INSTANCE.newNetworkChannel(channelName);
    }

    protected abstract INetworkChannel newNetworkChannel(String channelName);
}

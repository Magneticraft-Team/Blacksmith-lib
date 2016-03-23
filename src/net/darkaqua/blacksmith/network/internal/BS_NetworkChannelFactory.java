package net.darkaqua.blacksmith.network.internal;

import net.darkaqua.blacksmith.network.INetworkChannel;
import net.darkaqua.blacksmith.network.NetworkChannelFactory;
import net.darkaqua.blacksmith.network.internal.channel.SimpleChannel;

/**
 * Created by cout970 on 24/12/2015.
 */
public class BS_NetworkChannelFactory extends NetworkChannelFactory {

    private BS_NetworkChannelFactory() {
    }

    public static void init() {
        INSTANCE = new BS_NetworkChannelFactory();
    }

    @Override
    protected INetworkChannel newNetworkChannel(String channelName) {
        return new BS_NetworkChannel(new SimpleChannel(channelName));
    }
}

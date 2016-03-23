package net.darkaqua.blacksmith.network;

import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by cout970 on 24/12/2015.
 */
public interface INetworkChannel {

    <REQ extends INetworkMessage, REPLY extends INetworkMessage> void registerMessage(INetworkMessageHandler<? super REQ, ? extends REPLY> handler, Class<REQ> messageType, int discriminator, Side processSide);

    void sendToAll(INetworkMessage message);

    void sendTo(INetworkMessage message, EntityPlayer player);

    void sendToAllAround(INetworkMessage message, int dimension, double range, Vect3d pos);

    void sendToDimension(INetworkMessage message, int dimension);

    void sendToServer(INetworkMessage message);

    Object getInternalChannel();
}

package net.darkaqua.blacksmith.network;

import io.netty.buffer.ByteBuf;
import net.darkaqua.blacksmith.storage.IDataCompound;
import net.darkaqua.blacksmith.vectors.Vect3i;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by cout970 on 09/01/2016.
 */
public interface ExtendedByteBuf {

    ByteBuf getByteBuf();

    void writeByteArray(byte[] array);

    byte[] readByteArray();

    Vect3i readVect3i();

    void writeVect3i(Vect3i vec);

    <T extends Enum<T>> T readEnumValue(Class<T> enumClass);

    void writeEnumValue(Enum<?> value);

    void writeUUID(UUID uuid);

    UUID readUUID();

    void writeDataCompound(IDataCompound data);

    IDataCompound readDataCompound() throws IOException;

    void writeItemStack(ItemStack stack);

    ItemStack readItemStack() throws IOException;
}

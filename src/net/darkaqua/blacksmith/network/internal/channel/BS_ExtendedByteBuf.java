package net.darkaqua.blacksmith.network.internal.channel;

import io.netty.buffer.ByteBuf;
import net.darkaqua.blacksmith.network.ExtendedByteBuf;
import net.darkaqua.blacksmith.storage.DataElementFactory;
import net.darkaqua.blacksmith.storage.IDataCompound;
import net.darkaqua.blacksmith.vectors.Vect3i;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by cout970 on 09/01/2016.
 */
public class BS_ExtendedByteBuf implements ExtendedByteBuf {

    private PacketBuffer buff;

    public BS_ExtendedByteBuf(PacketBuffer buff) {
        this.buff = buff;
    }

    @Override
    public ByteBuf getByteBuf() {
        return buff;
    }

    @Override
    public void writeByteArray(byte[] array) {
        buff.writeByteArray(array);
    }

    @Override
    public byte[] readByteArray() {
        return buff.readByteArray();
    }

    @Override
    public Vect3i readVect3i() {
        return new Vect3i(buff.readBlockPos());
    }

    @Override
    public void writeVect3i(Vect3i vec) {
        buff.writeBlockPos(vec.toBlockPos());
    }

    @Override
    public <T extends Enum<T>> T readEnumValue(Class<T> enumClass) {
        return buff.readEnumValue(enumClass);
    }

    @Override
    public void writeEnumValue(Enum<?> value) {
        buff.writeEnumValue(value);
    }

    @Override
    public void writeUUID(UUID uuid) {
        buff.writeUuid(uuid);
    }

    @Override
    public UUID readUUID() {
        return buff.readUuid();
    }

    @Override
    public void writeDataCompound(IDataCompound data) {
        buff.writeNBTTagCompoundToBuffer(data.asNBTTagCompound());
    }

    @Override
    public IDataCompound readDataCompound() throws IOException {
        return DataElementFactory.fromNBTCompound(buff.readNBTTagCompoundFromBuffer());
    }

    @Override
    public void writeItemStack(ItemStack stack) {
        buff.writeItemStackToBuffer(stack);
    }

    @Override
    public ItemStack readItemStack() throws IOException {
        return buff.readItemStackFromBuffer();
    }
}

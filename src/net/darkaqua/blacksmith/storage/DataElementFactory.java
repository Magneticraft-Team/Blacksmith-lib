package net.darkaqua.blacksmith.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by cout970 on 21/12/2015.
 */
public abstract class DataElementFactory {

    protected static DataElementFactory INSTANCE;

    static {
        BS_DataElementFactory.init();
    }

    public static IDataCompound createDataCompound() {
        return INSTANCE.newDataCompound();
    }

    public static IDataList createDataList() {
        return INSTANCE.newDataList();
    }

    public static IDataCompound fromNBTCompound(NBTTagCompound tag) {
        if (tag == null) return null;
        return new NBTTagCompoundWrapper(tag);
    }

    public static IDataList fromNBTList(NBTTagList nbt) {
        if (nbt == null) return null;
        return new NBTTagListWrapper(nbt);
    }

    protected abstract IDataCompound newDataCompound();

    protected abstract IDataList newDataList();
}

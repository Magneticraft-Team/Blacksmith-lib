package net.darkaqua.blacksmith.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_DataElementFactory extends DataElementFactory {

    protected BS_DataElementFactory() {
    }

    @Override
    protected IDataCompound newDataCompound() {
        return fromNBTCompound(new NBTTagCompound());
    }

    @Override
    protected IDataList newDataList() {
        return fromNBTList(new NBTTagList());
    }


    public static void init() {
        INSTANCE = new BS_DataElementFactory();
    }
}

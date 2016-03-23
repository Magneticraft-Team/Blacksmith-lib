package net.darkaqua.blacksmith.scanner;

import net.darkaqua.blacksmith.util.Direction;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cout970 on 13/11/2015.
 */
public abstract class ObjectScanner {

    protected static ObjectScanner INSTANCE;

    static {
        BS_ObjectScanner.init();
    }

    public static <T> T findInItem(Item toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInItemStack(ItemStack toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInBlock(Block toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInBlockVariant(IBlockState toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInTileEntity(TileEntity toScan, Class<T> clazz) {
        return INSTANCE.find(toScan, clazz);
    }

    public static <T> T findInItem(Item toScan, Capability<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInItemStack(ItemStack toScan, Capability<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInBlock(Block toScan, Capability<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInBlockVariant(IBlockState toScan, Capability<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    public static <T> T findInTileEntity(TileEntity toScan, Capability<T> id, Direction dir) {
        return INSTANCE.findInterface(toScan, id, dir);
    }

    protected abstract <T> T find(Object toScan, Class<T> clazz);

    protected abstract <T> T findInterface(Object toScan, Capability<T> id, Direction dir);
}

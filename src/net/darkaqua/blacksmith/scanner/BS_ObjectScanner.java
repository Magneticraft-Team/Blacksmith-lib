package net.darkaqua.blacksmith.scanner;

import net.darkaqua.blacksmith.util.Direction;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by cout970 on 21/12/2015.
 */
public class BS_ObjectScanner extends ObjectScanner {

    private BS_ObjectScanner() {
    }

    public static void init() {
        INSTANCE = new BS_ObjectScanner();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T findInterface(Object toScan, Capability<T> id, Direction dir) {
        ICapabilityProvider provider = find(toScan, ICapabilityProvider.class);
        if (provider != null) {
            if (provider.hasCapability(id, dir == null ? null : dir.toEnumFacing())) {
                return provider.getCapability(id, dir == null ? null : dir.toEnumFacing());
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T find(Object toScan, Class<T> clazz) {
        if (clazz == null) return null;

        if (toScan instanceof ItemStack) {
            if (isInstance(toScan, clazz)) {
                return (T) toScan;
            } else {
                return find(((ItemStack) toScan).getItem(), clazz);
            }
        } else if (toScan instanceof IBlockState) {
            if (isInstance(toScan, clazz)) {
                return (T) toScan;
            } else {
                return find(((IBlockState) toScan).getBlock(), clazz);
            }
        } else if (toScan instanceof Item) {
            if (isInstance(toScan, clazz)) {
                return (T) toScan;
            }
        } else if (toScan instanceof Block) {
            if (isInstance(toScan, clazz)) {
                return (T) toScan;
            }
        } else if (toScan instanceof TileEntity) {
            if (isInstance(toScan, clazz)) {
                return (T) toScan;
            }
        }
        return null;
    }

    private static boolean isInstance(Object obj, Class<?> clazz) {
        return obj != null && clazz.isAssignableFrom(obj.getClass());
    }
}

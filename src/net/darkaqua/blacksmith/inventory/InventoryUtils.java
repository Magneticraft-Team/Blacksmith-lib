package net.darkaqua.blacksmith.inventory;

import net.darkaqua.blacksmith.storage.IDataCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by cout970 on 20/12/2015.
 */
public final class InventoryUtils {

    private InventoryUtils() {
    }

    public static boolean areExactlyEqual(ItemStack a, ItemStack b) {
        return a == b || !(a == null || b == null)
                && a.getItem().equals(b.getItem())
                && a.stackSize == b.stackSize
                && a.getItemDamage() == b.getItemDamage()
                && areDataCompoundEqual(a.getTagCompound(), b.getTagCompound());
    }

    public static boolean areEqual(ItemStack a, ItemStack b) {
        return a == b || !(a == null || b == null)
                && a.getItem().equals(b.getItem())
                && a.getItemDamage() == b.getItemDamage()
                && areDataCompoundEqual(a.getTagCompound(), b.getTagCompound());
    }

    public static boolean areDataCompoundEqual(NBTTagCompound a, NBTTagCompound b) {
        return a == b || !(a == null || b == null) && a.equals(b);
    }

    public static boolean areDataCompoundEqual(IDataCompound a, IDataCompound b) {
        return a == b || !(a == null || b == null) && a.equals(b);
    }

    public static boolean areOreDictEquivalent(ItemStack a, ItemStack b) {
        int[] Aids = OreDictionary.getOreIDs(a);
        int[] Bids = OreDictionary.getOreIDs(b);
        for (int Aid : Aids) {
            for (int Bid : Bids) {
                if (Aid == Bid) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canInsertSomething(IItemHandler inv, int slot, ItemStack stack) {
        ItemStack result = inv.insertItem(slot, stack, true);
        return !areExactlyEqual(stack, result);
    }

    public static boolean insertAllInInventory(IItemHandler inv, ItemStack stack) {
        if (canInsertAllInInventory(inv, stack)) {
            if (stack == null)
                return true;
            for (int i = 0; i < inv.getSlots(); i++) {
                stack = inv.insertItem(i, stack, false);
                if (stack == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canInsertAllInInventory(IItemHandler inv, ItemStack stack) {
        if (inv == null)
            return false;
        if (stack == null)
            return true;

        for (int i = 0; i < inv.getSlots(); i++) {
            stack = inv.insertItem(i, stack, true);
            if (stack == null) {
                return true;
            }
        }
        return false;
    }
}

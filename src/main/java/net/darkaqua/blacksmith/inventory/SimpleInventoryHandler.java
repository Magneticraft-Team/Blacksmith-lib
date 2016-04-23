package net.darkaqua.blacksmith.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * Created by cout970 on 20/12/2015.
 */
public class SimpleInventoryHandler implements IItemHandlerModifiable, INBTSerializable<NBTTagCompound> {

    protected ItemStack[] inventory;
    protected int maxStackSize = 64;

    public SimpleInventoryHandler(int slots, int stackSize){
        this(slots);
        maxStackSize = stackSize;
    }

    public SimpleInventoryHandler(int slots) {
        inventory = new ItemStack[slots];
    }

    @Override
    public int getSlots() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulated) {
        if (stack == null)
            return null;

        if (getStackInSlot(slot) == null) {
            int capacity = Math.min(maxStackSize, stack.getMaxStackSize());
            if (capacity >= stack.stackSize) {
                if (!simulated) {
                    setStackInSlot(slot, stack.copy());
                }
                return null;
            } else {
                if (!simulated) {
                    ItemStack insert = stack.copy();
                    insert.stackSize = capacity;
                    setStackInSlot(slot, insert);
                    ItemStack copy = stack.copy();
                    copy.stackSize -= capacity;
                    return copy;
                } else {
                    ItemStack copy = stack.copy();
                    copy.stackSize -= capacity;
                    return copy;
                }
            }
        } else if (InventoryUtils.areEqual(getStackInSlot(slot), stack)) {
            int capacity = Math.min(maxStackSize, stack.getMaxStackSize());
            int space = capacity - getStackInSlot(slot).stackSize;
            if (space >= stack.stackSize) {
                if (!simulated) {
                    ItemStack copy = stack.copy();
                    copy.stackSize += getStackInSlot(slot).stackSize;
                    setStackInSlot(slot, copy);
                }
                return null;
            } else {
                if (!simulated) {
                    ItemStack copy = stack.copy();
                    copy.stackSize -= space;
                    getStackInSlot(slot).stackSize = capacity;
                    return copy;
                } else {
                    ItemStack copy = stack.copy();
                    copy.stackSize -= space;
                    return copy;
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulated) {
        ItemStack storage = getStackInSlot(slot);
        if (storage == null || amount <= 0) {
            return null;
        }
        if (storage.stackSize > amount) {
            ItemStack ret = storage.copy();
            if (!simulated) {
                storage.stackSize -= amount;
            }
            ret.stackSize = amount;
            return ret;
        } else {
            if (!simulated) {
                setStackInSlot(slot, null);
            }
            return storage.copy();
        }
    }

    public void load(NBTTagCompound tag, String key) {
        NBTTagList list = tag.getTagList(key, new NBTTagCompound().getId());
        clear();
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound cmp = list.getCompoundTagAt(i);
            int slot = cmp.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < getSlots()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(cmp);
            }
        }
    }

    private void clear() {
        for (int i = 0; i < getSlots(); i++) {
            inventory[i] = null;
        }
    }

    public void save(NBTTagCompound tag, String key) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < getSlots(); i++) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound data = new NBTTagCompound();
                data.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(data);
                list.appendTag(data);
            }
        }
        tag.setTag(key, list);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        save(tag, "inv");
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        load(nbt, "inv");
    }
}

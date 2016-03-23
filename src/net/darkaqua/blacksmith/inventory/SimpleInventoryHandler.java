package net.darkaqua.blacksmith.inventory;

import net.darkaqua.blacksmith.storage.DataElementFactory;
import net.darkaqua.blacksmith.storage.IDataCompound;
import net.darkaqua.blacksmith.storage.IDataList;
import net.darkaqua.blacksmith.storage.IDataStorage;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * Created by cout970 on 20/12/2015.
 */
public class SimpleInventoryHandler implements IItemHandlerModifiable, IDataStorage {

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

    public void load(IDataCompound tag, String key) {
        IDataList list = tag.getDataList(key);
        clear();
        for (int i = 0; i < list.getSize(); i++) {
            IDataCompound cmp = list.getDataCompound(i);
            int slot = cmp.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < getSlots()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(cmp.asNBTTagCompound());
            }
        }
    }

    private void clear() {
        for (int i = 0; i < getSlots(); i++) {
            inventory[i] = null;
        }
    }

    public void save(IDataCompound tag, String key) {
        IDataList list = DataElementFactory.createDataList();
        for (int i = 0; i < getSlots(); i++) {
            if (getStackInSlot(i) != null) {
                IDataCompound data = DataElementFactory.createDataCompound();
                data.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(data.asNBTTagCompound());
                list.addDataCompound(data);
            }
        }
        tag.setDataElement(key, list);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public void loadData(IDataCompound data) {
        load(data, "inv");
    }

    @Override
    public void saveData(IDataCompound data) {
        save(data, "inv");
    }
}

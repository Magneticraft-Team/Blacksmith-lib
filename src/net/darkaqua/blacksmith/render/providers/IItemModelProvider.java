package net.darkaqua.blacksmith.render.providers;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 16/12/2015.
 */
public interface IItemModelProvider {

    IBakedModel getModelForItemStack(ItemStack stack);

    void reloadModels();
}

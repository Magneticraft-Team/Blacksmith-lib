package net.darkaqua.blacksmith.render.providers;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public interface IItemModelSelector {

    IBakedModel select(ItemStack data, Map<String, IModelIdentifier> map);
}

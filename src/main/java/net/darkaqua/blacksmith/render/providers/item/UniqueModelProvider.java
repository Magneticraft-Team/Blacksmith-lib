package net.darkaqua.blacksmith.render.providers.item;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.providers.IItemModelProvider;
import net.darkaqua.blacksmith.render.providers.factory.IModelFactory;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 22/03/2016.
 */
public class UniqueModelProvider implements IItemModelProvider {

    protected IModelIdentifier model;
    protected IModelFactory factory;

    public UniqueModelProvider(IModelFactory factory) {
        this.factory = factory;
    }

    @Override
    public IBakedModel getModelForItemStack(ItemStack stack) {
        return model.getBakedModel();
    }

    @Override
    public void reloadModels() {
        model = factory.createModels().values().stream().findFirst().get();
    }
}

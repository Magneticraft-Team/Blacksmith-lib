package net.darkaqua.blacksmith.render.providers.block;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.providers.IBlockModelProvider;
import net.darkaqua.blacksmith.render.providers.factory.IModelFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 22/03/2016.
 */
public class ItemOnlyModelProvider implements IBlockModelProvider {

    protected IModelIdentifier model;
    protected IModelFactory factory;

    public ItemOnlyModelProvider(IModelFactory factory) {
        this.factory = factory;
    }

    @Override
    public IBakedModel getModelForBlockData(IBlockState variant) {
        return EmptyBlockModelProvider.model;
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

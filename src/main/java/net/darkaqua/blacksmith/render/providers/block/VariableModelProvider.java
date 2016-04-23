package net.darkaqua.blacksmith.render.providers.block;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.providers.IBlockModelProvider;
import net.darkaqua.blacksmith.render.providers.IBlockModelSelector;
import net.darkaqua.blacksmith.render.providers.factory.IModelFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public class VariableModelProvider implements IBlockModelProvider {

    protected Map<String, IModelIdentifier> models;
    protected IModelFactory factory;
    protected IBlockModelSelector selector;

    public VariableModelProvider(IModelFactory factory, IBlockModelSelector selector) {
        this.factory = factory;
        this.selector = selector;
    }

    @Override
    public IBakedModel getModelForBlockData(IBlockState data) {
        return selector.select(data, models);
    }

    @Override
    public IBakedModel getModelForItemStack(ItemStack stack) {
        return selector.select(stack, models);
    }

    @Override
    public void reloadModels() {
        models = factory.createModels();
    }
}

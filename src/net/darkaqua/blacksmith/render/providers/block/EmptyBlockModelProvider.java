package net.darkaqua.blacksmith.render.providers.block;


import net.darkaqua.blacksmith.render.internal.EmptyBakedModel;
import net.darkaqua.blacksmith.render.providers.IBlockModelProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by cout970 on 28/12/2015.
 */
public class EmptyBlockModelProvider implements IBlockModelProvider {

    public static final IBakedModel model = new EmptyBakedModel();

    @Override
    public IBakedModel getModelForBlockData(IBlockState variant) {
        return model;
    }

    @Override
    public IBakedModel getModelForItemStack(ItemStack stack) {
        return model;
    }

    @Override
    public void reloadModels() {}
}

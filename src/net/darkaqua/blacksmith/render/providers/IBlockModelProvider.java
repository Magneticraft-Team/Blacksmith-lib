package net.darkaqua.blacksmith.render.providers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;

/**
 * Created by cout970 on 14/12/2015.
 */
public interface IBlockModelProvider extends IItemModelProvider{

    IBakedModel getModelForBlockData(IBlockState variant);
}

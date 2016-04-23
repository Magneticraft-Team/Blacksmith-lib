package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.model.IBakedModelPart;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.util.WorldRef;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cout970 on 20/12/2015.
 */
public interface IRenderManager {

    void irenderItemStack(ItemStack stack, Vect3d pos, ItemCameraTransforms.TransformType place);

    void irenderModelPartsDynamicLight(List<IModelPart> parts);

    void irenderModelPartsStaticLight(List<IBakedModelPart> parts, WorldRef ref, Vect3d offset);
}

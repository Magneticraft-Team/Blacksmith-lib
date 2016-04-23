package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.internal.BSRenderManager;
import net.darkaqua.blacksmith.render.model.IBakedModelPart;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.util.WorldRef;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cout970 on 19/03/2016.
 */
public abstract class RenderManager implements IRenderManager {

    protected static RenderManager INSTANCE;

    static {
        BSRenderManager.init();
    }

    public static void renderItemStack(ItemStack stack, Vect3d pos, ItemCameraTransforms.TransformType place) {
        INSTANCE.irenderItemStack(stack, pos, place);
    }

    public static void renderModelPartsDynamicLight(List<IModelPart> parts) {
        INSTANCE.irenderModelPartsDynamicLight(parts);
    }

    public static void renderModelPartsStaticLight(List<IBakedModelPart> parts, WorldRef ref, Vect3d offset) {
        INSTANCE.irenderModelPartsStaticLight(parts, ref, offset);
    }

    public IRenderManager getInstance() {
        return INSTANCE;
    }


}

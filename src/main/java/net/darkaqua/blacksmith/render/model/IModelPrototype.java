package net.darkaqua.blacksmith.render.model;

import net.darkaqua.blacksmith.render.ITexture;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

import javax.vecmath.Matrix4f;
import java.util.List;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface IModelPrototype {

    List<IModelPart> getModelParts();

    boolean isAmbientOcclusion();

    boolean isGui3d();

    ITexture getParticleTexture();

    Matrix4f getModelPerspective(ItemCameraTransforms.TransformType cameraTransformType);
}

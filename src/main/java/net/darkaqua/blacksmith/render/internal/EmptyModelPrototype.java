package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

import javax.vecmath.Matrix4f;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 22/03/2016.
 */
public class EmptyModelPrototype implements IModelPrototype {
    @Override
    public List<IModelPart> getModelParts() {
        return new LinkedList<>();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public ITexture getParticleTexture() {
        return null;
    }

    @Override
    public Matrix4f getModelPerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f mat = new Matrix4f();
        mat.setIdentity();
        return mat;
    }
}

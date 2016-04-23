package net.darkaqua.blacksmith.modelloader.techne;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

/**
 * Created by cout970 on 20/03/2016.
 */
public class TechneModelPrototype implements IModelPrototype {

    private TechneModelLoader.TechneModelPart part;

    public TechneModelPrototype(TechneModelLoader.TechneModelPart part) {
        this.part = part;
    }

    @Override
    public List<IModelPart> getModelParts() {
        return Collections.singletonList(part);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public ITexture getParticleTexture() {
        return getModelParts().get(0).getTexture();
    }

    @Override
    public Matrix4f getModelPerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f mat = new Matrix4f();
        mat.setIdentity();
        return mat;
    }
}

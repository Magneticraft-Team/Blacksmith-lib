package net.darkaqua.blacksmith.render.model.part;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 13/03/2016.
 */
public class SimpleBlockModel implements IModelPrototype {

    protected List<IModelPart> components;
//    protected Function<RenderPlace, RenderTransformation> transform;

    public SimpleBlockModel(IModelPart... component) {
        this(Lists.newArrayList(component));
    }

    public SimpleBlockModel(IModelPart component
//            , Function<RenderPlace, RenderTransformation> transform
    ) {
        components = new ArrayList<>(1);
        components.add(component);
//        this.transform = transform;
    }

    public SimpleBlockModel(List<IModelPart> component) {
        components = new ArrayList<>(component.size());
        components.addAll(component);
//        transform = place -> {
//            if (place == RenderPlace.THIRD_PERSON || place == RenderPlace.THIRD_PERSON_LEFT_HAND || place == RenderPlace.THIRD_PERSON_RIGHT_HAND) {
//                return new RenderTransformation(new Vect3d(0, 1.5, -2.75).multiply(0.0625F), new Vect3d(10.0, -45.0, 170.0), new Vect3d(0.375f, 0.375f, 0.375f));
//            }
//            return null;
//        };
    }

    @Override
    public List<IModelPart> getModelParts() {
        return components;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public ITexture getParticleTexture() {
        return components.get(0).getTexture();
    }

    @Override
    public Matrix4f getModelPerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f mat = new Matrix4f();
        mat.setIdentity();
        return mat;
    }
}
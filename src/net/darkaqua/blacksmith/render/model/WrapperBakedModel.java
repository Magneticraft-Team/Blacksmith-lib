package net.darkaqua.blacksmith.render.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.List;
import java.util.function.Function;

/**
 * Created by cout970 on 22/03/2016.
 */
public class WrapperBakedModel implements IPerspectiveAwareModel {

    protected IBakedModel model;
    protected Function<ItemCameraTransforms.TransformType, Matrix4f> func;

    public WrapperBakedModel(Function<ItemCameraTransforms.TransformType, Matrix4f> func) {
        this.func = func;
    }

    public IBakedModel getModel() {
        return model;
    }

    public void setModel(IBakedModel model) {
        this.model = model;
    }

    @Override
    public VertexFormat getFormat() {
        return null;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return model.getFaceQuads(p_177551_1_);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return model.getGeneralQuads();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return model.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return model.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return model.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return model.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return model.getItemCameraTransforms();
    }

    @Override
    public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return new ImmutablePair<>(this, func.apply(cameraTransformType));
    }
}

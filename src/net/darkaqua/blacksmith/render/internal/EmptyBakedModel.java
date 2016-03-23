package net.darkaqua.blacksmith.render.internal;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 20/03/2016.
 */
public class EmptyBakedModel implements IBakedModel {

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return new LinkedList<>();
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return new LinkedList<>();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
    }
}

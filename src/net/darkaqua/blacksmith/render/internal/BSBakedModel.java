package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IBakedQuad;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.darkaqua.blacksmith.render.model.IQuad;
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
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 19/03/2016.
 */
public class BSBakedModel implements IBakedModel, IPerspectiveAwareModel {

    private IModelPrototype prototype;
    private List<BakedQuad> generalQuads = new LinkedList<>();
    private Map<EnumFacing, List<BakedQuad>> faceQuads = new EnumMap<>(EnumFacing.class);

    public BSBakedModel(IModelPrototype prototype) {
        this.prototype = prototype;
        for(EnumFacing e : EnumFacing.values()){
            faceQuads.put(e, new LinkedList<>());
        }
        for (IModelPart p : prototype.getModelParts()) {
            ITexture tex = p.getTexture();
            for (IQuad q : p.getQuads()) {
                IBakedQuad baked = ModelFactory.bakeQuad(q, tex);
                if (q.getSide() == null){
                    generalQuads.add(baked.getBakedQuad());
                }else{
                    List<BakedQuad> side = faceQuads.get(q.getSide().toEnumFacing());
                    side.add(baked.getBakedQuad());
                }
            }
        }
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        return faceQuads.get(side);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return generalQuads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return prototype.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return prototype.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return prototype.getParticleTexture().getSprite();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return new ImmutablePair<>(this, prototype.getModelPerspective(cameraTransformType));
    }

    @Override
    public VertexFormat getFormat() {
        return null;
    }
}

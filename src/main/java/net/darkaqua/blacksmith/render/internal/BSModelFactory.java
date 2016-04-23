package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IBakedQuad;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 16/03/2016.
 */
public class BSModelFactory extends ModelFactory {

    private static List<ModelCallback> callbacks = new LinkedList<>();

    private BSModelFactory() {
    }

    public static void init() {
        INSTANCE = new BSModelFactory();
    }

    public static void onModelBakeEvent(ModelBakeEvent event) {
        for (ModelCallback model : callbacks) {
            IBakedModel baked = new BSBakedModel(model.getModelPrototype());
            model.onModelBake(event.modelLoader, baked);
        }
    }

    @Override
    public IModelIdentifier ibakeModel(IModelPrototype prototipe) {
        ModelCallback model = new ModelCallback(prototipe);
        callbacks.add(model);
        return model;
    }

    @Override
    public IBakedQuad ibakeQuad(IQuad quad, ITexture texture) {
        UnBakedQuad uQuad = new UnBakedQuad(quad.getSide() == null ? null : quad.getSide());

        TextureAtlasSprite sprite = texture.getSprite();
        if (sprite == null) { throw new IllegalStateException("Invalid texture for quad: " + quad); }

        Vect3d[] vertex = quad.getVertex();
        Vect2d[] uv = quad.getUV();

        for (int i = 0; i < 4; i++) {
            Vect3d currentVetex = vertex[i];
            Vect2d currentUV = new Vect2d(sprite.getInterpolatedU(uv[i].getX() * 16), sprite.getInterpolatedV(uv[i].getY() * 16));
            uQuad.addVertex(currentVetex, currentUV);
        }

        uQuad.setShade(true);
        return new BakedQuadWrapper(quad, texture, uQuad.bake());
    }

    @Override
    public IModelIdentifier iregisterModel(ModelCallback model) {
        callbacks.add(model);
        return model;
    }
}

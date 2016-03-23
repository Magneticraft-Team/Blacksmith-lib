package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IBakedQuad;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.minecraft.client.renderer.block.model.BakedQuad;

/**
 * Created by cout970 on 16/03/2016.
 */
public class BakedQuadWrapper implements IBakedQuad {

    private IQuad quad;
    private ITexture texture;
    private BakedQuad bake;

    public BakedQuadWrapper(IQuad quad, ITexture texture, BakedQuad bake) {
        this.quad = quad;
        this.texture = texture;
        this.bake = bake;
    }

    @Override
    public IQuad getQuad() {
        return quad;
    }

    @Override
    public ITexture getTexture() {
        return texture;
    }

    @Override
    public BakedQuad getBakedQuad() {
        return bake;
    }
}

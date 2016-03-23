package net.darkaqua.blacksmith.render.model;

import net.darkaqua.blacksmith.render.ITexture;
import net.minecraft.client.renderer.block.model.BakedQuad;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface IBakedQuad {

    IQuad getQuad();

    ITexture getTexture();

    BakedQuad getBakedQuad();
}

package net.darkaqua.blacksmith.render.model;

import net.darkaqua.blacksmith.render.ITexture;

import java.util.List;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface IModelPart {

    ITexture getTexture();

    List<IQuad> getQuads();
}

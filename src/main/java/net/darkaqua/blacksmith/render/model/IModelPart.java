package net.darkaqua.blacksmith.render.model;

import net.darkaqua.blacksmith.render.ITexture;

import java.util.List;

public interface IModelPart {

    ITexture getTexture();

    List<IQuad> getQuads();
}

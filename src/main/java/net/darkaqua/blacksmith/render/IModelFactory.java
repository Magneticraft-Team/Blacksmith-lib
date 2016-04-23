package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.internal.ModelCallback;
import net.darkaqua.blacksmith.render.model.IBakedQuad;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.darkaqua.blacksmith.render.model.IQuad;

/**
 * Created by cout970 on 19/03/2016.
 */
public interface IModelFactory {

    IBakedQuad ibakeQuad(IQuad quad, ITexture texture);

    IModelIdentifier iregisterModel(ModelCallback callback);

    IModelIdentifier ibakeModel(IModelPrototype prototipe);
}

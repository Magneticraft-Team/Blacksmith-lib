package net.darkaqua.blacksmith.render.model;

import net.minecraft.client.resources.model.IBakedModel;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface IModelIdentifier {

    IModelPrototype getModelPrototype();

    IBakedModel getBakedModel();
}

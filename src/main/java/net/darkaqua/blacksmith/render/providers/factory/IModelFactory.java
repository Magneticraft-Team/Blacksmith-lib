package net.darkaqua.blacksmith.render.providers.factory;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public interface IModelFactory {

    Map<String, IModelIdentifier> createModels();
}

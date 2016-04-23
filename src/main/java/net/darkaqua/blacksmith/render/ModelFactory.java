package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.internal.BSModelFactory;
import net.darkaqua.blacksmith.render.internal.ModelCallback;
import net.darkaqua.blacksmith.render.model.IBakedQuad;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.darkaqua.blacksmith.render.model.IQuad;

/**
 * Created by cout970 on 16/03/2016.
 */
public abstract class ModelFactory implements IModelFactory{

    protected static ModelFactory INSTANCE;

    static {
        BSModelFactory.init();
    }

    public static IModelIdentifier bakeModel(IModelPrototype prototype) {
        return INSTANCE.ibakeModel(prototype);
    }

    public static IModelIdentifier registerModel(ModelCallback callback){
        return INSTANCE.iregisterModel(callback);
    }

    public static IBakedQuad bakeQuad(IQuad quad, ITexture texture) {
        return INSTANCE.ibakeQuad(quad, texture);
    }
}

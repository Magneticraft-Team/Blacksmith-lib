package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by cout970 on 19/03/2016.
 */
public class ModelCallback implements IModelIdentifier {

    protected IModelPrototype prototype;
    protected IBakedModel model;

    public ModelCallback(IModelPrototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public IModelPrototype getModelPrototype() {
        return prototype;
    }

    @Override
    public IBakedModel getBakedModel() {
        return model;
    }

    public void onModelBake(ModelLoader loader, IBakedModel baked){
        this.model = baked;
    }
}

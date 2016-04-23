package net.darkaqua.blacksmith.modelloader.techne;

import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPrototype;
import net.darkaqua.blacksmith.render.providers.factory.IModelFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cout970 on 20/03/2016.
 */
public class TechneModelFactory implements IModelFactory {

    protected TechneModelLoader.TechneModelPart part;

    public TechneModelFactory(TechneModelLoader.TechneModelPart part) {
        this.part = part;
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> map = new HashMap<>();
        IModelIdentifier identifier = ModelFactory.bakeModel(getPrototype());
        map.put("all", identifier);
        return map;
    }

    public IModelPrototype getPrototype(){
        return new TechneModelPrototype(part);
    }
}

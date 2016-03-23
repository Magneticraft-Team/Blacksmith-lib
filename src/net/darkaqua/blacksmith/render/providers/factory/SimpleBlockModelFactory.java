package net.darkaqua.blacksmith.render.providers.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.part.ModelPartBlockSide;
import net.darkaqua.blacksmith.render.model.part.SimpleBlockModel;
import net.darkaqua.blacksmith.util.Direction;

import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public class SimpleBlockModelFactory implements IModelFactory {

    protected Map<Direction, ITexture> textures;
    protected boolean singleTexture;

    public SimpleBlockModelFactory(ITexture texture) {
        textures = Maps.newEnumMap(Direction.class);
        for (Direction d : Direction.values()) {
            textures.put(d, texture);
        }
        singleTexture = true;
    }

    public SimpleBlockModelFactory(Map<Direction, ITexture> texture) {
        textures = Maps.newHashMap(texture);
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> models = Maps.newHashMap();
        if (singleTexture) {
            IModelPart part = new ModelPartBlockSide(Direction.values(), textures.get(Direction.UP));
            models.put("all", ModelFactory.bakeModel(new SimpleBlockModel(part)));
        } else {
            List<IModelPart> parts = Lists.newArrayList();
            for (Direction d : Direction.values()) {
                parts.add(new ModelPartBlockSide(d, textures.get(d)));
            }
            models.put("all", ModelFactory.bakeModel(new SimpleBlockModel(parts)));
        }
        return models;
    }
}

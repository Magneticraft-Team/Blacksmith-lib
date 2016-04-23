package net.darkaqua.blacksmith.render.providers.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.part.ModelPartBlockSide;
import net.darkaqua.blacksmith.render.model.part.SimpleBlockModel;
import net.minecraft.util.EnumFacing;

import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public class SimpleBlockModelFactory implements IModelFactory {

    protected Map<EnumFacing, ITexture> textures;
    protected boolean singleTexture;

    public SimpleBlockModelFactory(ITexture texture) {
        textures = Maps.newEnumMap(EnumFacing.class);
        for (EnumFacing d : EnumFacing.values()) {
            textures.put(d, texture);
        }
        singleTexture = true;
    }

    public SimpleBlockModelFactory(Map<EnumFacing, ITexture> texture) {
        textures = Maps.newHashMap(texture);
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> models = Maps.newHashMap();
        if (singleTexture) {
            IModelPart part = new ModelPartBlockSide(EnumFacing.values(), textures.get(EnumFacing.UP));
            models.put("all", ModelFactory.bakeModel(new SimpleBlockModel(part)));
        } else {
            List<IModelPart> parts = Lists.newArrayList();
            for (EnumFacing d : EnumFacing.values()) {
                parts.add(new ModelPartBlockSide(d, textures.get(d)));
            }
            models.put("all", ModelFactory.bakeModel(new SimpleBlockModel(parts)));
        }
        return models;
    }
}

package net.darkaqua.blacksmith.render.providers.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.block.IRotatableBlock;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.part.ModelPartBlockSide;
import net.darkaqua.blacksmith.render.model.part.SimpleBlockModel;
import net.minecraft.util.EnumFacing;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by cout970 on 13/03/2016.
 */
public class RotatableBlockModelFactory implements IModelFactory {

    protected Map<EnumFacing, ITexture> textures;
    protected IRotatableBlock block;

    public RotatableBlockModelFactory(IRotatableBlock block, Map<EnumFacing, ITexture> texture) {
        textures = Maps.newHashMap(texture);
        this.block = block;
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> models = Maps.newHashMap();
        for (EnumFacing dir : block.getValidRotations()) {
            List<IModelPart> parts = Lists.newArrayList();
            for (EnumFacing d : EnumFacing.values()) {
                parts.add(new ModelPartBlockSide(d, getTextureGetter(dir, textures::get).apply(d)));
            }
            models.put(dir.name(), ModelFactory.bakeModel(new SimpleBlockModel(parts)));
        }
        return models;
    }

    private Function<EnumFacing, ITexture> getTextureGetter(EnumFacing dir, Function<EnumFacing, ITexture> textures) {
        switch (dir) {
            case NORTH: {
                return textures;
            }
            case SOUTH: {
                return direction -> textures.apply(direction.getOpposite());
            }
            case EAST: {
                return direction -> textures.apply(direction.rotateYCCW());
            }
            case WEST: {
                return direction -> textures.apply(direction.rotateY());
            }
            case UP: {
                return direction -> textures.apply(direction.rotateAround(EnumFacing.Axis.X));
            }
            case DOWN: {
                return direction -> {
                    EnumFacing newDir = direction.rotateAround(EnumFacing.Axis.X);
                    return textures.apply(direction == newDir ? direction : newDir.getOpposite());
                };
            }
        }
        return textures;
    }
}

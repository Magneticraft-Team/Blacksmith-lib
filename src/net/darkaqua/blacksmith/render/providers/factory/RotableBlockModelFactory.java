package net.darkaqua.blacksmith.render.providers.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.darkaqua.blacksmith.block.IRotableBlock;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.part.ModelPartBlockSide;
import net.darkaqua.blacksmith.render.model.part.SimpleBlockModel;
import net.darkaqua.blacksmith.util.Direction;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by cout970 on 13/03/2016.
 */
public class RotableBlockModelFactory implements IModelFactory {

    protected Map<Direction, ITexture> textures;
    protected IRotableBlock block;

    public RotableBlockModelFactory(IRotableBlock block, Map<Direction, ITexture> texture) {
        textures = Maps.newHashMap(texture);
        this.block = block;
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> models = Maps.newHashMap();
        for (Direction dir : block.getValidRotations()) {
            List<IModelPart> parts = Lists.newArrayList();
            for (Direction d : Direction.values()) {
                parts.add(new ModelPartBlockSide(d, getTextureGetter(dir, textures::get).apply(d)));
            }
            models.put(dir.name(), ModelFactory.bakeModel(new SimpleBlockModel(parts)));
        }
        return models;
    }

    private Function<Direction, ITexture> getTextureGetter(Direction dir, Function<Direction, ITexture> textures) {
        if (dir == Direction.NORTH) { return textures; }
        if (dir == Direction.SOUTH) { return direction -> textures.apply(direction.opposite()); }
        if (dir == Direction.EAST) { return direction -> textures.apply(direction.rotate(Direction.Axis.Y, false)); }
        if (dir == Direction.WEST) { return direction -> textures.apply(direction.rotate(Direction.Axis.Y, true)); }
        if (dir == Direction.UP) { return direction -> textures.apply(direction.rotate(Direction.Axis.X, true)); }
        if (dir == Direction.DOWN) { return direction -> textures.apply(direction.rotate(Direction.Axis.X, false)); }
        return textures;
    }
}

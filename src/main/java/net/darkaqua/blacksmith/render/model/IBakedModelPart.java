package net.darkaqua.blacksmith.render.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;

import java.util.List;

/**
 * Created by cout970 on 19/03/2016.
 */
public interface IBakedModelPart {

    List<BakedQuad> getFaceQuads(EnumFacing side);

    List<BakedQuad> getGeneralQuads();
}

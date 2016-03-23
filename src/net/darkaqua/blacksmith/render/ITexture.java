package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.util.ResourceReference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface ITexture {

    ResourceReference getResourceReference();

    TextureAtlasSprite getSprite();
}

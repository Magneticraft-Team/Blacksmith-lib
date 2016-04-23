package net.darkaqua.blacksmith.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface ITexture {

    ResourceLocation getResourceLocation();

    TextureAtlasSprite getSprite();
}

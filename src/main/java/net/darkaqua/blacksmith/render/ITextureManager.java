package net.darkaqua.blacksmith.render;

import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 19/03/2016.
 */
public interface ITextureManager {

    ITexture iregisterTexture(ResourceLocation location);

    void ibindTexture(ResourceLocation location);

    void ibindBlocksTexture();

    void ibindItemsTexture();
}

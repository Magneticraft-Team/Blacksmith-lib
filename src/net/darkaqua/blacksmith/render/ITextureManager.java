package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.util.ResourceReference;

/**
 * Created by cout970 on 19/03/2016.
 */
public interface ITextureManager {

    ITexture iregisterTexture(ResourceReference location);

    void ibindTexture(ResourceReference resourceReference);

    void ibindBlocksTexture();

    void ibindItemsTexture();
}

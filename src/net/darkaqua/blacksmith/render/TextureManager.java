package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.internal.BSTextureManager;
import net.darkaqua.blacksmith.util.ResourceReference;

/**
 * Created by cout970 on 19/03/2016.
 */
public abstract class TextureManager implements ITextureManager {

    protected static TextureManager INSTANCE;

    static {
        BSTextureManager.init();
    }

    public static ITextureManager getInstance() {
        return INSTANCE;
    }

    public static ITexture registerTexture(ResourceReference location) {
        return INSTANCE.iregisterTexture(location);
    }

    public static void bindTexture(ResourceReference resourceReference) {
        INSTANCE.ibindTexture(resourceReference);
    }

    public static void bindBlocksTexture() {
        INSTANCE.ibindBlocksTexture();
    }

    public static void bindItemsTexture() {
        INSTANCE.ibindItemsTexture();
    }
}

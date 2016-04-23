package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.render.internal.BSTextureManager;
import net.minecraft.util.ResourceLocation;

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

    public static ITexture registerTexture(ResourceLocation location) {
        return INSTANCE.iregisterTexture(location);
    }

    public static void bindTexture(ResourceLocation location) {
        INSTANCE.ibindTexture(location);
    }

    public static void bindBlocksTexture() {
        INSTANCE.ibindBlocksTexture();
    }

    public static void bindItemsTexture() {
        INSTANCE.ibindItemsTexture();
    }
}

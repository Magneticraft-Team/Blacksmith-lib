package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cout970 on 19/03/2016.
 */
public class TextureCallback implements ITexture {

    private ResourceLocation resourceLocation;
    private TextureAtlasSprite sprite;

    public TextureCallback(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    public TextureAtlasSprite getSprite() {
        return sprite;
    }

    public void onTextureLoad(TextureMap map, TextureAtlasSprite sprite) {
        this.sprite = sprite;
    }
}

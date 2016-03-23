package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.util.ResourceReference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

/**
 * Created by cout970 on 19/03/2016.
 */
public class TextureCallback implements ITexture {

    private ResourceReference resourceReference;
    private TextureAtlasSprite sprite;

    public TextureCallback(ResourceReference resourceReference) {
        this.resourceReference = resourceReference;
    }

    @Override
    public ResourceReference getResourceReference() {
        return resourceReference;
    }

    @Override
    public TextureAtlasSprite getSprite() {
        return sprite;
    }

    public void onTextureLoad(TextureMap map, TextureAtlasSprite sprite) {
        this.sprite = sprite;
    }
}

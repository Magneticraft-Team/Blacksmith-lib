package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 19/03/2016.
 */
public class BSTextureManager extends TextureManager {

    private static List<TextureCallback> callbacks = new LinkedList<>();

    private BSTextureManager() {
    }

    public static void init() {
        INSTANCE = new BSTextureManager();
    }

    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        for (TextureCallback tex : callbacks) {
            TextureAtlasSprite sprite = event.map.registerSprite(tex.getResourceLocation());
            tex.onTextureLoad(event.map, sprite);
        }
    }

    @Override
    public ITexture iregisterTexture(ResourceLocation location) {
        TextureCallback tex = new TextureCallback(location);
        callbacks.add(tex);
        return tex;
    }

    @Override
    public void ibindTexture(ResourceLocation location) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
    }

    @Override
    public void ibindBlocksTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    @Override
    public void ibindItemsTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }
}

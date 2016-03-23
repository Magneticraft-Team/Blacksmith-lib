package net.darkaqua.blacksmith.render.providers.factory;

import com.google.common.collect.ImmutableList;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.ModelFactory;
import net.darkaqua.blacksmith.render.internal.EmptyModelPrototype;
import net.darkaqua.blacksmith.render.internal.ModelCallback;
import net.darkaqua.blacksmith.render.model.IModelIdentifier;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.ModelLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 22/03/2016.
 */
public class DefaultItemModelFactory implements IModelFactory {

    private List<ITexture> layers;

    public DefaultItemModelFactory(List<ITexture> layers) {
        this.layers = layers;
    }

    @Override
    public Map<String, IModelIdentifier> createModels() {
        Map<String, IModelIdentifier> map = new HashMap<>();

        List<ResourceLocation> sprites = layers.stream().map(texture -> texture.getResourceReference().toResourceLocation()).collect(Collectors.toList());
        ImmutableList<ResourceLocation> list = ImmutableList.copyOf(sprites);
        IModel m = new ItemLayerModel(list);
        ModelCallback callBack = new ModelCallback(new EmptyModelPrototype()){
            @Override
            public void onModelBake(ModelLoader loader, IBakedModel baked){
                this.model = m.bake(m.getDefaultState(), DefaultVertexFormats.ITEM, ModelLoader.defaultTextureGetter());
            }
        };
        IModelIdentifier identifier = ModelFactory.registerModel(callBack);
        map.put("all", identifier);

        return map;
    }
}

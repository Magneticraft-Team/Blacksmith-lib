package net.darkaqua.blacksmith.modelloader.techne;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.render.RenderManager;
import net.darkaqua.blacksmith.render.TextureManager;
import net.darkaqua.blacksmith.render.model.IDynamicModel;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.vectors.Vect3d;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by cout970 on 31/01/2016.
 */
public class TechneDynamicModel implements IDynamicModel {

    protected PartSet total;
    protected Vect3d offset;

    public TechneDynamicModel(Map<String, IModelPart> parts) {
        total = new PartSet(parts);
    }

    public TechneDynamicModel(TechneModelLoader.TechneModelPart model){
        Map<String, IModelPart> parts = new HashMap<>();
        for (TechneCube c : model.getModelParts()) {
            parts.put(c.getName(), c);
        }
        total = new PartSet(parts);
    }

    @Override
    public void setOffset(Vect3d offset) {
        this.offset = offset;
    }

    @Override
    public void renderPartSet(IPartSet set) {
        ((PartSet) set).render(offset);
    }

    @Override
    public IPartSet getTotalPartSet() {
        return total;
    }

    @Override
    public IPartSet createFromNames(String... parts) {
        List<String> names = Lists.newArrayList(parts);
        Map<String, IModelPart> ids = new HashMap<>();
        total.getParts().entrySet().stream().filter(e -> names.contains(e.getKey())).forEach(e -> ids.put(e.getKey(), e.getValue()));
        return new PartSet(ids);
    }

    @Override
    public IPartSet createExcludingNames(String... parts) {
        List<String> names = Lists.newArrayList(parts);
        Map<String, IModelPart> ids = new HashMap<>();
        total.getParts().entrySet().stream().filter(e -> !names.contains(e.getKey())).forEach(e -> ids.put(e.getKey(), e.getValue()));
        return new PartSet(ids);
    }

    @Override
    public IPartSet createAllContains(String text) {
        Map<String, IModelPart> ids = new HashMap<>();
        total.getParts().entrySet().stream().filter(e -> e.getKey().contains(text)).forEach(e -> ids.put(e.getKey(), e.getValue()));
        return new PartSet(ids);
    }

    @Override
    public IPartSet createAllNotContains(String text) {
        Map<String, IModelPart> ids = new HashMap<>();
        total.getParts().entrySet().stream().filter(e -> !e.getKey().contains(text)).forEach(e -> ids.put(e.getKey(), e.getValue()));
        return new PartSet(ids);
    }

    @Override
    public IPartSet createFromFilter(Predicate<String> filter) {
        Map<String, IModelPart> ids = new HashMap<>();
        total.getParts().entrySet().stream().filter(e -> filter.test(e.getKey())).forEach(e -> ids.put(e.getKey(), e.getValue()));
        return new PartSet(ids);
    }


    private static class PartSet implements IDynamicModel.IPartSet {

        protected Map<String, IModelPart> parts;
        private int displayList;
        private boolean compiled;

        public PartSet(Map<String, IModelPart> parts) {
            this.parts = parts;
        }

        public Map<String, IModelPart> getParts() {
            return parts;
        }

        @Override
        public Set<String> getPartNames() {
            return parts.keySet();
        }

        public void render(Vect3d offset) {
            if (!compiled) {
                displayList = GL11.glGenLists(1);
                GL11.glNewList(displayList, GL11.GL_COMPILE);
                RenderManager.renderModelPartsDynamicLight(Lists.newArrayList(parts.values()));
                GL11.glEndList();
                compiled = true;
            }
            if (offset != null) {
                GL11.glPushMatrix();
                GL11.glTranslated(offset.getX(), offset.getY(), offset.getZ());
            }
            TextureManager.bindBlocksTexture();
            GL11.glCallList(displayList);
            if (offset != null) {
                GL11.glPopMatrix();
            }
        }
    }
}

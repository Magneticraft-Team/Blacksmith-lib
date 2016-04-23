package net.darkaqua.blacksmith.render.internal;

import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.RenderManager;
import net.darkaqua.blacksmith.render.model.IBakedModelPart;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.util.WorldRef;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 19/03/2016.
 */
public class BSRenderManager extends RenderManager {

    private CachedModel cache = new CachedModel();

    private BSRenderManager() {
    }

    public static void init() {
        INSTANCE = new BSRenderManager();
    }

    @Override
    public void irenderItemStack(ItemStack itemstack, Vect3d pos, ItemCameraTransforms.TransformType place) {
        net.minecraft.client.renderer.entity.RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        renderManager.renderEngine.getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);

        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(itemstack);
        int i = itemstack.stackSize;
        for (int j = 0; j < i; ++j) {
            if (ibakedmodel.isGui3d()) {
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
                ibakedmodel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
            } else {
                GlStateManager.pushMatrix();
                ibakedmodel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                float f3 = ibakedmodel.getItemCameraTransforms().ground.scale.x;
                float f4 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
                float f5 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
                GlStateManager.translate(0.0F * f3, 0.0F * f4, 0.046875F * f5);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        renderManager.renderEngine.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }


    @Override
    public void irenderModelPartsStaticLight(List<IBakedModelPart> parts, WorldRef ref, Vect3d offset) {
        if (parts.isEmpty()) { return; }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();

        if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(ref.getWorld(), ref.getPosition());

        cache.setBaked(parts);
        BlockPos pos = ref.getPosition();

        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        worldRenderer.setTranslation(offset.getX() - pos.getX(), offset.getY() - pos.getY(), offset.getZ() - pos.getZ());
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer()
                 .renderModel(world, cache, ref.getBlockState(), ref.getPosition(), worldRenderer, false);
        worldRenderer.setTranslation(0, 0, 0);
        tessellator.draw();

        RenderHelper.enableStandardItemLighting();
    }

    @Override
    public void irenderModelPartsDynamicLight(List<IModelPart> parts) {
        if (parts.isEmpty()) { return; }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);

        for (IModelPart id : parts) {
            if (id == null) { continue; }
            ITexture texture = id.getTexture();
            for (IQuad quad : id.getQuads()) {
                Vect3d[] pos = quad.getVertex();
                Vect2d[] uv = quad.getUV();
                Vect3d[] norm = quad.getNormals();
                for (int i = 0; i < 4; i++) {
                    worldRenderer
                            .pos(pos[i].getX(), pos[i].getY(), pos[i].getZ())
                            .tex(texture.getSprite().getInterpolatedU(uv[i].getX()*16d),
                                    texture.getSprite().getInterpolatedV(uv[i].getY()*16d))
                            .normal((float) norm[i].getX(), (float) norm[i].getY(), (float) norm[i].getZ())
                            .endVertex();
                }
            }
        }
        tessellator.draw();
    }

    private class CachedModel implements IBakedModel {

        public List<IBakedModelPart> baked;

        public List<IBakedModelPart> getBaked() {
            return baked;
        }

        public void setBaked(List<IBakedModelPart> baked) {

            this.baked = baked;
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            List<BakedQuad> list = new LinkedList<>();
            baked.stream().forEach(c -> list.addAll(c.getFaceQuads(side)));
            return list;
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {
            List<BakedQuad> list = new LinkedList<>();
            baked.stream().forEach(c -> list.addAll(c.getGeneralQuads()));
            return list;
        }

        @Override
        public boolean isAmbientOcclusion() {
            return false;
        }

        @Override
        public boolean isGui3d() {
            return false;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return null;
        }

        @Override
        public ItemCameraTransforms getItemCameraTransforms() {
            return null;
        }
    }
}

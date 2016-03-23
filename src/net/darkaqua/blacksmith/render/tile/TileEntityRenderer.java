package net.darkaqua.blacksmith.render.tile;

import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by cout970 on 19/03/2016.
 */
public abstract class TileEntityRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

    @Override
    public void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage) {
        renderTileEntity(te, new Vect3d(x,y,z), partialTicks, destroyStage);
    }

    public abstract void renderTileEntity(T te, Vect3d pos, float partialTicks, int destroyStage);
}

package net.darkaqua.blacksmith.render;

import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.client.model.TRSRTransformation;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Matrix4f;

/**
 * Created by cout970 on 22/03/2016.
 */
public class ItemCameraHelper {


    public static Matrix4f getMatrix(Vect3d t, Vect3d r, Vect3d s) {
        ItemTransformVec3f trans = new ItemTransformVec3f(
                new Vector3f((float)r.getX(), (float)r.getY(), (float)r.getZ()),
                new Vector3f((float)t.getX(), (float)t.getY(), (float)t.getZ()),
                new Vector3f((float)s.getX(), (float)s.getY(), (float)s.getZ())
        );
        TRSRTransformation transf = new TRSRTransformation(trans);
        return TRSRTransformation.blockCornerToCenter(transf).getMatrix();
    }
}

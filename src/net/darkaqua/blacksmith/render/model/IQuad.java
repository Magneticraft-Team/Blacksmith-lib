package net.darkaqua.blacksmith.render.model;

import net.darkaqua.blacksmith.util.Direction;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface IQuad {

    Vect3d[] getVertex();

    Vect2d[] getUV();

    Vect3d[] getNormals();

    Direction getSide();
}

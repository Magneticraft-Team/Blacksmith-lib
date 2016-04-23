package net.darkaqua.blacksmith.render.model.part;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.util.EnumFacing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 19/12/2015.
 */
public class ModelPartBlockSide implements IModelPart {

    protected ITexture texture;
    protected Set<EnumFacing> sides = new HashSet<>();
    protected List<IQuad> quads = new LinkedList<>();

    public ModelPartBlockSide(EnumFacing dir, ITexture texture) {
        this.texture = texture;
        this.sides.add(dir);
        generateQuads();
    }

    public ModelPartBlockSide(List<EnumFacing> dir, ITexture texture) {
        this.texture = texture;
        this.sides.addAll(dir);
        generateQuads();
    }

    public ModelPartBlockSide(EnumFacing[] dir, ITexture texture) {
        this(Lists.newArrayList(dir), texture);
    }

    @Override
    public ITexture getTexture() {
        return texture;
    }

    @Override
    public List<IQuad> getQuads() {
        return quads;
    }


    private void generateQuads() {
        quads.addAll(sides.stream().map(Quad::new).collect(Collectors.toList()));
    }

    private static class Quad implements IQuad {

        private static final double[][][] FULL_UV = new double[][][]{
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//DOWN
                {{0, 0}, {0, 1}, {1, 1}, {1, 0}},//UP
                {{1, 1}, {1, 0}, {0, 0}, {0, 1}},//NORTH
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//SOUTH
                {{0, 1}, {1, 1}, {1, 0}, {0, 0}},//WEST
                {{1, 1}, {1, 0}, {0, 0}, {0, 1}},//EAST
        };

        private static final double[][][] VERTEX = new double[][][]{
                {{0, 0, 0}, {1, 0, 0}, {1, 0, 1}, {0, 0, 1}},//DOWN
                {{0, 1, 0}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}},//UP
                {{0, 0, 0}, {0, 1, 0}, {1, 1, 0}, {1, 0, 0}},//NORTH
                {{0, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}},//SOUTH
                {{0, 0, 0}, {0, 0, 1}, {0, 1, 1}, {0, 1, 0}},//WEST
                {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}, {1, 0, 1}} //EAST
        };

        private EnumFacing side;
        private Vect3d[] vertex;
        private Vect2d[] uv;
        private Vect3d[] normals;

        public Quad(EnumFacing side) {
            this.side = side;
            vertex = new Vect3d[4];
            uv = new Vect2d[4];
            for (int i = 0; i < 4; i++) {
                vertex[i] = new Vect3d(VERTEX[side.ordinal()][i]);
                uv[i] = new Vect2d(FULL_UV[side.ordinal()][i]);
            }
            normals = new Vect3d[4];
            Vect3d temp1 = vertex[1].copy().sub(vertex[0]);
            Vect3d temp2 = vertex[2].copy().sub(vertex[0]);
            Vect3d norm = temp1.crossProduct(temp2).normalize();
            for (int i = 0; i < 4; i++) {
                normals[i] = norm;
            }
        }

        @Override
        public Vect3d[] getVertex() {
            return vertex;
        }

        @Override
        public Vect2d[] getUV() {
            return uv;
        }

        @Override
        public Vect3d[] getNormals() {
            return normals;
        }

        @Override
        public EnumFacing getSide() {
            return side;
        }
    }
}
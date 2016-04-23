package net.darkaqua.blacksmith.render.model.part;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.util.EnumFacing;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 24/01/2016.
 */
public class ModelPartBuilder {

    protected ITexture texture;
    protected List<IQuad> quads;
    protected Vect2d tempUV;
    protected TempData[] tempVertex = new TempData[4];
    protected EnumFacing side;
    protected int vertex;
    protected boolean useShade;

    public ModelPartBuilder(ITexture texture) {
        this.texture = texture;
        quads = new LinkedList<>();
        tempUV = Vect2d.nullVector();
    }

    public void addVertex(Vect3d pos) {
        tempVertex[vertex] = new TempData(pos.copy(), tempUV);
        vertex++;
        if (vertex >= 4) {
            vertex = 0;
            Vect3d[] vertexArray = new Vect3d[4];
            Vect2d[] uv = new Vect2d[4];
            for (int i = 0; i < 4; i++) {
                vertexArray[i] = tempVertex[i].getVertex();
                uv[i] = tempVertex[i].getUV();
            }
            ModelQuad mQuad = new ModelQuad(vertexArray, uv, side);
            quads.add(mQuad);
        }
    }

    public void addVertex(double x, double y, double z) {
        this.addVertex(new Vect3d(x, y, z));
    }

    public void addUV(Vect2d uv) {
        tempUV = uv.copy();
    }

    public void addUV(double u, double v) {
        tempUV = new Vect2d(u, v);
    }

    public void addVertexWithUV(Vect3d pos, Vect2d uv) {
        addVertexWithUV(pos.getX(), pos.getY(), pos.getZ(), uv.getX(), uv.getY());
    }

    public void addVertexWithUV(double x, double y, double z, double u, double v) {
        addUV(u, v);
        addVertex(x, y, z);
    }

    public IModelPart build() {
        if (vertex != 0) {
            throw new IllegalStateException("ModelPartBuilder is missing vertices, only " + vertex + " stored.");
        }
        ModelPart part = new ModelPart(Lists.newArrayList(quads), texture, useShade);
        quads.clear();
        return part;
    }

    public boolean isUseShade() {
        return useShade;
    }

    public void setUseShade(boolean useShade) {
        this.useShade = useShade;
    }

    public ITexture getTexture() {
        return texture;
    }

    public void setTexture(ITexture texture) {
        this.texture = texture;
    }

    public EnumFacing getSide() {
        return side;
    }

    public void setSide(EnumFacing side) {
        this.side = side;
    }

    public static class ModelPart implements IModelPart {

        protected List<IQuad> quads;
        protected ITexture texture;
        protected boolean useShade;

        public ModelPart(List<IQuad> quads, ITexture texture, boolean useShade) {
            this.quads = quads;
            this.texture = texture;
            this.useShade = useShade;
        }

        @Override
        public List<IQuad> getQuads() {
            return quads;
        }

        @Override
        public ITexture getTexture() {
            return texture;
        }
    }

    public static class ModelQuad implements IQuad {

        protected Vect3d[] vertex;
        protected Vect2d[] uv;
        protected EnumFacing side;
        protected Vect3d[] normals;

        public ModelQuad(Vect3d[] vextex, Vect2d[] uv, EnumFacing side) {
            this.vertex = vextex;
            this.uv = uv;
            this.side = side;
            normals = new Vect3d[4];
            Vect3d temp1 = vextex[1].copy().sub(vertex[0]);
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

    private class TempData {
        private Vect3d vertex;
        private Vect2d uv;

        public TempData(Vect3d vertex, Vect2d uv) {
            this.vertex = vertex;
            this.uv = uv;
        }

        public Vect3d getVertex() {
            return vertex;
        }

        public Vect2d getUV() {
            return uv;
        }
    }
}

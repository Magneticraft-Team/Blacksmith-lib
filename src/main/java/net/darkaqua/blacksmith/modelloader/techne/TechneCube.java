package net.darkaqua.blacksmith.modelloader.techne;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cout970 on 31/01/2016.
 */
public class TechneCube implements IModelPart {

    protected Quad[] quadList;
    protected String name;
    protected Vect3d cubePos;
    protected Vect3d cubeSize;
    protected ITexture texture;
    protected Vect2d textureOffset;
    protected int textureSize;
    protected boolean flipped;
    protected Vect3d rotation;
    protected Vect3d offset;

    public TechneCube(String name, Vect3d cubePos, Vect3d cubeSize, ITexture texture, Vect2d textureOffset, int textureSize) {
        this.cubePos = cubePos;
        this.name = name;
        this.cubeSize = cubeSize;
        this.texture = texture;
        this.textureOffset = textureOffset;
        this.textureSize = textureSize;
        this.offset = Vect3d.nullVector();
        this.rotation = Vect3d.nullVector();
    }

    public String getName() {
        return name;
    }

    public Vect3d getCubePos() {
        return cubePos;
    }

    public Vect3d getCubeSize() {
        return cubeSize;
    }

    @Override
    public ITexture getTexture() {
        return texture;
    }

    @Override
    public List<IQuad> getQuads() {
        createQuads();
        return Lists.newArrayList(quadList);
    }

    public Vect2d getTextureOffset() {
        return textureOffset;
    }

    public int getTextureSize() {
        return textureSize;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void createQuads() {
        quadList = new Quad[6];
        Vect3d start = Vect3d.nullVector();
        Vect3d end = cubeSize.copy().multiply(0.0625f);

        if (flipped) {
            double aux = end.getX();
            end.setX(start.getX());
            start.setX(aux);
        }

        Vertex vertex1 = new Vertex(start.getX(), start.getY(), start.getZ());
        Vertex vertex2 = new Vertex(end.getX(), start.getY(), start.getZ());
        Vertex vertex3 = new Vertex(end.getX(), end.getY(), start.getZ());
        Vertex vertex4 = new Vertex(start.getX(), end.getY(), start.getZ());
        Vertex vertex5 = new Vertex(start.getX(), start.getY(), end.getZ());
        Vertex vertex6 = new Vertex(end.getX(), start.getY(), end.getZ());
        Vertex vertex7 = new Vertex(end.getX(), end.getY(), end.getZ());
        Vertex vertex8 = new Vertex(start.getX(), end.getY(), end.getZ());

        double pixel = 1d / textureSize;

        double width = cubeSize.getX() * pixel;
        double height = cubeSize.getY() * pixel;
        double length = cubeSize.getZ() * pixel;

        double offsetX = textureOffset.getX() * pixel;
        double offsetY = textureOffset.getY() * pixel;

        quadList[0] = new Quad(new Vertex[]{vertex6.copy(), vertex2.copy(), vertex3.copy(), vertex7.copy()},
                offsetX + length + width,
                offsetY + length,
                offsetX + length + width + length,
                offsetY + length + height);

        quadList[1] = new Quad(new Vertex[]{vertex1.copy(), vertex5.copy(), vertex8.copy(), vertex4.copy()},
                offsetX,
                offsetY + length,
                offsetX + length,
                offsetY + length + height);

        quadList[2] = new Quad(new Vertex[]{vertex6.copy(), vertex5.copy(), vertex1.copy(), vertex2.copy()},
                offsetX + length,
                offsetY,
                offsetX + length + width,
                offsetY + length);

        quadList[3] = new Quad(new Vertex[]{vertex3.copy(), vertex4.copy(), vertex8.copy(), vertex7.copy()},
                offsetX + length + width,
                offsetY + length,
                offsetX + length + width + width,
                offsetY);

        quadList[4] = new Quad(new Vertex[]{vertex2.copy(), vertex1.copy(), vertex4.copy(), vertex3.copy()},
                offsetX + length,
                offsetY + length,
                offsetX + length + width,
                offsetY + length + height);

        quadList[5] = new Quad(new Vertex[]{vertex5.copy(), vertex6.copy(), vertex7.copy(), vertex8.copy()},
                offsetX + length + width + length,
                offsetY + length,
                offsetX + length + width + length + width,
                offsetY + length + height);

        if (flipped) {
            for (Quad aQuadList : quadList) {
                aQuadList.flipFace();
            }
        }

        for (Quad q : quadList) {
            for (int i = 0; i < 4; i++) {
                q.vertex[i].getPos().add(offset);
                if (rotation != null) {
                    q.vertex[i].getPos().rotateX(rotation.getX());
                    q.vertex[i].getPos().rotateY(rotation.getY());
                    q.vertex[i].getPos().rotateZ(rotation.getZ());
                }
                q.vertex[i].getPos().add(cubePos);
            }
        }
        //inverting Y axis
        for (Quad q : quadList) {
            for (int i = 0; i < 4; i++) {
                q.vertex[i].getPos().rotateX(Math.toRadians(180));
                q.vertex[i].getPos().add(0, 1, 1);
            }
        }
    }

    public void setOffset(Vect3d offset) {
        this.offset = offset;
    }

    public void setRotation(Vect3d rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "TestTechneCube{" +
                "name='" + name + '\'' +
                ", cubePos=" + cubePos +
                ", cubeSize=" + cubeSize +
                ", texture=" + texture +
                ", textureOffset=" + textureOffset +
                ", textureSize=" + textureSize +
                ", rotation=" + rotation +
                ", offset=" + offset +
                ", flipped=" + flipped +
                ", quadList=" + Arrays.toString(quadList) +
                '}';
    }

    private class Vertex {

        private Vect3d pos;
        private Vect2d uv;

        public Vertex(double x, double y, double z) {
            pos = new Vect3d(x, y, z);
        }

        private Vertex(Vect3d pos, Vect2d uv) {
            this.pos = pos.copy();
            this.uv = uv != null ? uv.copy() : null;
        }

        public void setUV(double u, double v) {
            uv = new Vect2d(u, v);
        }

        public Vect3d getPos() {
            return pos;
        }

        public Vect2d getUV() {
            return uv;
        }

        public Vertex copy() {
            return new Vertex(pos, uv);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "pos=" + pos +
                    ", uv=" + uv +
                    '}';
        }
    }

    private class Quad implements IQuad {

        protected static final double EPSILON = 0.0f / 1F;
        protected Vertex[] vertex;
        protected Vect3d[] normals;

        public Quad(Vertex[] vertices, double u, double v, double u2, double v2) {
            vertex = vertices;
            vertices[0].setUV(u2 - EPSILON, v + EPSILON);
            vertices[1].setUV(u + EPSILON, v + EPSILON);
            vertices[2].setUV(u + EPSILON, v2 - EPSILON);
            vertices[3].setUV(u2 - EPSILON, v2 - EPSILON);
            normals = new Vect3d[4];
            Vect3d temp1 = vertices[2].getPos().copy().sub(vertices[0].getPos());
            Vect3d temp2 = vertices[1].getPos().copy().sub(vertices[0].getPos());
            Vect3d norm = temp1.crossProduct(temp2).normalize();
            for (int i = 0; i < 4; i++) {
                normals[i] = norm;
            }
        }

        public void flipFace() {
            Vertex[] flippedVertex = new Vertex[vertex.length];
            for (int i = 0; i < vertex.length; ++i) {
                flippedVertex[i] = vertex[vertex.length - i - 1];
            }
            vertex = flippedVertex;
        }


        @Override
        public String toString() {
            return "Quad{" +
                    "vertex=" + Arrays.toString(vertex) +
                    '}';
        }

        @Override
        public Vect3d[] getVertex() {
            return new Vect3d[]{vertex[0].getPos(), vertex[1].getPos(), vertex[2].getPos(), vertex[3].getPos()};
        }

        @Override
        public Vect2d[] getUV() {
            return new Vect2d[]{vertex[0].getUV(), vertex[1].getUV(), vertex[2].getUV(), vertex[3].getUV()};
        }

        @Override
        public Vect3d[] getNormals() {
            return normals;
        }

        @Override
        public EnumFacing getSide() {
            return null;
        }
    }
}

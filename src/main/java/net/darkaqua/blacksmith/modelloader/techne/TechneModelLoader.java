package net.darkaqua.blacksmith.modelloader.techne;

import jline.internal.Log;
import net.darkaqua.blacksmith.render.ITexture;
import net.darkaqua.blacksmith.render.model.IModelPart;
import net.darkaqua.blacksmith.render.model.IQuad;
import net.darkaqua.blacksmith.vectors.Vect2d;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * Created by cout970 on 19/12/2015.
 */
public class TechneModelLoader {

    public static final List<String> cubeTypes = Arrays.asList(
            "d9e621f7-957f-4b77-b1ae-20dcd0da7751",
            "de81aa14-bd60-4228-8d8d-5238bcd3caaa"
    );

    public static TechneModelPart loadModel(ResourceLocation file, ITexture textureReference) throws ModelFormatException {
        List<TechneCube> parts = new LinkedList<>();
        InputStream stream;
        try {
            IResource res = Minecraft.getMinecraft().getResourceManager().getResource(file);
            if (res == null)
                throw new IOException();
            stream = res.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {

            ZipInputStream zipInput = new ZipInputStream(stream);
            Map<String, byte[]> zipContents = new HashMap<>();
            ZipEntry entry;

            while ((entry = zipInput.getNextEntry()) != null) {

                byte[] data = new byte[(int) entry.getSize()];
                int i = 0;
                while (zipInput.available() > 0 && i < data.length) {
                    data[i++] = (byte) zipInput.read();
                }
                zipContents.put(entry.getName(), data);
            }

            byte[] modelXml = zipContents.get("model.xml");
            if (modelXml == null) {
                throw new ModelFormatException("Model " + file + " contains no model.xml file");
            }

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(modelXml));

            NodeList nodeListTechne = document.getElementsByTagName("Techne");
            if (nodeListTechne.getLength() < 1) {
                throw new ModelFormatException("Model " + file + " contains no Techne tag");
            }

            NodeList nodeListModel = document.getElementsByTagName("Model");
            if (nodeListModel.getLength() < 1) {
                throw new ModelFormatException("Model " + file + " contains no Model tag");
            }

            NamedNodeMap modelAttributes = nodeListModel.item(0).getAttributes();
            if (modelAttributes == null) {
                throw new ModelFormatException("Model " + file + " contains a Model tag with no attributes");
            }

            Node modelTexture = modelAttributes.getNamedItem("texture");
            String texture = null;
            if (modelTexture != null) {
                texture = modelTexture.getTextContent();
            }

            NodeList textureDim = document.getElementsByTagName("TextureSize");
            Dimension textureDims = null;
            if (textureDim.getLength() > 0) {
                try {
                    String[] tmp = textureDim.item(0).getTextContent().split(",");
                    if (tmp.length == 2) {
                        textureDims = new Dimension(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                    }
                } catch (NumberFormatException e) {
                    throw new ModelFormatException("Model " + file + " contains a TextureSize tag with invalid data");
                }
            }

            NodeList shapes = document.getElementsByTagName("Shape");

            for (int i = 0; i < shapes.getLength(); i++) {
                Node shape = shapes.item(i);
                NamedNodeMap shapeAttributes = shape.getAttributes();
                if (shapeAttributes == null) {
                    throw new ModelFormatException("Shape #" + (i + 1) + " in " + file + " has no attributes");
                }

                Node name = shapeAttributes.getNamedItem("name");
                String shapeName = null;
                if (name != null) {
                    shapeName = name.getNodeValue();
                }
                if (shapeName == null) {
                    shapeName = "Shape #" + (i + 1);
                }

                String shapeType = null;
                Node type = shapeAttributes.getNamedItem("type");
                if (type != null) {
                    shapeType = type.getNodeValue();
                }
                if (shapeType != null && !cubeTypes.contains(shapeType)) {
                    Log.warn("Model shape [" + shapeName + "] in " + file + " is not a cube, ignoring");
                    continue;
                }

                try {
                    boolean mirrored = false;
                    String[] offset = new String[3];
                    String[] position = new String[3];
                    String[] rotation = new String[3];
                    String[] size = new String[3];
                    String[] textureOffset = new String[2];

                    NodeList shapeChildren = shape.getChildNodes();
                    for (int j = 0; j < shapeChildren.getLength(); j++) {
                        Node shapeChild = shapeChildren.item(j);

                        String shapeChildName = shapeChild.getNodeName();
                        String shapeChildValue = shapeChild.getTextContent();
                        if (shapeChildValue != null) {
                            shapeChildValue = shapeChildValue.trim();

                            switch (shapeChildName) {
                                case "IsMirrored":
                                    mirrored = !shapeChildValue.equals("False");
                                    break;
                                case "Offset":
                                    offset = shapeChildValue.split(",");
                                    break;
                                case "Position":
                                    position = shapeChildValue.split(",");
                                    break;
                                case "Rotation":
                                    rotation = shapeChildValue.split(",");
                                    break;
                                case "Size":
                                    size = shapeChildValue.split(",");
                                    break;
                                case "TextureOffset":
                                    textureOffset = shapeChildValue.split(",");
                                    break;
                            }
                        }
                    }

                    Vect3d cubeSize = new Vect3d(Integer.parseInt(size[0]), Integer.parseInt(size[1]), Integer.parseInt(size[2]));
                    Vect3d cubeOffset = new Vect3d(Float.parseFloat(offset[0]), Float.parseFloat(offset[1]), Float.parseFloat(offset[2]));
                    Vect3d cubePosition = new Vect3d(Float.parseFloat(position[0]), Float.parseFloat(position[1]), Float.parseFloat(position[2]));
                    Vect2d cubeTextureOffset = new Vect2d(Integer.parseInt(textureOffset[0]), Integer.parseInt(textureOffset[1]));
                    Vect3d cubeRotation = new Vect3d(
                            Math.toRadians(Float.parseFloat(rotation[0])),
                            Math.toRadians(Float.parseFloat(rotation[1])),
                            Math.toRadians(Float.parseFloat(rotation[2])));


                    TechneCube cube = new TechneCube(shapeName, cubePosition.multiply(1 / 16d).add(0.5, -0.5, 0.5),
                                                     cubeSize, textureReference, cubeTextureOffset,
                                                     (int) Math.max(textureDims != null ? textureDims.getWidth() : 32, textureDims != null ? textureDims.getHeight() : 32));

                    cube.setOffset(cubeOffset.multiply(1 / 16d));
                    cube.setRotation(cubeRotation);
                    cube.setFlipped(mirrored);
                    cube.getQuads();

                    parts.add(cube);
                } catch (NumberFormatException e) {
                    Log.warn("Model shape [" + shapeName + "] in " + file + " contains malformed integers within its data, ignoring");
                    e.printStackTrace();
                }
            }
        } catch (ZipException e) {
            throw new ModelFormatException("Model " + file + " is not a valid zip file");
        } catch (IOException e) {
            throw new ModelFormatException("Model " + file + " could not be read", e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            throw new ModelFormatException("Model " + file + " contains invalid XML", e);
        }

        return new TechneModelPart(parts, textureReference);
    }

    public static class TechneModelPart implements IModelPart {

        protected List<TechneCube> modelParts;
        protected ITexture texture;

        public TechneModelPart(List<TechneCube> modelParts, ITexture texture) {
            this.modelParts = modelParts;
            this.texture = texture;
        }

        public List<TechneCube> getModelParts() {
            return modelParts;
        }

        @Override
        public ITexture getTexture() {
            return texture;
        }

        @Override
        public List<IQuad> getQuads() {
            return modelParts.stream().flatMap(cube -> cube.getQuads().stream()).collect(Collectors.toList());
        }
    }
}

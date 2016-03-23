package net.darkaqua.blacksmith.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by cout970 on 15/11/2015.
 */
public class ResourceReference {

    private String domain;
    private String path;

    public ResourceReference(String domain, String fileName) {
        this.path = fileName;
        this.domain = domain;
    }

    public ResourceReference(ResourceLocation loc) {
        this(loc.getResourceDomain(), loc.getResourcePath());
    }

    public ResourceLocation toResourceLocation() {
        return new ResourceLocation(domain, path);
    }

    public IResource getResource() throws IOException {
        return Minecraft.getMinecraft().getResourceManager().getResource(toResourceLocation());
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public String toString() {
        return getDomain() + ":" + getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceReference)) return false;

        ResourceReference that = (ResourceReference) o;

        return !(domain != null ? !domain.equals(that.domain) : that.domain != null) && !(path != null ? !path.equals(that.path) : that.path != null);
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}

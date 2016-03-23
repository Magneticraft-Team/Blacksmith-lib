package net.darkaqua.blacksmith.config;

import net.darkaqua.blacksmith.config.internal.BS_ConfigurationFactory;

import java.io.File;

/**
 * Created by cout970 on 06/12/2015.
 */
public abstract class ConfigurationFactory {

    protected static ConfigurationFactory INSTANCE;

    static {
        BS_ConfigurationFactory.init();
    }

    public static IConfiguration create(File config) {
        return INSTANCE.createConfiguration(config, null, false);
    }

    public static IConfiguration create(File config, String version) {
        return INSTANCE.createConfiguration(config, version, false);
    }

    public static IConfiguration create(File config, String version, boolean caseSensitiveCustomCategories) {
        return INSTANCE.createConfiguration(config, version, caseSensitiveCustomCategories);
    }

    protected abstract IConfiguration createConfiguration(File config, String version, boolean caseSensitiveCustomCategories);
}

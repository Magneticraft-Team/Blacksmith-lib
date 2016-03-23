package net.darkaqua.blacksmith.lang;

import java.io.File;

/**
 * Created by cout970 on 19/03/2016.
 */
public interface ILangManager {

    void addName(Object obj, String name, String lang);

    default void addName(Object obj, String name){
        addName(obj, name, "en_US");
    }

    void save();

    void save(String lang);

    void load();

    void load(String lang);

    File getFolder();

    void clear();
}

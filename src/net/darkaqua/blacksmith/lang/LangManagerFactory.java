package net.darkaqua.blacksmith.lang;

import java.io.File;

/**
 * Created by cout970 on 19/03/2016.
 */
public class LangManagerFactory {

    public static ILangManager createLangManager(File folder){
        return new LangManager(folder);
    }
}

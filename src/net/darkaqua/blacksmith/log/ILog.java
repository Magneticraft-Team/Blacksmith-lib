package net.darkaqua.blacksmith.log;

import org.apache.logging.log4j.Level;

/**
 * Created by cout970 on 16/03/2016.
 */
public interface ILog {

    void error(String s);

    void debug(Object s);

    void info(String s);

    void warn(String s);

    void raw(String name, Level l, String s);
}

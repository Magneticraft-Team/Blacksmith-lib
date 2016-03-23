package net.darkaqua.blacksmith.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by cout970 on 16/03/2016.
 */
public class BSLog implements ILog {

    public Logger LOGGER;

    public BSLog(String prefix) {
        LOGGER = LogManager.getLogger(prefix);
    }

    @Override
    public void error(String s) {
        LOGGER.error(s);
    }

    @Override
    public void debug(Object s) {
        LOGGER.info("[DEBUG]" + s);
    }

    @Override
    public void info(String s) {
        LOGGER.info(s);
    }

    @Override
    public void warn(String s) {
        LOGGER.warn(s);
    }

    @Override
    public void raw(String name, Level l, String s) {
        LOGGER.log(l, name, s);
    }
}

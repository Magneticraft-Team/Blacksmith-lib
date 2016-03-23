package net.darkaqua.blacksmith.log;

/**
 * Created by cout970 on 16/03/2016.
 */
public class LogFactory {

    public static ILog createLog(String prefix){
        return new BSLog(prefix);
    }
}

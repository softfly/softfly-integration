package pl.softfly.oipf.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

public class LoggerUtil {

    private LoggerUtil() {

    }

    public static void start(Logger logger) {
        logger.info(StringUtils.center("START", 50, "="));
    }

    public static void end(Logger logger) {
        logger.info(StringUtils.center("END", 50, "="));
    }
}

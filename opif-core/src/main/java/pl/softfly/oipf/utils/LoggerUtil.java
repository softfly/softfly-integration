package pl.softfly.oipf.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

public class LoggerUtil {

    private LoggerUtil() {

    }

    public static String start() {
        return StringUtils.center("START", 50, "=");
    }

    public static String end() {
        return StringUtils.center("END", 50, "=");
    }
}

package util;

import org.slf4j.Logger;

public class LogUtil {

    /**
     * trace log request
     *
     * @param logger
     * @param msg
     */
    public static void trace(Logger logger, String msg) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(msg);
    }

    public static void trace(Logger logger, String msg, Throwable t) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(msg, t);
    }

    public static void trace(Logger logger, String msg, Object... objects) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(msg, objects);

    }

    public static void trace(Logger logger, String msg, Throwable t, Object... objects) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(msg, t, objects);
    }


    /**
     * debug log request
     *
     * @param logger
     * @param msg
     */
    public static void debug(Logger logger, String msg) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(msg);

    }

    public static void debug(Logger logger, String msg, Throwable t) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(msg, t);
    }

    public static void debug(Logger logger, String msg, Object... objects) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(msg, objects);
    }


    public static void debug(Logger logger, String msg, Throwable t, Object... objects) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(msg, t, objects);
    }

    /**
     * info log request
     *
     * @param logger
     * @param msg
     */
    public static void info(Logger logger, String msg) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(msg);
    }

    public static void info(Logger logger, String msg, Throwable t) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(msg, t);
    }

    public static void info(Logger logger, String msg, Object... objects) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(msg, objects);
    }


    public static void info(Logger logger, String msg, Throwable t, Object... objects) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(msg, t, objects);
    }


    /**
     * warn log request
     *
     * @param logger
     * @param msg
     */
    public static void warn(Logger logger, String msg) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(msg);
    }

    public static void warn(Logger logger, String msg, Throwable t) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(msg, t);
    }

    public static void warn(Logger logger, String msg, Object... objects) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(msg, objects);
    }


    public static void warn(Logger logger, String msg, Throwable t, Object... objects) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(msg, t, objects);
    }


    /**
     * error log request
     *
     * @param logger
     * @param msg
     */
    public static void error(Logger logger, String msg) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        logger.error(msg);
    }

    public static void error(Logger logger, String msg, Throwable t) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        logger.error(msg, t);
    }

    public static void error(Logger logger, String msg, Object... objects) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        logger.error(msg, objects);
    }

    public static void error(Logger logger, String msg, Throwable t, Object... objects) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        logger.error(msg, t, objects);
    }
}

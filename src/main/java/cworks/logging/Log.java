package cworks.logging;

import cworks.logging.loggers.LoggerBuilder;

public final class Log {

    /**
     * One and only context, saving for quicker access
     */
    private static LogContext context = LogContext.getContext();
    
    /**
     * Builder method used to bake a customized logger chain with custom options
     * @return
     */
    public static LoggerBuilder newLogger() {
        return new LoggerBuilder();
    }
    
    /**
     * Log something at the trace level
     * @param something
     */
    public static void trace(String something) {
        log(Level.TRACE, something);
    }

    /**
     * Log something at the trace level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void trace(String something, String tags) {
        log(Level.TRACE, something, tags);
    }

    /**
     * Log something at the debug level
     * @param something
     */
    public static void debug(String something) {
        log(Level.DEBUG, something);
    }

    /**
     * Log something at the debug level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void debug(String something, String tags) {
        log(Level.DEBUG, something, tags);
    }

    /**
     * Log something at the info level
     * @param something
     */
    public static void info(String something) {
        log(Level.INFO, something);
    }

    /**
     * Log something at the info level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void info(String something, String...tags) {
        log(Level.INFO, something, tags);
    }

    /**
     * Log something at the warn level
     * @param something
     */
    public static void warn(String something) {
        log(Level.WARN, something);
    }

    /**
     * Log something at the warn level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void warn(String something, String...tags) {
        log(Level.WARN, something, tags);
    }

    /**
     * Log something at the error level
     * @param something
     */
    public static void error(String something) {
        log(Level.ERROR, something);
    }

    /**
     * Log something at the error level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void error(String something, String...tags) {
        log(Level.ERROR, something, tags);
    }
    
    public static void log(String something) {
        log(Level.INFO, something);
    }
    
    public static void log(String something, String...tags) {
        log(Level.INFO, something, tags);
    }

    public static void log(Level level, String something) {
        context.forEach(logger -> {
           logger.write(level, something);
        });
    }
    
    public static void log(Level level, String something, String...tags) {
        context.forEach(logger -> {
            logger.write(level, something, tags);
        });
    }

}

package cworks.logging;

import cworks.logging.loggers.LoggerBuilder;
import cworks.logging.loggers.LoggingContext;

public final class Log {

    /**
     * One and only context, saving for quicker access
     */
    private static LoggingContext context = LoggingContext.getContext();

    /**
     * This flag controls whether logging will happen or not. 
     */
    private static boolean enabled = true;
    
    /**
     * Builder method used to bake a customized logger chain with custom options
     * @return new builder
     */
    public static LoggerBuilder newLogger() {
        return new LoggerBuilder();
    }

    /**
     * Will turn off logging
     */
    public static void disable() {
        enabled = false;
    }

    /**
     * Will turn on logging
     */
    public static void enable() {
        enabled = true;
    }

    /**
     * returns the state of this Log
     * @return
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     * Enables logging at the trace level and below (trace, debug, info, warn, error)
     */
    public static void trace() {
        enable();
        context.level(Level.TRACE);
    }
    
    /**
     * Log something at the trace level
     * @param something
     */
    public static void trace(String something) {
        log(Level.TRACE, something);
    }

    /**
     * Log something plus a Throwable at the trace level 
     * @param something
     * @param throwable
     */
    public static void trace(String something, Throwable throwable) {
        log(Level.TRACE, something, throwable);
    }

    /**
     * Log something at the trace level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void trace(String something, String...tags) {
        log(Level.TRACE, something, tags);
    }

    /**
     * Log something plus a Throwable with custom tags (helps with grepping) at the trace level
     * @param something
     * @param throwable
     * @param tags
     */
    public static void trace(String something, Throwable throwable, String...tags) {
        log(Level.TRACE, something, throwable, tags);
    }

    /**
     * Enables logging at the debug level and below (debug, info, warn, error)
     */
    public static void debug() {
        enable();
        context.level(Level.DEBUG);
    }
    
    /**
     * Log something at the debug level
     * @param something
     */
    public static void debug(String something) {
        log(Level.DEBUG, something);
    }

    /**
     * Log something at the debug level
     * @param something
     */
    public static void debug(String something, Throwable throwable) {
        log(Level.DEBUG, something, throwable);
    }

    /**
     * Log something at the debug level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void debug(String something, String...tags) {
        log(Level.DEBUG, something, tags);
    }

    /**
     * Log something at the debug level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void debug(String something, Throwable throwable, String...tags) {
        log(Level.DEBUG, something, throwable, tags);
    }

    /**
     * Enable logging at the info level and below (info, warn, error)
     */
    public static void info() {
        enable();
        context.level(Level.INFO);
    }
    
    /**
     * Log something at the info level
     * @param something
     */
    public static void info(String something) {
        log(Level.INFO, something);
    }

    /**
     * Log something at the info level
     * @param something
     */
    public static void info(String something, Throwable throwable) {
        log(Level.INFO, something, throwable);
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
     * Log something at the info level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void info(String something, Throwable throwable, String...tags) {
        log(Level.INFO, something, throwable, tags);
    }

    /**
     * Enable logging at the warn level and below (warn, error)
     */
    public static void warn() {
        enable();
        context.level(Level.WARN);
    }
    
    /**
     * Log something at the warn level
     * @param something
     */
    public static void warn(String something) {
        log(Level.WARN, something);
    }

    /**
     * Log something at the warn level
     * @param something
     */
    public static void warn(String something, Throwable throwable) {
        log(Level.WARN, something, throwable);
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
     * Log something at the warn level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void warn(String something, Throwable throwable, String...tags) {
        log(Level.WARN, something, throwable, tags);
    }

    /**
     * Enable logging at the error level and below (error)
     */
    public static void error() {
        enable();
        context.level(Level.ERROR);
    }

    /**
     * Log something at the error level
     * @param something
     */
    public static void error(String something) {
        log(Level.ERROR, something);
    }
    
    /**
     * Log something at the error level
     * @param something
     */
    public static void error(String something, Throwable throwable) {
        log(Level.ERROR, something, throwable);
    }

    /**
     * Log something at the error level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void error(String something, String...tags) {
        log(Level.ERROR, something, tags);
    }

    /**
     * Log something at the error level with custom tags (helps with grepping)
     * @param something
     * @param tags
     */
    public static void error(String something, Throwable throwable, String...tags) {
        log(Level.ERROR, something, throwable, tags);
    }

    /**
     * Log something at the default logging level 
     * @param something
     */
    public static void log(String something) {
        log(Level.DEBUG, something);
    }

    /**
     * Log something at the default logging level with tags 
     * @param something
     * @param tags
     */
    public static void log(String something, String...tags) {
        log(Level.DEBUG, something, tags);
    }

    /**
     * Log something at the specific level 
     * @param level
     * @param something
     */
    public static void log(Level level, String something) {
        if(enabled) {
            context.forEach(logger -> {
                logger.write(level, something);
            });
        }
    }

    /**
     * Log something at the specific level with a Throwable 
     * @param level
     * @param something
     * @param throwable
     */
    private static void log(Level level, String something, Throwable throwable) {
        if(enabled) {
            context.forEach(logger -> {
                logger.write(level, something, throwable);
            });
        }
    }

    /**
     * Log something at the specific level with some tags 
     * @param level
     * @param something
     * @param tags
     */
    public static void log(Level level, String something, String...tags) {
        if(enabled) {
            context.forEach(logger -> {
                logger.write(level, something, tags);
            });
        }
    }

    /**
     * Log something at the specific level with tags and a Throwable
     * @param level
     * @param something
     * @param throwable
     * @param tags
     */
    private static void log(Level level, String something, Throwable throwable, String...tags) {
        if(enabled) {
            context.forEach(logger -> {
                logger.write(level, something, throwable, tags);
            });
        }
    }

}

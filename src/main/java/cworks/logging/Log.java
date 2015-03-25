package cworks.logging;

import cworks.logging.loggers.LoggerBuilder;

public class Log {

    /**
     * One and only context, saving for quicker access
     */
    private static LogContext context = LogContext.getContext();
    
    /**
     * Builder method used to bake a customized logger chain with custom options
     * @return
     */
    public static LoggerBuilder newSetup() {
        return new LoggerBuilder();
    }
    
    public static void error(String something) {
        log(Level.ERROR, something);
    }
    
    public static void warn(String something) {
        log(Level.WARN, something);
    }
    
    public static void info(String something) {
        log(Level.INFO, something);
    }
    
    public static void debug(String something) {
        log(Level.DEBUG, something);
    }
    
    public static void log(String something) {
        log(Level.INFO, something);
    }
    
    public static void log(String something, String...tags) {
        log(Level.INFO, something, tags);
    }

    public static void log(Level level, String something) {
        context.chain().write(level, something);
    }
    
    public static void log(Level level, String something, String...tags) {
        context.chain().write(level, something, tags);
    }

}

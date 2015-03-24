/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: Log
 * Created: 3/23/15 10:18 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.logging;

import cworks.logging.loggers.BasicLogBuilder;
import cworks.logging.loggers.FileLogger;
import cworks.logging.loggers.SystemOutLogger;

public class Log {

    private static final Logger CHAIN = createLoggers();

    public static LogBuilder setup() {
        return new BasicLogBuilder();
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

    public static void log(Level level, String something) {
        
        CHAIN.write(level, something);
        

    }

    private static Logger createLoggers() {
        Logger soutLogger = new SystemOutLogger(Level.INFO);
        Logger fileLogger = new FileLogger(Level.INFO);
        soutLogger.setNext(fileLogger);
        return soutLogger;
    }
    
}

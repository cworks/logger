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

    private static final boolean TO_FILE = System.getProperty("log") != null;
    
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
    
    public static void log(String something, String...tags) {
        log(Level.INFO, something, tags);
    }

    public static void log(Level level, String something) {
        CHAIN.write(level, something);
    }
    
    public static void log(Level level, String something, String...tags) {
        CHAIN.write(level, something, tags);
    }

    /**
     * A slightly important factory method which is intended to always return
     * at least one functional Logger but could return more than one based
     * on user configuration.  Each logger is created to log at or above
     * a certain level and with a specific line format.
     *  
     * @return 
     */
    private static Logger createLoggers() {
        
        // get properties first from system properties, then environment
        
        Logger soutLogger = new SystemOutLogger(Level.INFO);
        if(Log.TO_FILE) {
            Logger fileLogger = new FileLogger(Level.INFO);
            soutLogger.setNext(fileLogger);
        }
        
        return soutLogger;
    }
    
}

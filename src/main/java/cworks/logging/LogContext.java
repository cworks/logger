package cworks.logging;

import cworks.logging.loggers.FileLogger;
import cworks.logging.loggers.Logger;
import cworks.logging.loggers.SystemOutLogger;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public final class LogContext {

    /**
     * The one and only logging context
     */
    private static final LogContext INSTANCE;
    
    static {
        INSTANCE = new LogContext();
        INSTANCE.addLogger(createSystemOutLogger());
        if(System.getProperty("log.file") != null) {
            try {
                Logger logger = createFileLogger();
                if(logger != null) {
                    INSTANCE.addLogger(logger);
                }
            } catch(Exception ex) {
                INSTANCE.chain().stream().forEach(logger -> {
                    logger.write(Level.ERROR, "Error creating log file in static initializer.");
                });
            }
        }
    }
    
    /**
     * If we have file loggers they are captured here so that we can close them when JVM exits 
     */
    final private List<Logger> chain;
    
    /**
     * Tags to use on every log statement 
     */
    private String[] tags;
    
    /**
     * Private construction because LogContext is a singleton
     */
    private LogContext() {
        this.chain = new LinkedList<>();
    }

    public static LogContext getContext() {
        return INSTANCE;
    }
    
    /**
     * A slightly important factory method which is intended to always return
     * at least one functional Logger but could return more than one based
     * on user configuration.  Each logger is created to log at or above
     * a certain level and with a specific line format.
     *
     * We should follow the pattern of getting properties from the system first
     * so that they can override environmental properties.  If system properties
     * don't exist then look for environmental properties.  If environmental
     * properties are absent then use sensible defaults.
     *
     * properties and ENV_VAR
     * log.file       LOG_FILE
     * log.level      LOG_LEVEL
     * log.name       LOG_NAME
     * log.tags       LOG_TAGS
     * @return top of the Logger chain
     */
    static Logger createFileLogger() {
        Logger logger = null;
        if(System.getProperty("log.file") != null) {
            File file = new File(System.getProperty("log.file"));
            // TODO, need to verify we can write to file.
            String tags = System.getProperty("log.tags");
            if(tags != null) {
                logger = new FileLogger(
                    System.getProperty("log.name", "file-log"),
                    Level.valueOf(System.getProperty("log.level", "DEBUG")),
                    file,
                    tags.split("\\s*,\\s*"));
            } else {
                logger = new FileLogger(
                    System.getProperty("log.name", "file-log"),
                    Level.valueOf(System.getProperty("log.level", "DEBUG")),
                    file);
            }
        }
        return logger;
    }
    
    static Logger createSystemOutLogger() {
        Logger logger;
        String tags = System.getProperty("log.tags");
        if(tags != null) {
            logger = new SystemOutLogger(
                System.getProperty("log.name", "system-out-log"),
                Level.valueOf(System.getProperty("log.level", "DEBUG")),
                tags.split("\\s*,\\s*"));
        } else {
            logger = new SystemOutLogger(
                System.getProperty("log.name", "system-out-log"),
                Level.valueOf(System.getProperty("log.level", "DEBUG")));
        }
        return logger;
    }

    public List<Logger> chain() {
        return this.chain;
    }

    public void addLogger(Logger logger) {
        chain.add(logger);
    }
}

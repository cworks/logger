package cworks.logging;

import cworks.logging.loggers.FileLogger;
import cworks.logging.loggers.Logger;
import cworks.logging.loggers.SystemOutLogger;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public final class LogContext {
    
    public static final String LOG_NAME_PROPERTY  = "log.name";
    public static final String LOG_LEVEL_PROPERTY = "log.level";
    public static final String LOG_TAGS_PROPERTY  = "log.tags";
    public static final String LOG_FILE_PROPERTY  = "log.file";

    /**
     * The one and only logging context
     */
    private static final LogContext INSTANCE;
    
    static {
        INSTANCE = new LogContext();
        INSTANCE.addLogger(createSystemOutLogger());
        if(System.getProperty(LOG_FILE_PROPERTY) != null) {
            try {
                Logger logger = createFileLogger();
                if(logger != null) {
                    INSTANCE.addLogger(logger);
                }
            } catch(Exception ex) {
                INSTANCE.chain.stream().forEach(logger -> {
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
        if(System.getProperty(LOG_FILE_PROPERTY) != null) {
            File file = new File(System.getProperty(LOG_FILE_PROPERTY));
            // TODO, need to verify we can write to file.
            String tags = System.getProperty(LOG_TAGS_PROPERTY);
            if(tags != null) {
                logger = new FileLogger(
                    System.getProperty(LOG_NAME_PROPERTY, "file-log"),
                    Level.valueOf(System.getProperty(LOG_LEVEL_PROPERTY, "DEBUG")),
                    file,
                    tags.split("\\s*,\\s*"));
            } else {
                logger = new FileLogger(
                    System.getProperty(LOG_NAME_PROPERTY, "file-log"),
                    Level.valueOf(System.getProperty(LOG_LEVEL_PROPERTY, "DEBUG")),
                    file);
            }
        }
        return logger;
    }
    
    static Logger createSystemOutLogger() {
        Logger logger;
        String tags = System.getProperty(LOG_TAGS_PROPERTY);
        if(tags != null) {
            logger = new SystemOutLogger(
                System.getProperty(LOG_NAME_PROPERTY, "system-out-log"),
                Level.valueOf(System.getProperty(LOG_LEVEL_PROPERTY, "DEBUG")),
                tags.split("\\s*,\\s*"));
        } else {
            logger = new SystemOutLogger(
                System.getProperty(LOG_NAME_PROPERTY, "system-out-log"),
                Level.valueOf(System.getProperty(LOG_LEVEL_PROPERTY, "DEBUG")));
        }
        return logger;
    }

    public void forEach(Consumer<? super Logger> action) {
        chain.stream().forEach(action::accept);
    }

    public void addLogger(Logger logger) {
        chain.add(logger);
    }

}

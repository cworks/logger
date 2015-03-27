package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.internal.LoggerLinkedList;
import cworks.logging.io.IO;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public final class LoggingContext {

    /**
     * Public property that controls how we start up - name of log
     */
    public static final String LOG_NAME_PROPERTY  = "log.name";

    /**
     * Public property that controls how we start up - log level 
     */
    public static final String LOG_LEVEL_PROPERTY = "log.level";

    /**
     * Public property that controls how we start up - log tags 
     */
    public static final String LOG_TAGS_PROPERTY  = "log.tags";

    /**
     * Public property that controls how we start up - log file
     */
    public static final String LOG_FILE_PROPERTY  = "log.file";

    /**
     * The one and only logging context
     */
    private static final LoggingContext INSTANCE;

    /**
     * When this class is loaded a SystemOutLogger will be created from
     * a combination of System properties and default.  In addition if
     * a System property for a file logger exists one will be created
     * from a combination of System properties and defaults.
     */
    static {
        INSTANCE = new LoggingContext();
        if(System.getProperty(LOG_FILE_PROPERTY) != null) {
            try {
                INSTANCE.addLogger(newFileLogger());
            } catch(Exception ex) {
                INSTANCE.chain.stream().forEach(logger -> {
                    logger.write(Level.ERROR, "Error creating log file in static initializer.");
                });
            }
        } else {
            INSTANCE.addLogger(newSystemOutLogger());
        }
    }
    
    /**
     * All loggers are persisted here and file loggers are closed when JVM exits
     */
    final private LoggerLinkedList chain;

    /**
     * Private construction because LogContext is a singleton
     */
    private LoggingContext() {
        this.chain = new LoggerLinkedList(100);
    }

    public static LoggingContext getContext() {
        return INSTANCE;
    }
    
    /**
     * A slightly important factory method which is intended to always return
     * a functional Logger based on JVM properties and defaults.
     *
     * properties:
     *  
     * log.file
     * log.level
     * log.name
     * log.tags
     *
     * @return top of the Logger chain
     */
    static Logger newFileLogger() throws IOException {
        Logger logger = null;
        if(System.getProperty(LOG_FILE_PROPERTY) != null) {
            File file = new File(System.getProperty(LOG_FILE_PROPERTY));
            IO.touch(file);
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
    
    static Logger newSystemOutLogger() {
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
        if(logger != null) {
            chain.add(logger);
        }
    }

    public void level(Level level) {
        forEach(logger -> {
            logger.setLevel(level);
        });
    }
}

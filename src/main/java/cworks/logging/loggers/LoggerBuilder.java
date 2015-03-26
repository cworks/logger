package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.LogContext;
import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

import java.io.File;
import java.io.IOException;

public class LoggerBuilder {
    
    private String name;
    private Level  level;
    private String[] tags;
    private File file;
    private FormatStrategy formatStrategy;
    
    public LoggerBuilder() {

    }
    
    public LoggerBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public LoggerBuilder level(String level) {
        this.level = Level.valueOf(level.trim().toUpperCase());
        return this;
    }

    public LoggerBuilder tags(String... tags) {
        this.tags = tags;
        return this;
    }

    public LoggerBuilder file(String file) throws IOException {
        this.file = new File(file);
        return this;
    }
    
    public LoggerBuilder formatStrategy(FormatStrategy strategy) {
        this.formatStrategy = strategy;
        return this;
    }

    /**
     * Create the appropriate logger and add it to LogContext
     */
    public void add() {
        Logger logger;
        
        if(file != null) {
            logger = new FileLogger(file);
        } else {
            logger = new SystemOutLogger();
        }
        
        if(name != null) {
            logger.setName(name);
        }
        
        if(level != null) {
            logger.setLevel(level);
        }

        if(tags != null) {
            logger.setTags(tags);
        }
        
        if(formatStrategy != null) {
            logger.setFormatStrategy(formatStrategy);
        } else {
            logger.setFormatStrategy(new SimpleFormatStrategy());
        }


        LogContext.getContext().addLogger(logger);
    }
}
